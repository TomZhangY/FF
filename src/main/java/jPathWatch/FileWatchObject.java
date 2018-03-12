package jPathWatch;

import name.pachler.nio.file.WatchEvent.Kind;

public class FileWatchObject {
	private Kind eventKind;
	private String fileName;
	private String filePath;

	public FileWatchObject(Kind eventKind, String fileName, String filePath) {
		super();
		this.eventKind = eventKind;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	public Kind getEventKind() {
		return eventKind;
	}

	public void setEventKind(Kind eventKind) {
		this.eventKind = eventKind;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public String toString() {
		return "FileWatchObject [eventKind=" + eventKind + ", fileName=" + fileName + ", filePath=" + filePath + "]";
	}
}
