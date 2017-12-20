package util.qrcode;

import java.io.File;

public interface QRCodeImage {
	public File createImage(String contents, int width, int height, String imgPath);
	
	public byte[] createImageToByte(String contents, int width, int height, String imgPath);
}
