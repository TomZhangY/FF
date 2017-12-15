package util.sendkey.jna;

import java.io.IOException;

/**
 * 对记事本自动运行一系列按键
 * @author Tom
 *
 */
public class JNAT1 {
	/** * @param args */

	public static void main(String[] args) {
		W32API.HWND hwnd = User32.INSTANCE.FindWindow(0, "1.txt - 记事本");
//		W32API.HWND hwnd = User32.INSTANCE.FindWindow(0, "计算器");
//		W32API.HWND hwnd = User32.INSTANCE.FindWindow(0, "C:\\Users\\Tom\\Desktop\\1.txt - Notepad++");

		W32API.HWND childHwnd = User32.INSTANCE.FindWindowEx(hwnd,0,0,0);
		// User32.INSTANCE.SetForegroundWindow(hwnd);
		for (int i = 0; i < 6; i++) {
//			 keyPress(82);
//			 System.out.println(User32.INSTANCE.PostMessage(hwnd, 82, 0,0));
//			User32.INSTANCE.PostMessage(hwnd, 256, 82, 0);
			 backKeyPress(childHwnd,50);
			 backKeyRelease(childHwnd,82);
		}
		if (hwnd != null) {
			// 显示该程序
			User32.INSTANCE.ShowWindow(hwnd, 9);
			User32.INSTANCE.SetForegroundWindow(hwnd);
		} else {
			try {
				System.out.println(" can't find the window !!");
				// 打开笔记本
				Runtime.getRuntime().exec("NotePad.exe");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static void backKeyPress(W32API.HWND hwnd, int keyNum) {
		User32.INSTANCE.PostMessage(hwnd, 256, keyNum, 0);
	}

	public static void backKeyRelease(W32API.HWND hwnd, int keyNum) {
		User32.INSTANCE.PostMessage(hwnd, 257, keyNum, 0);
	}
	
	public static void keyPress(int keyNum) {
		User32.INSTANCE.keybd_event(keyNum, 0, 0, 0);
		User32.INSTANCE.keybd_event(keyNum, 0, "KEYEVENTF_KEYUP", 0);
	}
}