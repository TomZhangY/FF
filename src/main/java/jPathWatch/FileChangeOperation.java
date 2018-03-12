package jPathWatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChangeOperation {
	private Matcher m0, m1;
	private boolean isInquiryResponse = false;
	
	private Pattern requestPrefix = Pattern.compile("(?i)Send Loyalty Read Request"); // next <Esp:Interface /> is request
	private Pattern responsePrefix = Pattern.compile("(?i)Inquiry Response Arrived"); // next <Esp:Interface /> is response
	private Pattern interfacePattern = Pattern.compile("(<Esp:Interface.*?<\\/Esp:Interface>)");
	
	
	public Object operate(Object info) {
		try {
			if (info instanceof String) {
				String log = (String) info;
				if ((m0 = requestPrefix.matcher(log)).find()) {
					return null;

				}
				if ((m0 = responsePrefix.matcher(log)).find()) {
					isInquiryResponse = true;
					return null;
				}
				if (isInquiryResponse && (m1 = interfacePattern.matcher(log)).find()) {
					String responseXml = m1.group(1);
					processRoyalty(responseXml);
					isInquiryResponse = false;
					return null;
				}
			}
		} catch (Exception e) {
			
		}
		return null;
	}
	
	private void processRoyalty(String responseXml) {
		
	}
}
