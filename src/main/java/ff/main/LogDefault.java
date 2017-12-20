package ff.main;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class LogDefault {
	// 
	private static Logger logger = Logger.getLogger(LogDefault.class);

	public static void main(String[] args) {
		// 通过Xml
		DOMConfigurator.configure(".conf/log4j.xml");
		// 通过properties
		PropertyConfigurator.configure("conf/log4j.properties");
		
		logger.debug("load log4j");
	}
	
}
