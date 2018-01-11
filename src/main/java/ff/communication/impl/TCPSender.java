package ff.communication.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import ff.communication.IReceipt;
import ff.communication.ISender;
import ff.communication.IWriteLogic;


public class TCPSender implements ISender{
	
	private static Logger logger = Logger.getLogger(TCPSender.class);
	
	protected final Socket socket;
	
	private IWriteLogic writeLogic;
	
	private String encoding = "UTF-8";
	
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	@Override
	public void setWriteLogic(IWriteLogic writeLogic) {
		this.writeLogic = writeLogic;
	}

	public IWriteLogic getWriteLogic() {
		return writeLogic;
	}
	
	public TCPSender(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public OutputStream getOutputStream() throws IOException {
		return socket.getOutputStream();
	}

	@Override
	public Object operate(Object info)  {
		IReceipt receipt = (IReceipt)info;
		try {
			if(null == writeLogic)
				throw new NullPointerException("Sender didn't set a write logic.");
			
			if(receipt.getSendContent() instanceof String){
				String message = (String) receipt.getSendContent();
				writeLogic.write(socket.getOutputStream(),message.getBytes(encoding));
			}else{
				logger.debug("Can't convert " + receipt.getSendContent().getClass() + " to byte array");
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}
}
