package amgs.gfx;

import java.awt.image.*;

public class SpriteSheet {

	private BufferedImage sheet;
	
	private int pixelMultiply, spriteWidth, spriteHeight;
	
	public SpriteSheet(BufferedImage sheet, int originalSpriteSize, int pixelMultiply) {
		this.sheet = sheet;
		this.pixelMultiply = pixelMultiply;
		this.spriteWidth = originalSpriteSize * pixelMultiply;
		this.spriteHeight = originalSpriteSize * pixelMultiply;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		return sheet.getSubimage(x, y, width, height);
	}
	
	// getters & setters
	
	public int getSpriteWith() {
		return spriteWidth;
	}
	
	public int getSpriteHeight() {
		return spriteHeight;
	}

	public int getPixelMultiply() {
		return pixelMultiply;
	}
	
}
