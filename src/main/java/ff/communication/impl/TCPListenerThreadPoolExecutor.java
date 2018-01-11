package ff.communication.impl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ff.communication.IReceiverExceptionHandler;


public class TCPListenerThreadPoolExecutor extends ThreadPoolExecutor{
	
	//统一异常处理
	protected IReceiverExceptionHandler receiverExceptionHandler;
	
	public IReceiverExceptionHandler getReceiverExceptionHandler() {
		return receiverExceptionHandler;
	}

	public void setReceiverExceptionHandler(
			IReceiverExceptionHandler receiverExceptionHandler) {
		this.receiverExceptionHandler = receiverExceptionHandler;
	}

	public TCPListenerThreadPoolExecutor(int corePoolSize,
			int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	public TCPListenerThreadPoolExecutor(int corePoolSize,
			int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
	}

	public TCPListenerThreadPoolExecutor(int corePoolSize,
			int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory,
			RejectedExecutionHandler handler) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory, handler);
	}

	public TCPListenerThreadPoolExecutor(int corePoolSize,
			int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				threadFactory);
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		// 执行完后如果有异常就会发送空的xml回去
		if(null == t){
			return ;
		}
		AbstractReceiver receiver = (AbstractReceiver)r;
		if(receiverExceptionHandler == null){
			return ;
		}
		receiverExceptionHandler.handleException(receiver.getPacket(), t);
	}
}
