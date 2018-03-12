package qrcodeTest;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;
import org.tap.util.swing.ext.ImagePanel;
import org.tap.util.swing.ext.TransparentPanel;

public class TTTT extends JFrame{
	private final static Logger logger = Logger.getLogger(TTTT.class);
	public final static int SCREEN_WIDTH = 800;
	public final static int SCREEN_HEIGHT = 600;
	static JPanel receiptPane ;
	static JPanel contentPane;
	
	public void make() {
		contentPane = new TransparentPanel();
		contentPane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setContentPane(contentPane);
		
		receiptPane = (ImagePanel) create(ImagePanel.class, "0,0,800,600,.//conf//bg_determine_member.png");
		receiptPane.setVisible(true);
		contentPane.add(receiptPane);
		
	}
	
	public static void main(String[] args) {
		TTTT t = new TTTT();
		t.make();
		t.setVisible(true);
		t.pack();
		t.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private static JComponent create(Class clazz, String tmp) {
		String[] values = tmp.trim().split(",");
		int x = toInt(values[0]);
		int y = toInt(values[1]);
		int w = toInt(values[2]);
		int h = toInt(values[3]);
		System.out.println(String.format("%d %d %d %d\n", x, y, w, h));

		if (clazz == ImagePanel.class) {
			String[] images = (values.length > 4) ? Arrays.copyOfRange(values, 4, values.length) : null;
			if (images != null)
				System.out.println("image is not null");
				return new ImagePanel(x, y, w, h, images);
		} 
		return null;
	}
	
	private static int toInt(String s) {
		try {
			return Integer.parseInt(s.trim());
		} catch (NumberFormatException e) {
			logger.debug(String.format("unable to parse '%s' to int-Type", s));
			return 0;
		}
	}

}
