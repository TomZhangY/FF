package ff.communication;

import java.io.IOException;
import java.io.OutputStream;

public interface ISender extends IOperation{
	
	public OutputStream getOutputStream() throws IOException;
	
	public void setWriteLogic(IWriteLogic writeLogic);
	
	public Object operate(Object info);
}
