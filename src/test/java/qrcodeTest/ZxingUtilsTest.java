package qrcodeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import util.qrcode.ZxingUtils;

public class ZxingUtilsTest {

	@Test
	public void testCreateImage() {
		File f = new ZxingUtils().createImage("helloword", 120, 120, "D://image4.bmp");
		assertTrue(f.exists());
	}

	@Test
	public void testCreateImageToByte() {
		String fs = "D://image5.bmp";
		File f = new File(fs);
		byte[] bytes = new ZxingUtils().createImageToByte("helloword", 120, 120, fs);
		System.out.println("file length =" + f.length());
		assertEquals(f.length(), bytes.length);
	}

}
