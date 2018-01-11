package ff.communication.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import ff.communication.IReadLogic;

public class HEXLengthReadLogic implements IReadLogic {
	
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
		return readSocketInputStream(buff,is);
	}

	@Override
	public byte[] read(InputStream is) throws IOException {
		int length = readLength(is);
		commLog.debug("Read four length bit, the total length is " + length + " bytes.");
		if(length > this.maxLength){
			String error = length + " bytes ,Over maximum receive data length  "+maxLength + " bytes." ;
			commLog.error(error) ;
			throw new IOException(error) ;
		}
		byte[] buff = new byte[length];
		readContent(is, buff, length);
		return buff;
	}

	private int readSocketInputStream(byte[] dest, InputStream is) throws IOException{
		int length = readLength(is);
		commLog.debug("Read length bit, the total length is " + length + " bytes.");
		if(length > this.maxLength){
			String error = length + " bytes ,Over maximum receive data length  "+maxLength + " bytes." ;
			commLog.error(error) ;
			throw new IOException(error) ;
		}
		return readContent(is,dest,length);
	}
	
	private int readLength(InputStream is) throws IOException{
		byte[] bytes = new byte[4];
		for(int i = 0;i < bytes.length;i++){
			int b = is.read();
			if(b == -1)
				throw new IOException("Client inputStream has been closed");
			bytes[i] = (byte)(b & 0xff);
			commLog.debug("Read length tag byte[" + i + "] value is " + b);
		}
		
		return bytesToInt(bytes);
	}
	
	private int bytesToInt(byte[] bytes){
		int num = bytes[3] & 0xFF;
		num |= ((bytes[2] << 8) & 0xFF00);
		num |= ((bytes[1] << 16) & 0xFF0000);
		num |= ((bytes[0] << 24) & 0xFF000000);
		return num;
	}
	
	private int readContent(InputStream is,byte[] dest, int readLength) throws IOException{
		int index = 0;
		commLog.debug("Read Logic start read content, readLength:  "+ readLength) ;
		try{
			while(index < readLength){
				dest[index++] = (byte)is.read();
			}
//			commLog.debug("Read Logic readContent data byte[]s:  "+ HexString.printHexString(dest)) ;
		}catch(SocketTimeoutException e){
			e.printStackTrace();
			commLog.debug("Read Logic SocketTimeoutException:  "+ e) ;
			throw e;
		}catch(Exception e1){
			e1.printStackTrace();
			commLog.debug("Read Logic Exception:  "+ e1) ;
			throw new IOException(e1);
		}
		return index;
	}
	
	public static void main(String[] args) {
		
	}

}
