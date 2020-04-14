package amgs;

public class Constants {

    // size of a normal tile after redimension
    public static final int ORIGINAL_SPRITE_SIZE = 16;
	public static final int PIXEL_MULTIPLY = 3;
    public static final int TILE_WIDTH = ORIGINAL_SPRITE_SIZE*PIXEL_MULTIPLY;
    public static final int TILE_HEIGHT = ORIGINAL_SPRITE_SIZE*PIXEL_MULTIPLY;
    
    // TESTING CONSTANTS
    // is this game run a test ?
    public static boolean isTest = false;
    public static boolean isCameraLimitedToGameArea = false;

}