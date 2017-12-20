package ff.main;

import org.apache.log4j.Logger;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {
		org.apache.log4j.PropertyConfigurator.configure("conf/log4j.properties");
		new Thread() {
			@Override
			public void run() {
				logger.debug("我是Tom");
			}
			
		}.start();
		logger.error("123");
//System.err.println("1");

	}
	
}
