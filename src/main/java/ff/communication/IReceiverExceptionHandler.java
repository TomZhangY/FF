package ff.communication;


public interface IReceiverExceptionHandler {

	public void handleException(IPacket packet, Throwable t);
}
