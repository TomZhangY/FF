package util.sendkey.jna;

import java.io.IOException;

public class JNAHelper {
	private W32API.HWND hwnd ;

	public W32API.HWND getHwnd() {
		return hwnd;
	}

	public void setHwnd(W32API.HWND hwnd) {
		this.hwnd = hwnd;
	}
	
	public JNAHelper(String title) {
//		"1.txt - 记事本"
		W32API.HWND Phwnd = User32.INSTANCE.FindWindow(0, title);
		// 创建子窗口(之前是只用父窗口，但是没作用)
		W32API.HWND hwnd = User32.INSTANCE.FindWindowEx(Phwnd,0,0,0);
		
		// 下面是主动显示该窗口
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
	
	public void sendKey(int keyNum) {
		User32.INSTANCE.PostMessage(hwnd, 256, keyNum, 0);
	}
	
	public void sendKey(String key) {
		Integer keyNum = VKCode.KeyMap.get(key);
		if(keyNum != null) {
			sendKey(keyNum);
		}
	}
}
