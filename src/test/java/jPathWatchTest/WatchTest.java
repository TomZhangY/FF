package jPathWatchTest;

import jPathWatch.FileWatchListener;
import jPathWatch.FileWatchOperation;

public class WatchTest {
	public static void main(String[] args) {
		FileWatchListener fl = new FileWatchListener(new FileWatchOperation(),"D:/temp/");
		fl.start();
	}
}
