package jPathWatch;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.tap.vpos2.beans.VPOS;

import name.pachler.nio.file.StandardWatchEventKind;

public class FileWatchOperation {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	private Pattern fileNamePattern = Pattern.compile("StockMngData.xml");
	
	// private String ignoreContent = "<?xml version=\"1.0\"?>";
	private String ignoreContent = "<\\?xml.*?\\?>";
		
	private int readRetry = 3;
	
	public Object operate(Object object) {
		if (object instanceof FileWatchObject) {
			FileWatchObject obj = (FileWatchObject) object;
			if (obj.getEventKind() == StandardWatchEventKind.ENTRY_CREATE || obj.getEventKind() == StandardWatchEventKind.ENTRY_MODIFY) {
				if (fileNamePattern.matcher(obj.getFileName()).matches()) {
					
					String fileContent = getFileContent(obj.getFilePath(),readRetry);
					System.out.println("fileContent = "+ fileContent);
					if (StringUtils.contains(fileContent, "TicketEnd")) {
						try {
							VPOS root = (VPOS) XmlHelp.unmarshaller(fileContent);
							System.out.println(fileContent);
						} catch (JAXBException e) {
							
						}	
					}
				}
			}
		}
		return null;
	}
	
	private String getFileContent(String filePath,int retry) {
		String str = "";
		File file = new File(filePath);
		try {
			str = FileUtils.readFileToString(file).replaceAll(ignoreContent, "").trim();
		} catch (IOException e) {
			logger.error("unable to read file: " + filePath + ", remaining retry: " + (retry - 1));
			if (retry > 0)
				return getFileContent(filePath, retry - 1);
		}
		return str;
	}
}
