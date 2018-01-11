package ff.communication;

public interface IPacket {
	
	public ISender getSender();
	
	public Object getReceived();
}
