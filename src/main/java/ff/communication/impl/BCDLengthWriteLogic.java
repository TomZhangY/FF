package ff.communication.impl;

import java.io.IOException;
import java.io.OutputStream;

import ff.communication.IWriteLogic;

public class BCDLengthWriteLogic implements IWriteLogic {

	@Override
	public void write(OutputStream os, byte[] src) throws IOException {
		final int length = src.length;
		
		byte[] buffer = new byte[length + 2];
		buffer[0] = (byte)((length / 256) & 0xff);
		buffer[1] = (byte)((length % 256) & 0xff);
		System.arraycopy(src, 0, buffer, 2, length);
		
		os.write(buffer);
		os.flush();
	}

	@Override
	public void write(OutputStream os, byte[] src, int offset, int length)
			throws IOException {

		byte[] buffer = new byte[length + 2];
		buffer[0] = (byte)((length / 256) & 0xff);
		buffer[1] = (byte)((length % 256) & 0xff);
		System.arraycopy(src, offset, buffer, 2, length);
		
		os.write(buffer);
		os.flush();
	}

}
