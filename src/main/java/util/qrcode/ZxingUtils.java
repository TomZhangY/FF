package util.qrcode;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import util.qrcode.QRCodeImage;

/**
 * Created by liuyangkly on 15/6/27.
 * 使用了google zxing作为二维码生成工具
 */
public class ZxingUtils implements QRCodeImage{

    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        if (!ImageIO.write(image, format, file)) {
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    /** 将内容contents生成长为width，宽为width的图片，图片路径由imgPath指定
     * @throws Exception 
     */
	public File createImage(String contents, int width, int height, String imgPath){
		try {
            Map<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF8");
            
            hints.put(EncodeHintType.MARGIN, 0);
			BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);

            File imageFile = new File(imgPath);
			writeToFile(bitMatrix, "png", imageFile);
			return imageFile;
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
		String contents ="http://eservice.watsons.com.cn/?d4c1accae9d038c9d299919d00ecef18dc16aa1a39027993ad6b1332f4e35f63e9bb5343b1f7193b9a7605272db829ccf9bc6c7bd8dd8f18363f5a6669876afe6d4a3896bc9696542d8c3a3c71cd41b03a928b9f50313ed39717b3fe69bb686899bf03843271d012a0615a6f4149bd1c156cd4163cf8ce4de2e725a87d09cf8b";
		 new ZxingUtils().createImage(contents, 300,300, "conf/img.png");
	}
}
