package amgs.gfx;

import amgs.*;
import amgs.sound.SoundPlayer;

import java.awt.*;
import java.awt.image.*;

public class Assets {

	// WARN: The handler need to be initialised
	private static Handler handler;

	public static Font silkscreen28, minecraftia24, minecraftia34;
	
	// main spritesheet characteristics
	private static int width, height;
	
	// sprites
	public static BufferedImage rock, ground, wall, landingZone;
	public static BufferedImage bullet, gun, emptyZone, spaceship;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage hudBackground;
	public static BufferedImage[] button;
	public static BufferedImage[] smallGemme, healthCrate;
	public static BufferedImage[] star1;
	public static BufferedImage[] blob, krug;
	public static BufferedImage[] reticles;   // [0] can shoot, [1] shoot not possible
	public static BufferedImage[] titleScreen;
	public static BufferedImage[] controls;

	// background images
	public static BufferedImage[] immobileBackgroundStars, nebulaeBg, gasCloudsBg;

	// sounds
	public static SoundPlayer gemmePickUpSound, healthCratePickUpSound;
	public static SoundPlayer buttonHoverSound, buttonClickSound;
	public static SoundPlayer boltgunShootSound;
	public static SoundPlayer playerHurtSound;
	public static SoundPlayer krugDeathSound, blobDeathSound;

	// load every game asset once
	public static void init() {
		// fonts
		silkscreen28 = FontLoader.loadFontToSize("/res/fonts/silkscreen/slkscr.ttf", 28f);
		minecraftia24 = FontLoader.loadFontToSize("/res/fonts/minecraftia/Minecraftia-Regular.ttf", 24f);
		minecraftia34 = FontLoader.loadFontToSize("/res/fonts/minecraftia/Minecraftia-Regular.ttf", 34f);
		
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
		bullet = sheet.crop(width*2, height*2, width, height);
		gun = sheet.crop(width*1, height*2, width, height);
		emptyZone = sheet.crop(0, height*7, width, height);

		hudBackground = sheet.crop(width*8, 0, width*8, height*3);

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

		krug = new BufferedImage[2];
		krug[0] = sheet.crop(width*6, height*3, width, height);
		krug[1] = sheet.crop(width*7, height*3, width, height);

		healthCrate = new BufferedImage[6];
		healthCrate[0] = sheet.crop(width*4, height*5, width, height);
		healthCrate[1] = healthCrate[0];
		healthCrate[2] = healthCrate[0];
		healthCrate[3] = sheet.crop(width*5, height*5, width, height);
		healthCrate[4] = sheet.crop(width*6, height*5, width, height);
		healthCrate[5] = sheet.crop(width*7, height*5, width, height);

		reticles = new BufferedImage[2];
		reticles[0] = sheet.crop(0, height*3, width, height);
		reticles[1] = sheet.crop(width*1, height*3, width, height);

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

		// get suitable multiplier for cover sprites to fit the window
		int multiplier = Constants.PIXEL_MULTIPLY - 1;
		while(320*(multiplier + 1) < handler.getDisplayWidth()) {
			multiplier++;
		} 

		// title cover sprite
		titleScreen = new BufferedImage[2];
		BufferedImage titleScreenSpriteSheet = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/title_screen.png",
			multiplier);
		titleScreen[0] = titleScreenSpriteSheet.getSubimage(
			0, 0, 
			titleScreenSpriteSheet.getWidth()/2, 
			titleScreenSpriteSheet.getHeight());
		titleScreen[1] = titleScreenSpriteSheet.getSubimage(
			titleScreenSpriteSheet.getWidth()/2, 0,
			titleScreenSpriteSheet.getWidth()/2, 
			titleScreenSpriteSheet.getHeight());

		// controls cover sprite
		controls = new BufferedImage[2];
		BufferedImage controlsSpriteSheet = ImageLoader.loadImageResizedPixelMultiply(
			"/res/images/controls_anim.png",
			multiplier);
		controls[0] = controlsSpriteSheet.getSubimage(
			0, 0, 
			controlsSpriteSheet.getWidth()/2, 
			controlsSpriteSheet.getHeight());
		controls[1] = controlsSpriteSheet.getSubimage(
			controlsSpriteSheet.getWidth()/2, 0,
			controlsSpriteSheet.getWidth()/2, 
			controlsSpriteSheet.getHeight());

		// sounds
		gemmePickUpSound = new SoundPlayer("/res/sounds/items/pick_up_gemme.wav");
		healthCratePickUpSound = new SoundPlayer("/res/sounds/items/pick_up_health_crate.wav");

		buttonHoverSound = new SoundPlayer("/res/sounds/gui/button_hover_sound.wav");
		buttonClickSound =  new SoundPlayer("/res/sounds/gui/button_click_sound.wav");

		boltgunShootSound = new SoundPlayer("/res/sounds/guns/boltgun_shoot_3.wav");

		playerHurtSound = new SoundPlayer("/res/sounds/entities/player_hurt.wav");
		krugDeathSound = new SoundPlayer("/res/sounds/entities/krug_death_sound.wav");
		blobDeathSound = new SoundPlayer("/res/sounds/entities/blob_death_sound.wav");
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
			int nbOfImgAlongY = handler.getScreenHeight()/bgStarsBrush.getHeight() + 1;
			BufferedImage tempImg = new BufferedImage(
				nbOfImgAlongX*bgStarsBrush.getWidth(), 
				nbOfImgAlongY*bgStarsBrush.getHeight(), 
				BufferedImage.TYPE_INT_ARGB);
			Graphics gImg = tempImg.getGraphics();
			System.out.println("tempImg = ("+tempImg.getWidth()+", "+tempImg.getHeight()+") ");
			
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
