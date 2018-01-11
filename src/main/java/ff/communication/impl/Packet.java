package ff.communication.impl;

import java.io.Serializable;

import ff.communication.IPacket;
import ff.communication.ISender;

public class Packet implements IPacket, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5851738466936893057L;
	
//	private final String uuid;
	protected final ISender sender;
	protected final Object received;
	
	public Packet(ISender sender, Object received){
//		uuid = UUID.randomUUID().toString();
		this.sender = sender;
		this.received = received;
	}

	@Override
	public ISender getSender() {
		return sender;
	}

	@Override
	public Object getReceived() {
		return received;
	}
}
