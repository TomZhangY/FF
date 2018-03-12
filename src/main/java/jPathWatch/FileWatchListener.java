package jPathWatch;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import name.pachler.nio.file.ClosedWatchServiceException;
import name.pachler.nio.file.FileSystems;
import name.pachler.nio.file.Path;
import name.pachler.nio.file.Paths;
import name.pachler.nio.file.StandardWatchEventKind;
import name.pachler.nio.file.WatchEvent;
import name.pachler.nio.file.WatchKey;
import name.pachler.nio.file.WatchService;

/*
 * JPathWatch 可以根据不同的平台来监控文件 ,这里监控的是一个目录下文件的增删改
 * 注意，这里像 modify 一次文件，有时会触发2次同样的modify事件，有时会触发一次，有时先触发一次空的modify，再触发一次真正的modify
 */
public class FileWatchListener {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private FileWatchOperation operation;
//	private String watchDir  = "C:/Program Files/StoreLine/WinPOS/TEMP/";
	private String watchDir  = "D:/temp/";

	private WatchService watchService;
	private Path watchedPath;

	private boolean watchFlag;
	
	public FileWatchListener(FileWatchOperation operation, String watchDir) {
		this.operation = operation;
		this.watchDir = watchDir;
		this.watchFlag = false;

		watchService = FileSystems.getDefault().newWatchService();
		watchedPath = Paths.get(watchDir);
	}

	public void start() {
		watchFlag = true;
		WatchKey key = null;
		try {
			key = watchedPath.register(watchService, StandardWatchEventKind.ENTRY_CREATE, StandardWatchEventKind.ENTRY_DELETE, StandardWatchEventKind.ENTRY_MODIFY);
		} catch (UnsupportedOperationException e) {
			logger.error("file watching not supported!", e);
		} catch (IOException e) {
			logger.error("I/O errors", e);
		}
		String dirStr = new File(watchDir).getAbsolutePath();
		boolean modifyFlag = false;

		while (watchFlag) {
			WatchKey signalledKey = null;
			try {
				signalledKey = watchService.take();
			} catch (ClosedWatchServiceException e) {
				logger.error(e.getMessage(), e);
			} catch (InterruptedException e) {
				logger.error(e.getMessage(), e);
			}

			List<WatchEvent<?>> list = signalledKey.pollEvents();
			signalledKey.reset();
			
			
			for (WatchEvent e : list) {

				Path context = (Path) e.context();
				String message = "";

				if (e.kind() == StandardWatchEventKind.ENTRY_CREATE) {
					message = dirStr + context.toString() + " created";

				} else if (e.kind() == StandardWatchEventKind.ENTRY_DELETE) {
					message = dirStr + context.toString() + " deleted";

				} else if (e.kind() == StandardWatchEventKind.ENTRY_MODIFY) {
//					if (modifyFlag == false) {
//						modifyFlag = true;
//						continue;
//					} else {
						message = dirStr + context.toString() + " modified";
//						modifyFlag = false;
//					}
				} else if (e.kind() == StandardWatchEventKind.OVERFLOW) {
					message = "OVERFLOW: more changes happened than we could retreive";
					continue;
				}
				System.out.println(message + "message");
				FileWatchObject watchObj = new FileWatchObject(e.kind(), context.toString(), dirStr + File.separator + context.toString());
				logger.trace("[FileWatchListener] handling " + watchObj);
				if (operation != null) {
					operation.operate(watchObj);
				} else {
					logger.error("FileWatchOperation is null, setOperation() is required.");
				}
			}
		}

	}

	public void stop() {
		this.watchFlag = false;
	}
}
