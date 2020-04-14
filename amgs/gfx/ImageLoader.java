package amgs.gfx;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class ImageLoader {
	
	public static BufferedImage loadImageResizedPixelMultiply(String path, int pixelMultiply) {
		BufferedImage img = loadImage(path);
		BufferedImage imgResized = resizeBufferedImage(img, pixelMultiply);
		return imgResized;
	}

	public static BufferedImage loadImage(String path){
		try {
			// here, use ImageLoader instead.class of getClass() as we are in static context
			// more info: https://programming.guide/java/difference-between-class-and-getclass.html
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	private static BufferedImage resizeBufferedImage(BufferedImage img, int pixelMultiply) {
		int img_width = img.getWidth();
		int img_height = img.getHeight();
		// WARN: need to go to Image then convert back to BufferedImage
		Image resizedImg = img.getScaledInstance(
			img_width * pixelMultiply,
			img_height * pixelMultiply,
			Image.SCALE_SMOOTH);
		BufferedImage finalResizedImg = convertImagetoBufferedImage(resizedImg);
		return finalResizedImg;
	}
	
	public static BufferedImage convertImagetoBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			// do a type cast if possible
			return (BufferedImage) img;
		} else {
			// Create a buffered image with transparency
			BufferedImage bimage = new BufferedImage(img.getWidth(null), 
				img.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);
			// Draw the image on to the buffered image
			Graphics2D bGr = bimage.createGraphics();
			bGr.drawImage(img, 0, 0, null);
			bGr.dispose();
			return bimage;
		}
	}
	
 }
