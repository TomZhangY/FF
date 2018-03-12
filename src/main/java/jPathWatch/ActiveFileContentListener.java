package jPathWatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

public class ActiveFileContentListener {
	private Logger logger = Logger.getLogger(this.getClass());

	private File targetFile;

	private int delay = 1000;

	private FileChangeOperation handler;

	private ConcurrentLinkedQueue<String> queue;

	private boolean monitorFlag;

	private String loggerName = ActiveFileContentListener.class.getName();

	
	// filePath = "C:/Program Files/StoreLine/WinPOS/LOG/Eft2.log"
	public ActiveFileContentListener(String filePath, int delay, FileChangeOperation handler) {
		this.targetFile = new File(filePath);
		this.delay = delay;
		this.handler = handler;
		this.queue = new ConcurrentLinkedQueue<String>();
		this.monitorFlag = false;
	}

	public void start() {
		this.startMonitor(true);
	}

	public void stop() {
		this.stopMonitor();
	}

	private boolean isTargetExist() {
		return targetFile.exists();
	}

	private int addToQueue(String data) {
		logger.trace("data: " + data);
		enqueue(data);
		if (handler != null) {
			handler.operate(dequeue());
		}
		return queue.size();
	}

	private int enqueue(String data) {
		// synchronized (lock) {
		queue.add(data);
		// }
		return queue.size();
	}

	private String dequeue() {
		// synchronized (lock) {
		return queue.poll();
		// }
	}

	private void stopMonitor() {
		this.monitorFlag = false;
	}

	private void startMonitor(final boolean ignoreExistingContent) {
		this.monitorFlag = true;

		new Thread(new Runnable() {
			boolean reachTail = false;
			BufferedReader br = null;

			public void run() {
				try {
					String line = null;
					int state = 0;
					while (monitorFlag) {
						switch (state) {

							case -1: // File not Found / Error to read

								if (targetFile.exists()) {
									logger.info("Target file [" + targetFile + "] found, switch state to " + (++state));
								} else {
									logger.debug("Unable to read target file [" + targetFile + "], stay in stage " + state);
									try {
										Thread.sleep(delay);
									} catch (InterruptedException e) {
									}
								}
								break;

							case 0:
								if (targetFile.exists()) {
									logger.info("Target file [" + targetFile + "] exists, go for preparation, switch state to " + (++state));
								} else {
									logger.info("Target file [" + targetFile + "] NOT exists, switch state to " + (--state));
								}

								break;

							case 1: // init BufferedReader
//								if (br == null) {
								try {
									br = new BufferedReader(new InputStreamReader(new FileInputStream(targetFile.getAbsolutePath())));
									logger.info("Ready to read target file [" + targetFile + "], switch state to " + (++state));
								} catch (FileNotFoundException e) {
									logger.debug("Unable to prepare to read target file [" + targetFile + "], switch state to " + (--state), e);
								}
//								}
								break;

							case 2: // read file & add to queue
								if (br == null) {
									--state;
								} else {
//									try {
										line = null;
										while (monitorFlag) {
											if (ignoreExistingContent == true && reachTail == false) {
												boolean test = true;
												while (test) {
													try{
														test = (br.readLine()!= null) ? true:false; 
													} catch(Exception e) {
														logger.warn("[ActiveFileContentListener@startMonitor]: state 2, br.readLine()throw exception: ] " + e);
														continue;
													}
												}
												reachTail = true;
												logger.info("Target file [" + targetFile + "] already reach last line.");
											} else {
												try {
													line = br.readLine();
												} catch (Exception e) {
													logger.warn("[ActiveFileContentListener@startMonitor]: state 2 and reachTail true, br.readLine()throw exception: ] " + e);
													continue;
												}

												if (line == null) {
													try {
														Thread.sleep(delay);
													} catch (InterruptedException e) {
													}
												} else {
													addToQueue(line);
												}
											}
										}
//									} 
//									catch (IOException e) {
//										logger.warn("Unable to read target file [" + targetFile + "], switch state to " + (state = -1), e);
//									}
								}
								break;
						}
					}
				} catch (Exception e) {
					logger.warn(e);
				}
				return; // exit Runnable
			}
		}).start();
	}

	public String getLoggerName() {
		return loggerName;
	}

	public void setLoggerName(String loggerName) {
		this.loggerName = loggerName;
		logger = Logger.getLogger(loggerName);
	}

	public String getTargetFileName() {
		return this.targetFile.getName();
	}

	public boolean isMonitorFlag() {
		return this.monitorFlag;
	}
}
