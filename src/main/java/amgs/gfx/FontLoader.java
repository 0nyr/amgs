package amgs.gfx;

import java.awt.*;
import java.io.*;

public class FontLoader {
	
	public static Font loadFontToSize(String path, float size) {
		Font newFont = loadFont(path);
		Font resizedFont = resizeFont(newFont, size);
		return resizedFont;
	}
	
	private static Font loadFont(String path) {
		Font loadFont;
		try {
			loadFont = Font.createFont(Font.TRUETYPE_FONT, FontLoader.class.getResourceAsStream(path));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
			System.out.println(e);
			loadFont = new Font("Arial", Font.BOLD, 24);
		}
		return loadFont;
	}
	
	private static Font resizeFont(Font givenFont, float size) {
		return givenFont.deriveFont(Font.PLAIN, size);
	}

}
