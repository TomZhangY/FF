package ff.communication.impl;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import ff.communication.IWriteLogic;

public class HEXLengthWriteLogic implements IWriteLogic {
	
	private Logger commLog = Logger.getLogger("Communication");

	@Override
	public void write(OutputStream os, byte[] src) throws IOException {
		int writeLength = src.length;
		byte[] lengthTag = intToBytes(writeLength);
		commLog.debug("Write Logic start write readLength:  "+ writeLength) ;
		os.write(lengthTag);
		os.write(src);
//		commLog.debug("Write Logic write data byte[]s:  "+ HexString.printHexString(src)) ;
//		commLog.debug("\r\n") ;
	}

	@Override
	public void write(OutputStream os, byte[] src, int offset, int length)
			throws IOException {
		byte[] lengthTag = intToBytes(length);
		os.write(lengthTag);
		os.write(src, offset, length);
	}

	public byte[] intToBytes(int i){
		byte[] bt = new byte[4];
		bt[3] = (byte) (0xff & i);
		bt[2] = (byte) ((0xff00 & i) >> 8);
		bt[1] = (byte) ((0xff0000 & i) >> 16);
		bt[0] = (byte) ((0xff000000 & i) >> 24);
		return bt;
	}
}
