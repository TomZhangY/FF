package jPathWatchTest;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.commons.io.FileUtils;



public class Sample {

    private WatchService watcher;

    private Path path;

    public Sample(Path path) throws IOException {
        this.path = path;
        watcher = FileSystems.getDefault().newWatchService();
        this.path.register(watcher, OVERFLOW, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
    }

    public void handleEvents() throws InterruptedException {
        // start to process the data files
        while (true) {
            // start to handle the file change event
            final WatchKey key = watcher.take();

            for (WatchEvent<?> event : key.pollEvents()) {
                // get event type
                final WatchEvent.Kind<?> kind = event.kind();

                // get file name
                @SuppressWarnings("unchecked")
                final WatchEvent<Path> pathWatchEvent = (WatchEvent<Path>) event;
                final Path fileName = pathWatchEvent.context();

                if (kind == ENTRY_CREATE) {
                    // 说明点1
                    // create a new thread to monitor the new file
                    new Thread(new Runnable() {
                        public void run() {
                            File file = new File(path.toFile().getAbsolutePath() + "/" + fileName);
                            boolean exist;
                            long size = 0;
                            long lastModified = 0;
                            int sameCount = 0;
                            while (exist = file.exists()) {
                                // if the 'size' and 'lastModified' attribute keep same for 3 times,
                                // then we think the file was transferred successfully
                                if (size == file.length() && lastModified == file.lastModified()) {
                                    if (++sameCount >= 3) {
                                        break;
                                    }
                                } else {
                                    size = file.length();
                                    lastModified = file.lastModified();
                                }
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    return;
                                }
                            }
                            // if the new file was cancelled or deleted
                            if (!exist) {
                                return;
                            } else {
                                // update database ...
                            } 
                        }
                    }).start();
                } else if (kind == ENTRY_DELETE) {
                    // todo
                } else if (kind == ENTRY_MODIFY) {
                	System.out.println("modify");
                	 File file = new File(path.toFile().getAbsolutePath() + "/" + fileName);
                	 try {
						String content = FileUtils.readFileToString(file).trim();
						System.out.println(content);
					} catch (IOException e) {
						e.printStackTrace();
					}
                } else if (kind == OVERFLOW) {
                    // todo
                }
            }

            // IMPORTANT: the key must be reset after processed
            if (!key.reset()) {
                return;
            }
        }
    }

    public static void main(String args[]) throws IOException, InterruptedException {
        new Sample(Paths.get("D:/temp/")).handleEvents();
    }
}
