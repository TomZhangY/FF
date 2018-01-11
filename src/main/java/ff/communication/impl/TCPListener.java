package ff.communication.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;
import ff.communication.IOperation;
import ff.communication.IPacket;
import ff.communication.IReadLogic;
import ff.communication.IReceipt;
import ff.communication.IWriteLogic;

public class TCPListener {
	private static Logger logger = Logger.getLogger(TCPListener.class);
	
	private final ReentrantLock lock = new ReentrantLock();
	
	private volatile boolean serverSocketStatus = true;
	
	protected SocketServerThread socketServerThread;
	
	// 控制读写逻辑
	protected final IReadLogic readLogic;

	protected final IWriteLogic writeLogic;
	
	private String messageEncoding = "UTF-8";
	
	// 配置通信参数
	protected final CommunicationConfigurator communicationConfig;
	
	// 线程池
	protected ThreadPoolExecutor threadPool;
	
	// 具体操作
	protected IOperation receiver;
	
	public IOperation getReceiver() {
		return receiver;
	}

	public void setReceiver(IOperation receiver) {
		this.receiver = receiver;
	}

	public ThreadPoolExecutor getThreadPool() {
		return threadPool;
	}

	public void setThreadPool(ThreadPoolExecutor threadPool) {
		this.threadPool = threadPool;
	}
	
	public String getMessageEncoding() {
		return messageEncoding;
	}

	public void setMessageEncoding(String messageEncoding) {
		this.messageEncoding = messageEncoding;
	}
	
	public TCPListener(CommunicationConfigurator communicationConfig) {
		this(communicationConfig, new BCDLengthReadLogic(),
				new BCDLengthWriteLogic());
	}

	public TCPListener(CommunicationConfigurator communicationConfig,
			IReadLogic readLogic, IWriteLogic writeLogic) {
		this.readLogic = readLogic;
		this.writeLogic = writeLogic;
		this.communicationConfig = communicationConfig;
		logger.info("Instanced " + this.getClass() + " successfully");
	}
	
	// 开启服务
	public void startListener() {
		if (null == receiver) {
			return ;
		}
		
		final ReentrantLock runLock = lock;
		runLock.lock();
		try {
			if (null == threadPool) {
				loadDefaultThreadPoolExecutor();
			}
			socketServerThread = new SocketServerThread();
			socketServerThread.start();
		} finally {
			runLock.unlock();
		}
		
	}
	
	// 默认初始化线程池
	protected void loadDefaultThreadPoolExecutor() {
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(16);
		threadPool = new TCPListenerThreadPoolExecutor(1, 1, 120000,
				TimeUnit.MILLISECONDS, queue);
	}
	
	protected class SocketServerThread extends Thread {
		private ServerSocket serverSocket;
		
		public void run() {
			try {
				serverSocket = new ServerSocket(communicationConfig.getPort(),
						communicationConfig.getClientMaximum(),
						InetAddress.getByName(communicationConfig.getAddress()));
				serverSocket.setSoTimeout(1000);
			} catch (Exception e) {
				logger.error(e);
			}
			
			while(serverSocketStatus) {
				try {
					Socket socket = serverSocket.accept();
					socket.setSoTimeout(communicationConfig.getTimeout());
					SocketThread socketThread = new SocketThread(socket);
					socketThread.start();
				} catch (java.net.SocketTimeoutException e) {
					try {
						Thread.sleep(communicationConfig
								.getTcpServerSleepTime());
					} catch (InterruptedException e1) {
						logger.debug(e);
					}
					continue;
				}catch (Exception e) {
					logger.error(e);
				}
			}
		}
		
		public void shutdown() throws IOException {
			if (null == serverSocket)
				return;
			serverSocket.close();
		}
		
	}
	
	protected class SocketThread extends Thread {
		private Socket socket;
		private String uuid;

		public SocketThread(Socket socket) {
			this.socket = socket;
			uuid = UUID.randomUUID().toString();
		}
		
		public String getUUID() {
			return uuid;
		}
		
		public void run() {
			while(serverSocketStatus) {
				try {
					byte[] buffer = readLogic.read(socket.getInputStream());
					String request = new String(buffer, messageEncoding) ;
					// 将发送的 TCPSender类 包装进 packet
					IPacket packet = createIPacket(socket.getOutputStream(),
							request);
					// 用线程池运行 操作的线程
					threadPool.execute(new ReceiverExecutor(receiver,packet));
					
				} catch (SocketTimeoutException e) {
					logger.error(e);
					e.printStackTrace();
					try {
						Thread.sleep(communicationConfig
								.getTcpListenerSleepTime());
					} catch (InterruptedException e1) {
						logger.debug(e);
					}
					continue;
				} catch (Exception e) {
					try {
						socket.close();
					} catch (Exception e1) {
						logger.error(e1);
					}
					break;
				}
			}
		}
		
		private IPacket createIPacket(OutputStream ops, String messageObj)
				throws IOException {
			TCPSender sender = new TCPSender(socket);
			sender.setEncoding(messageEncoding);
			sender.setWriteLogic(writeLogic);
			return new Packet(sender, messageObj);
		}
	}
	
	/**
	 * 执行处理xml和返回的数据
	 * @author Tom
	 *
	 */
	private class ReceiverExecutor extends AbstractReceiver{
		
		public ReceiverExecutor(IOperation operation, IPacket packet) {
			super(operation, packet);
		}
		
		@Override
		public void run() {
			// 处理 发送的数据
			IReceipt receipt = (IReceipt) operation.operate(packet);
			// 准备返回的数据
			packet.getSender().operate(receipt);
		}
	}
	
}
