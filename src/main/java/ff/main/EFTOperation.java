package ff.main;

import org.apache.log4j.Logger;

import ff.communication.IOperation;
import ff.communication.IPacket;
import ff.communication.impl.Receipt;

public class EFTOperation implements IOperation {

	private static Logger logger = Logger.getLogger(EFTOperation.class);
	
	public Object operate(Object info) {
		if (!(info instanceof IPacket))
			throw new IllegalArgumentException(
					"info is not an instance of IPacket.");
		IPacket packet = (IPacket)info;
		Receipt receipt = null;
		try{
			String submitObject = (String)packet.getReceived();
			String vposr = submitObject;
			System.out.println(vposr);
			logger.debug(vposr);
			receipt = new Receipt(packet, vposr);
		}catch(Exception objException){
			logger.error(objException);
		}
		return receipt;
	}
	
}
