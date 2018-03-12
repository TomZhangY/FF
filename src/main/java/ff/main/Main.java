package ff.main;

import org.apache.log4j.Logger;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ff.communication.impl.TCPListener;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
	private static FileSystemXmlApplicationContext context;
	
	
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("conf/log4j.properties");
		
		try {
			context = new FileSystemXmlApplicationContext("conf/system.xml");
			TCPListener tcp = (TCPListener)context.getBean("tcpListener");
//			tcp.startListener();
			
			// jvm 退出时会执行这个线程
			Runtime.getRuntime().addShutdownHook(
					new Thread() {
						@Override
						public void run() {
							System.out.println("jvm 关闭前需要执行这个线程");
						}
					});
			
		} catch (Exception e) {
			logger.fatal(e.getMessage(), e);
		}
		
		
		new Thread() {
			@Override
			public void run() {
				logger.debug("我是Tom");
			}
		}.start();
		logger.error("123");
	}
	
}
