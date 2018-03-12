package jPathWatch;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.tap.vpos2.beans.Accounts;
import org.tap.vpos2.beans.LoyaltyInfo;
import org.tap.vpos2.beans.ObjectFactory;
import org.tap.vpos2.beans.VPOS;

public class XmlHelp {
	private static JAXBContext context = null;
	
	static{
		try {
			context = JAXBContext.newInstance(ObjectFactory.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	public static Object unmarshaller(String xml) throws JAXBException{
		return context.createUnmarshaller().unmarshal(new StringReader(xml));
	}
	
	public static String marshaller(VPOS vpos) throws JAXBException{
		StringWriter writer = new StringWriter();
		context.createMarshaller().marshal(vpos, writer);
		return writer.toString();
	}
	
	public static LoyaltyInfo unmarshallerLoyaltyInfo(String xmlPath ,String ticketID) throws JAXBException{
		File file = new File(xmlPath);
		if(!file.exists()) {
			return null ;
		}
		if(ticketID == null || ticketID.trim().equals("")) {
			return null ;
		}
		try {
			InputStream in = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(in);  
			LoyaltyInfo info = (LoyaltyInfo) context.createUnmarshaller().unmarshal(reader); 
			if(info != null) {
				String ffTicketId = info.getTransID() ;
				if(ffTicketId == null) {
					ffTicketId = "" ;
				}
				ticketID = ticketID.trim();
				ffTicketId = ffTicketId.trim();
				
				if(!ticketID.equals(ffTicketId)) {
					return null ;
				}
				List<Accounts> accounts = info.getAccounts();
				if(accounts != null && accounts.size()>0) {
					for(Accounts account : accounts) {
						String originalPoint = account.getOriginalPoint();
						String redeem = account.getRedeem();
						String earned = account.getEarned();
						String balance = account.getBalance();
						if(originalPoint == null || originalPoint.equals("")) {
							account.setOriginalPoint("0");
						}
						if(redeem == null || redeem.equals("")) {
							account.setRedeem("0");
						}
						if(earned == null || earned.equals("")) {
							account.setEarned("0");
						}
						if(balance == null || balance.equals("")) {
							account.setBalance("0");
						}
					}
				}
			}
			
			return info ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}  
		return null ;
	}
	
	public static void main(String[] args) throws JAXBException {
//		LoyaltyInfo info = XmlHelp.unmarshallerLoyaltyInfo("./src/test/java/LoyaltyInfo.xml") ;
//		if(info != null) {
//			System.out.println(info);
//			System.out.println(info.getLoyaltyNumber());
//		}
	}
}
