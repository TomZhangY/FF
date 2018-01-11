package ff.communication;

import java.io.IOException;
import java.io.OutputStream;

public interface IWriteLogic {
	
	public void write(OutputStream os, byte[] src) throws IOException;
	
	public void write(OutputStream os, byte[] src, int offset, int length) throws IOException;
}
