package ff.communication.impl;

import ff.communication.IOperation;
import ff.communication.IPacket;

public abstract class AbstractReceiver implements Runnable {

	protected final IOperation operation;
	
	protected final IPacket packet;
	
	public AbstractReceiver(IOperation operation, IPacket packet){
		this.operation = operation;
		this.packet = packet;
	}
	
	public IPacket getPacket(){
		return packet;
	}
}
