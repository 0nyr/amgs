package amgs.gfx;

import amgs.*;

import java.awt.*;
import java.awt.image.*;

public class Assets {

	// WARN: The handler need to be initialised
	private static Handler handler;

	public static Font silkscreen28, minecraftia24;
	
	// main spritesheet characteristics
	private static int width, height;
	
	// sprites
	public static BufferedImage rock, ground, wall, landingZone, crate;
	public static BufferedImage bullet, gun, ennemi, emptyZone, spaceship;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage titleScreen;
	public static BufferedImage[] button;
	public static BufferedImage[] smallGemme;
	public static BufferedImage[] star1;
	public static BufferedImage[] blob;

	// background images
	public static BufferedImage[] immobileBackgroundStars, nebulaeBg, gasCloudsBg;

	// load every game asset once
	public static void init() {
		// fonts
		silkscreen28 = FontLoader.loadFontToSize("/res/fonts/silkscreen/slkscr.ttf", 28f);
		minecraftia24 = FontLoader.loadFontToSize("/res/fonts/minecraftia/Minecraftia-Regular.ttf", 24f);
		
		// sprites
		titleScreen = ImageLoader.loadImage("/res/images/title_screen.png");
		
		// sprites from spritesheet
		SpriteSheet sheet = createMainResizedSpriteSheet();
		width = sheet.getSpriteWith();
		height = sheet.getSpriteHeight();
		
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_down[0] = sheet.crop(width*1, 0, width, height);
		player_down[1] = sheet.crop(width*1, height*1, width, height);
		player_up[0] = sheet.crop(0, 0, width, height);
		player_up[1] = sheet.crop(0, height*1, width, height);
		player_right[0] = sheet.crop(width*2, 0, width, height);
		player_right[1] = sheet.crop(width*3, 0, width, height);
		player_left[0] = sheet.crop(width*2, height*1, width, height);
		player_left[1] = sheet.crop(width*3, height*1, width, height);
		
		rock = sheet.crop(width*3, height*3, width, height);
		ground = sheet.crop(width*3, height*2, width, height);
		wall = sheet.crop(width*2, height*3, width, height);
		landingZone = sheet.crop(0, height*4, width*4, height*3);
		spaceship = sheet.crop(0, height*7, width*4, height*3);
		crate = sheet.crop(width*1, height*3, width, height);
		bullet = sheet.crop(width*2, height*2, width, height);
		gun = sheet.crop(width*1, height*2, width, height);
		ennemi = sheet.crop(0, height*2, width, height);
		emptyZone = sheet.crop(0, height*7, width, height);

		button = new BufferedImage[2];
		button[0] = sheet.crop(width*4, 0, width*4, height);
		button[1] = sheet.crop(width*4, height, width*4, height);

		smallGemme = new BufferedImage[6];
		smallGemme[0] = sheet.crop(width*4, height*2, width, height);
		smallGemme[1] = smallGemme[0];
		smallGemme[2] = smallGemme[0];
		smallGemme[3] = sheet.crop(width*5, height*2, width, height);
		smallGemme[4] = sheet.crop(width*6, height*2, width, height);
		smallGemme[5] = sheet.crop(width*7, height*2, width, height);

		blob = new BufferedImage[2];
		blob[0] = sheet.crop(width*4, height*3, width, height);
		blob[1] = sheet.crop(width*5, height*3, width, height);

		star1 = new BufferedImage[2];
		star1[0] = sheet.crop(width*4, height*4, width, height);
		star1[1] = sheet.crop(width*5, height*4, width, height);

		// background sprites
		immobileBackgroundStars = createFixedBackgroundImages(2);

		nebulaeBg = new BufferedImage[2];
		BufferedImage nebulaeBgSpriteSheet = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/nebulae_background.png", 
			Constants.PIXEL_MULTIPLY*2);
		nebulaeBg[0] = nebulaeBgSpriteSheet.getSubimage(
			0, 0, 
			nebulaeBgSpriteSheet.getWidth()/2, 
			nebulaeBgSpriteSheet.getHeight());
		nebulaeBg[1] = nebulaeBgSpriteSheet.getSubimage(
			nebulaeBgSpriteSheet.getWidth()/2, 0,
			nebulaeBgSpriteSheet.getWidth()/2, 
			nebulaeBgSpriteSheet.getHeight());

		gasCloudsBg = new BufferedImage[2];
		BufferedImage gasCloudsSpriteSheet = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/gas_clouds_background.png", Constants.PIXEL_MULTIPLY*2);
		gasCloudsBg[0] = gasCloudsSpriteSheet.getSubimage(
			0, 0, 
			gasCloudsSpriteSheet.getWidth()/2, 
			gasCloudsSpriteSheet.getHeight()/2);
		gasCloudsBg[1] = gasCloudsSpriteSheet.getSubimage(
			gasCloudsSpriteSheet.getWidth()/2, 0, 
			gasCloudsSpriteSheet.getWidth()/2, 
			gasCloudsSpriteSheet.getHeight()/2);

		// sounds
		
	}
	
	private static SpriteSheet createMainResizedSpriteSheet() {
		BufferedImage resizedSheet = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/spritesheet.png",
			Constants.PIXEL_MULTIPLY);
		SpriteSheet mainSheet = new SpriteSheet(resizedSheet, 
			Constants.ORIGINAL_SPRITE_SIZE, 
			Constants.PIXEL_MULTIPLY);
		return mainSheet;
	}

	private static BufferedImage[] createFixedBackgroundImages(int nbImages) {
		/* This function creates the fixed background images from images used as a brush 
		or tiles. The size of the image created is equal to the one of the screen */
		BufferedImage bgStarsResized = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/background_stars_spritesheet.png", Constants.PIXEL_MULTIPLY);
		
		BufferedImage[] imbBackgroundStars = new BufferedImage[nbImages];

		System.out.println(bgStarsResized.getWidth() +"   "+bgStarsResized.getHeight());

		for(int i = 0; i < nbImages; i++) {
			BufferedImage bgStarsBrush = bgStarsResized.getSubimage(
				(bgStarsResized.getWidth()/nbImages)*i, 
				0, 
				bgStarsResized.getWidth()/nbImages, 
				bgStarsResized.getHeight());
			
			int nbOfImgAlongX = handler.getScreenWidth()/bgStarsBrush.getWidth() + 1;
			int nbOfImgAlongY = handler.getScreenHeight()/bgStarsBrush.getHeight();
			BufferedImage tempImg = new BufferedImage(
				nbOfImgAlongX*bgStarsBrush.getWidth(), 
				nbOfImgAlongY*bgStarsBrush.getHeight(), 
				BufferedImage.TYPE_INT_ARGB);
			Graphics gImg = tempImg.getGraphics();
			
			for(int y = 0; y < nbOfImgAlongY; y++) {
				for(int x = 0; x < nbOfImgAlongX; x++) {
					gImg.drawImage(bgStarsBrush, bgStarsBrush.getWidth()*x, 
						bgStarsBrush.getHeight()*y, null);
				}
			}
			gImg.dispose();
			System.out.println(tempImg.getWidth() +"   "+tempImg.getHeight());

			imbBackgroundStars[i] = tempImg.getSubimage(0, 0, 
				handler.getScreenWidth(), handler.getScreenHeight());
		}

		return imbBackgroundStars;
	}

	// SETTERS
 
	public static void setHandler(Handler initHandler) {
		handler = initHandler;
	}
	
}
