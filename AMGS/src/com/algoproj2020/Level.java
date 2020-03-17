package com.algoproj2020;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import fr.projetinsa.graphics.Tile;
import fr.projetinsa.éléments.Entity;
 
public class Level {
	private byte[] tiles;
	/* We store tiles just like we stored pixels color. 
	 * Each pixel is covered by a tile, so each element of the array (index x+y*width) corresponds to the type of the tile that covers the pixel in question (index x+y*width).*/
	public int width; // The width of the level.
	public int height; // The width of the level.
	public ArrayList<Entity> entities =new ArrayList<Entity>(); // We store the entities of the level in a ArrayList.
	private String imagePath; // The path used to access the background image.
    private BufferedImage image;// BufferedImage to store the image located at imagePath.
    
	/**
	 * Generate the level.
	 * @param imagePath the path of the image used to generate the level.
	 */
	public Level(String imagePath) {
		 if (imagePath != null) {// If a path is given, then 
	            this.imagePath = imagePath;
	            this.loadLevelFromFile();// we load the image from the input path.
	        } else {// Otherwise the level is randomly generated.
	            this.width = 64;
	            this.height = 64;
	            tiles = new byte[width * height];
	            this.generateLevel();
	        }
	    }
	/**
	 * Get the image used to build the level.
	 */
	 private void loadLevelFromFile() {
	        try {
	            this.image = ImageIO.read(new File(imagePath));// We store the image in a BufferImage.
	            this.width = this.image.getWidth();// We adapt the dimensions of the level to fit the one of the image.
	            this.height = this.image.getHeight();
	            tiles = new byte[width * height];
	            this.loadTiles();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 /**
	  *Converts the image into an RGB array of pixels and the path to associate each pixel of the image with a tile type.
	  */
	 private void loadTiles() {
		 int[] tileColours = this.image.getRGB(0, 0, width, height, null, 0, width);// We convert the background image into an RGB array of pixels. 
		 for (int y = 0; y < height; y++) {
			 for (int x = 0; x < width; x++) {
				 tileCheck: for (Tile t : Tile.tiles) {
					 if (t != null && t.getLevelColour() == tileColours[x + y * width]) {
						 /*We go through the level pixel by pixel and
						  *according to the color of each pixel, we associate a type of tile to each pixel.*/
						 this.tiles[x + y * width] = t.getId();
						 break tileCheck;
					 }
				 }
			 }
		 }
	 }
	 
	 private void saveLevelToFile() {
	    	try {
	    		ImageIO.write(image, "png", new File(Level.class.getResource(this.imagePath).getFile()));
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    }
	 /**
	  * Modifies one tile of the level.
	  * @param x the x coordinate of the new tile
	  * @param y the y coordinate of the new tile
	  * @param newTile the new tile
	  */
	 public void alterTile(int x, int y, Tile newTile) {
		 this.tiles[x + y * width] = newTile.getId();// We modify the type of the tile of coordinates (x,y).
		 image.setRGB(x, y, newTile.getLevelColour());// We modify the color of the of coordinates (x,y).
	 }
	 
	 public void generateLevel() {
		for(int y=0;y<height;y++) {
			for(int x=0;x<width;x++) {
				if(x*y%10<7) {
					tiles[x+y*width]=Tile.GRASS.getId();
				}else {
					tiles[x+y*width]=Tile.STONE.getId();
				}
			}
		}
	}
	 public synchronized List<Entity> getEntities() {
		 return this.entities;
	 }
	/**
	 * Updates all the entities and tiles of the level.
	 */
	public void update() {
		for (Entity e : getEntities()) {
            e.update();
        }

        for (Tile t : Tile.tiles) {
            if (t == null) {
                break;
            }
            t.update();// méthode à définir, pour l'instant il ne se passe rien
        }
    }
		
	/**
	 * 
	 * @param screen
	 */
	public void renderEntities(Screen screen) {
		for (Entity e: entities) {
			e.render(screen);
		}
	}
	public void rendertile(Screen screen, int xOffset, int yOffset) {//checked
		 if (xOffset < 0)
	            xOffset = 0;
	        if (xOffset > ((width << 3) - screen.width))
	            xOffset = ((width << 3) - screen.width);
	        if (yOffset < 0)
	            yOffset = 0;
	        if (yOffset > ((height << 3) - screen.height))
	            yOffset = ((height << 3) - screen.height);

	        screen.setOffset(xOffset, yOffset);

	        for (int y = (yOffset >> 3); y < (yOffset + screen.height >> 3) + 1; y++) {
	            for (int x = (xOffset >> 3); x < (xOffset + screen.width >> 3) + 1; x++) {
	                getTile(x, y).render(screen, this, x << 3, y << 3);
	            }
	        }
	    }
	
	public Tile getTile(int x, int y) {
        if (0 > x || x >= width || 0 > y || y >= height)
            return Tile.VOID;
        return Tile.tiles[tiles[x + y * width]];
    }
	public synchronized void addEntity(Entity entity) {
        this.getEntities().add(entity);
    }

}
