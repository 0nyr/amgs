package fr.projetinsa.graphics;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public String path;
	public int width;
	public int height;
	
	public int pixels[];
	/**
	 * Constructor of the class
	 * @param path the path used to access the image
	 */
	public SpriteSheet(String path) {
		BufferedImage image = null;
		try { 
			image=ImageIO.read(new File(path));// we store the spritesheet in a BufferedImage
		}catch(IOException e){
			e.printStackTrace();
		}
		if(image==null) {return;}
		this.path=path;
		this.width=image.getWidth();
		this.height=image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);/*We convert the spritesheet into an RGB array
		Each pixels of the image will be represented by an integer of the form 0xAARRGGBB (hexadecimal representation)*/
		
		for(int i=0;i<pixels.length;i++) {
			pixels[i]=(pixels[i] & 0xFFFFFF)/64;
		//*On représente notre image sous la forme d'un tableau de pixels ne contenant que les quatre couleurs suivantes:
		//*255/3*0,255/3*0,255/3*0 (BLACK) 255/3*1,255/3*1,255/3*1 (DARK GREY) 255/3*2,255/3*2,255/3*2 (LIGHT GREY) 255,255,255 (WHITE) 
		
		}	
		
	}
}