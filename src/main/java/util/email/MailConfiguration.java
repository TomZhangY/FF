package util.email;

import java.io.Serializable;

public class MailConfiguration implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String smtpHost ;
	private int smtpPort=25;
	private boolean smtpNeedAuth = true ;
	private String fromMail ;
	private String userName ;
	private String password ;
	private String templateName;
	private boolean debug = false ;
	private int maxReceiver = 4000;
	
	private int connectionTimeout = 10000;
	private int readTimeout = 20000;
	
	private String contentType = "text/html";
	private String encoding = "UTF-8";
	private boolean trainingModel = false ;
	
	public boolean isTrainingModel() {
		return trainingModel;
	}
	public void setTrainingModel(boolean trainingModel) {
		this.trainingModel = trainingModel;
	}
	private String[] bccs ;
	
	public int getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getSmtpHost() {
		return smtpHost;
	}
	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}
	public boolean isSmtpNeedAuth() {
		return smtpNeedAuth;
	}
	public void setSmtpNeedAuth(boolean smtpNeedAuth) {
		this.smtpNeedAuth = smtpNeedAuth;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public int getMaxReceiver() {
		return maxReceiver;
	}
	public void setMaxReceiver(int maxReceiver) {
		this.maxReceiver = maxReceiver;
	}
	public boolean isDebug() {
		return debug;
	}
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
	public String getFromMail() {
		return fromMail;
	}
	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}
	
	public String[] getBccs() {
		return bccs;
	}
	public void setBccs(String[] bccs) {
		this.bccs = bccs;
	}
	
	public void setDefaultBccs(String bccs){
		if(bccs == null || bccs.trim().equals("")){
			return ;
		}
		String[] bccStrs = bccs.split(",");
		if(bccStrs == null || bccStrs.length== 0){
			return ;
		}
		this.bccs = bccStrs ;
	}
	
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public int getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getEncoding() {
		return encoding;
	}
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	@Override
	public String toString() {
		return "MailConfiguration [smtpHost=" + smtpHost + ", smtpPort="
				+ smtpPort + ", smtpNeedAuth=" + smtpNeedAuth + ", fromMail="
				+ fromMail + ", userName=" + userName + ", password="
				+ password + ", templateName=" + templateName + ", debug="
				+ debug + ", maxReceiver=" + maxReceiver + ", connectionTimeout="
						+ connectionTimeout+ ", readTimeout="
								+ readTimeout+ ", contentType="
										+ contentType+ ", encoding="
												+ encoding + "]";
	}
}
