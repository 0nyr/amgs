package amgs.levels;

import java.awt.*;

public class ColorCode {

    // tiles color code
    public static final String CC_EMPTY_ZONE = "0x000000";
    public static final String CC_LANDING_ZONE = "0x7c80a6";
    public static final String CC_GROUND = "0x7b3d20";
    public static final String CC_WALL = "0xe8d4c1";

    // items color code (on default walkable tile)
    public static final String CC_GEMME = "0x3687e1";
    public static final String CC_HEALTH_CRATE = "0xdd3636";

    // entities color code (on default walkable tile)
    public static final String CC_PLAYER = "0xfa7242";
    public static final String CC_BLOB = "0xb31c4a";
    public static final String CC_ROCK = "0x998e87";

    // test on how to use color code to convert it to int and Color
    public static Color realC = Color.decode(CC_EMPTY_ZONE);
    public static int c = Color.decode(CC_EMPTY_ZONE).getRGB();
}