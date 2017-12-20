package util.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

import util.qrcode.QRCodeImage;

public class QRCodeEncoder implements QRCodeImage{

	private static String encoding = "UTF-8";

	public File createImage(String contents, int width, int height, String imgPath) {
		// 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大
		if( width <= 0 || width >=15){
			width = 7; 
		}
		int size = width ;
		// 设置偏移量，不设置可能导致解析出错  
        int pixoff = 2;  
        int le = 300 ;
		Qrcode testQrcode = new Qrcode();
		testQrcode.setQrcodeErrorCorrect('L');
		testQrcode.setQrcodeEncodeMode('B');
		testQrcode.setQrcodeVersion(size);
		String testString = contents;
		try {
			byte[] d = testString.getBytes(encoding);

			// 图片尺寸
			int imgSize = 67 + 12 * (size - 1);

			BufferedImage bi = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_BYTE_BINARY);
			Graphics2D g = bi.createGraphics();
			g.setBackground(Color.WHITE);
			g.clearRect(0, 0, imgSize, imgSize);
			g.setColor(Color.BLACK);
			// if (d.length > 0 && d.length < 800) {
			// boolean[][] s = testQrcode.calQrcode(d);
			// for (int i = 0; i < s.length; i++) {
			// for (int j = 0; j < s.length; j++) {
			// if (s[j][i]) {
			// g.fillRect(j * 4 + 2, i * 4 + 2, 4, 4);
			// }
			// }
			// }
			// }

			if (d.length > 0 && d.length < le) {
				boolean[][] codeOut = testQrcode.calQrcode(d);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							g.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			}
			g.dispose();
			bi.flush();
			File f = new File(imgPath);
			if (!f.exists()) {
				f.createNewFile();
			}
			ImageIO.write(bi, "bmp", f);
			return f;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public byte[] createImageToByte(String contents, int width, int height, String imgPath) {
		File f = createImage(contents,width,height,imgPath);
		ByteArrayOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			if(f.exists()) {
				bos = new ByteArrayOutputStream((int) f.length());  
				bis = new BufferedInputStream(new FileInputStream(f));
				byte[] bs = new byte[1024];
				int b ;
				while((b = bis.read(bs)) != -1 ) {
					bos.write(bs, 0, b);
				}
				return bos.toByteArray();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
            try {  
                bis.close();
                bos.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
		return null;
	}

	public static void main(String[] args) throws Exception {
		new QRCodeEncoder().createImage("yHVKVTe3z01T1JCfTSh6rVqAr%2FwzX%2BIzq1UqqrCmCZ%2F4c8%3D", 120, 120, "D://image4.bmp");
//		QRCodeEncoderTest.create_image("http://eservice.watsons.com.cn", 120, 120, "D://image4.bmp");
	} // end main

}
