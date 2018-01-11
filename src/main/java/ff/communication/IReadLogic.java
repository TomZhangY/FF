package ff.communication;

import java.io.IOException;
import java.io.InputStream;

public interface IReadLogic {
	
	public int read(InputStream is, byte[] buff) throws IOException;

	public byte[] read(InputStream is) throws IOException;
}
