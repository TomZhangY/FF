package ff.communication.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import ff.communication.IReadLogic;

public class BCDLengthReadLogic implements IReadLogic {
	
	private long maxLength = 512000 ;
	
	public long getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(long maxLength) {
		this.maxLength = maxLength;
	}
	
	private static final Logger commLog = Logger.getLogger("Communication");
	
	@Override
	public int read(InputStream is, byte[] buff) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		readSocketInputStream(baos,is);
		int length = baos.size();
		byte[] read = baos.toByteArray();
		for(int i = 0;i < length;i++){
			buff[i] = read[i];
		}
		baos.close();
		return length;
	}

	@Override
	public byte[] read(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		readSocketInputStream(baos,is);
		byte[] buff = baos.toByteArray();
		baos.close();
		return buff;
	}

	private boolean readSocketInputStream(ByteArrayOutputStream baos, InputStream is) throws IOException{
		int length = readLength(is);
		if(length > this.maxLength){
			String error = length + " bytes ,Over maximum receive data length  "+ maxLength + " bytes." ;
			commLog.error(error) ;
			throw new IOException(error) ;
		}
		commLog.debug("Read length bit, the total length is " + length + " bytes.");
		return readContent(baos,is,length);
	}
	
	private int readLength(InputStream is) throws IOException{
		int high = 0;
		int low = 0;
		// first read high
		high = is.read();
		if(high == -1)
			throw new IOException("Client inputStream has been closed");
		low = is.read();
		if(low == -1)
			throw new IOException("Client inputStream has been closed");
		commLog.debug("read high byte is " + high + ", low byte is " + low);
		return high * 256 + low;
	}
	
	private boolean readContent(ByteArrayOutputStream baos,InputStream is,int sendLength) throws IOException{
		int readLength = 0;
		int b = -1;
		try{
			while((b = is.read()) != -1){
				readLength++;
				baos.write(b);
				if(readLength == sendLength){
					break;
				}
			}
		}catch(SocketTimeoutException e){
			throw e;
		}finally{
			baos.flush();
		}
		return true;
	}
}
