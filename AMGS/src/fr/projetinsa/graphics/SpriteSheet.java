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
	
	public static  SpriteSheet sheet =new SpriteSheet("images/pokemon.png");
	
	public SpriteSheet(String path) {
		BufferedImage image = null;
		try { 
			image=ImageIO.read(new File(path));//problème le code n'arrive pas à &ccéder à l'image
		}catch(IOException e){
			e.printStackTrace();
		}
		if(image==null) {return;}
		this.path=path;
		this.width=image.getWidth();
		this.height=image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);//Returns an array of integer pixels in the default RGB color model from the image .
		for(int i=0;i<pixels.length;i++) {
			pixels[i]=(pixels[i] & 0xff)/64;// We use & oxff in order to be sure that all colours are opaque and we divide by 64 to limit the number of colours that are used.
		}	
	}
}