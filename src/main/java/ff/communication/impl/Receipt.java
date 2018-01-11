package ff.communication.impl;

import ff.communication.IPacket;
import ff.communication.IReceipt;

public class Receipt implements IReceipt{

	private Object SendContent;
	private IPacket packet;
	
	public Receipt(IPacket packet,Object SendContent) {
		this.packet = packet;
		this.SendContent = SendContent;
	}
	
	@Override
	public Object getSendContent() {
		return SendContent;
	}
	public void setSendContent(Object sendContent) {
		SendContent = sendContent;
	}
	
	@Override
	public IPacket getPacket() {
		return packet;
	}
	public void setPacket(IPacket packet) {
		this.packet = packet;
	}
}
