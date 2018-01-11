package ff.communication.impl;

import ff.communication.IPacket;
import ff.communication.IReceiverExceptionHandler;

public class ExceptionHandler implements IReceiverExceptionHandler {

	public void handleException(IPacket packet, Throwable t) {
		packet.getSender().operate("");
	}

}
