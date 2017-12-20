package qrcodeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import util.qrcode.QRCodeEncoder;

public class QRCodeEncoderTest {

	@Test
	public void testCreateImage() {
		File f = new QRCodeEncoder().createImage("helloword", 120, 120, "D://image4.bmp");
		assertTrue(f.exists());
	}

	@Test
	public void testCreateImageToByte() {
		String fs = "D://image4.bmp";
		File f = new File(fs);
		byte[] bytes = new QRCodeEncoder().createImageToByte("helloword", 120, 120, fs);
		System.out.println("file length =" + f.length());
		assertEquals(f.length(), bytes.length);
		
	}

}
