package ff.communication.impl;

import java.io.Serializable;

public class CommunicationConfigurator implements Serializable {

	public static final String KEY = "CommunicationConfigurator";

	private static final long serialVersionUID = -3805029162894682557L;

	private int clientMaximum;
	private long tcpListenerSleepTime = 10;
	private long tcpServerSleepTime = 500;
	private int tcpSocketIOSize;
	private int timeout;
	private int port;
	private String address;

	public CommunicationConfigurator() {
	}

	public int getClientMaximum() {
		return clientMaximum;
	}

	public void setClientMaximum(int clientMaximum) {
		this.clientMaximum = clientMaximum;
	}

	public long getTcpListenerSleepTime() {
		return tcpListenerSleepTime;
	}

	public void setTcpListenerSleepTime(long tcpListenerSleepTime) {
		this.tcpListenerSleepTime = tcpListenerSleepTime;
	}

	public long getTcpServerSleepTime() {
		return tcpServerSleepTime;
	}

	public void setTcpServerSleepTime(long tcpServerSleepTime) {
		this.tcpServerSleepTime = tcpServerSleepTime;
	}

	public int getTcpSocketIOSize() {
		return tcpSocketIOSize;
	}

	public void setTcpSocketIOSize(int tcpSocketIOSize) {
		this.tcpSocketIOSize = tcpSocketIOSize;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
