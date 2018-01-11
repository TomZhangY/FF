package util.sendkey.jna;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VKCode {

	public static final int KEY_A = 0x41;
	public static final int KEY_B = 0x42;
	public static final int KEY_C = 0x43;
	public static final int KEY_D = 0x44;
	public static final int KEY_E = 0x45;
	public static final int KEY_F = 0x46;
	public static final int KEY_G = 0x47;
	public static final int KEY_H = 0x48;
	public static final int KEY_I = 0x49;
	public static final int KEY_J = 0x4A;
	public static final int KEY_K = 0x4B;
	public static final int KEY_L = 0x4C;
	public static final int KEY_M = 0x4D;
	public static final int KEY_N = 0x4E;
	public static final int KEY_O = 0x4F;
	public static final int KEY_P = 0x50;
	public static final int KEY_Q = 0x51;
	public static final int KEY_R = 0x52;
	public static final int KEY_S = 0x53;
	public static final int KEY_T = 0x54;
	public static final int KEY_U = 0x55;
	public static final int KEY_V = 0x56;
	public static final int KEY_W = 0x57;
	public static final int KEY_X = 0x58;
	public static final int KEY_Y = 0x59;
	public static final int KEY_Z = 0x5A;

	public static final int KEY_0 = 0x30;
	public static final int KEY_1 = 0x31;
	public static final int KEY_2 = 0x32;
	public static final int KEY_3 = 0x33;
	public static final int KEY_4 = 0x34;
	public static final int KEY_5 = 0x35;
	public static final int KEY_6 = 0x36;
	public static final int KEY_7 = 0x37;
	public static final int KEY_8 = 0x38;
	public static final int KEY_9 = 0x39;

	public static final int F1 = 0x70;
	public static final int F2 = 0x71;
	public static final int F3 = 0x72;
	public static final int F4 = 0x73;
	public static final int F5 = 0x74;
	public static final int F6 = 0x75;
	public static final int F7 = 0x76;
	public static final int F8 = 0x77;
	public static final int F9 = 0x78;
	public static final int F10 = 0x79;
	public static final int F11 = 0x7A;
	public static final int F12 = 0x7B;

	public static final int ESC = 0x1B;
	public static final int ENTER = 0x0D;
	public static final int BACKSPACE = 0x08;
	public static final int CAPSLOCK = 0x14;
	public static final int CTRL = 0xA2;
	public static final int SHIFT = 0xA0;
	public static final int ALT = 0xA4;
	public static final int LEFT = 0x25;
	public static final int UP = 0x26;
	public static final int RIGHT = 0x27;
	public static final int DOWN = 0x28;

	/** Key [:;] */
	public static final int OEM_1 = 0xBA;
	/** Key [?/] */
	public static final int OEM_2 = 0xBF;
	/** Key [~`] */
	public static final int OEM_3 = 0xC0;
	/** Key [{[] */
	public static final int OEM_4 = 0xDB;
	/** Key [|\] */
	public static final int OEM_5 = 0xDC;
	/** Key [}]] */
	public static final int OEM_6 = 0xDD;
	/** Key ["'] */
	public static final int OEM_7 = 0xDE;

	public static final Map<String, Integer> KeyMap;

	static {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("A", KEY_A);
		map.put("B", KEY_B);
		map.put("C", KEY_C);
		map.put("D", KEY_D);
		map.put("E", KEY_E);
		map.put("F", KEY_F);
		map.put("G", KEY_G);
		map.put("H", KEY_H);
		map.put("I", KEY_I);
		map.put("J", KEY_J);
		map.put("K", KEY_K);
		map.put("L", KEY_L);
		map.put("M", KEY_M);
		map.put("N", KEY_N);
		map.put("O", KEY_O);
		map.put("P", KEY_P);
		map.put("Q", KEY_Q);
		map.put("R", KEY_R);
		map.put("S", KEY_S);
		map.put("T", KEY_T);
		map.put("U", KEY_U);
		map.put("V", KEY_V);
		map.put("W", KEY_W);
		map.put("X", KEY_X);
		map.put("Y", KEY_Y);
		map.put("Z", KEY_Z);
		map.put("0", KEY_0);
		map.put("1", KEY_1);
		map.put("2", KEY_2);
		map.put("3", KEY_3);
		map.put("4", KEY_4);
		map.put("5", KEY_5);
		map.put("6", KEY_6);
		map.put("7", KEY_7);
		map.put("8", KEY_8);
		map.put("9", KEY_9);
		map.put("F1", F1);
		map.put("F2", F2);
		map.put("F3", F3);
		map.put("F4", F4);
		map.put("F5", F5);
		map.put("F6", F6);
		map.put("F7", F7);
		map.put("F8", F8);
		map.put("F9", F9);
		map.put("F10", F10);
		map.put("F11", F11);
		map.put("F12", F12);
		map.put("ESC", ESC);
		map.put("ENTER", ENTER);
		map.put("BACKSPACE", BACKSPACE);
		map.put("CAPSLOCK", CAPSLOCK);
		map.put("CTRL", CTRL);
		map.put("SHIFT", SHIFT);
		map.put("ALT", ALT);
		map.put("LEFT", LEFT);
		map.put("UP", UP);
		map.put("RIGHT", RIGHT);
		map.put("DOWN", DOWN);
		map.put("OEM_1", OEM_1);
		map.put("OEM_2", OEM_2);
		map.put("OEM_3", OEM_3);
		map.put("OEM_4", OEM_4);
		map.put("OEM_5", OEM_5);
		map.put("OEM_6", OEM_6);
		map.put("OEM_7", OEM_7);

		KeyMap = Collections.unmodifiableMap(map);
	}

}
