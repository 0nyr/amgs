package amgs.levels;

import amgs.*;
import amgs.utils.*;

import java.awt.*;
import java.awt.image.*;
import java.util.*;

public class LevelGenerator {

    private ArrayList<String> colorCodes; // contains color code where to get random elements

    public static final int BASE_RADIUS = 5; // in tile
    public static final int BORDER_VOID = 12;
    public static final int OVAL_CORRECTOR = 1;

    private Handler handler;

    public LevelGenerator(Handler handler) {
        this.handler = handler;
        initColorCodeElement();
    }

    public BufferedImage generateSpriteMapDependingOnWaveNumber() {
        // BufferedImage manip : https://youtu.be/iAdnFhNS110
        int waveNumber = handler.getWorld().getGameHud().getWave();
        // init sprite map
        int circleDiameter = (BASE_RADIUS + waveNumber)*2 + OVAL_CORRECTOR;
        int middle = BASE_RADIUS + waveNumber + BORDER_VOID;
        int middleCorrected = middle - OVAL_CORRECTOR;
        int size = (middle)*2;
        BufferedImage spriteMap = new BufferedImage(
            size, size, BufferedImage.TYPE_INT_ARGB);

        // fill the sprite map with void
        Graphics gSpriteMap = spriteMap.createGraphics();
        gSpriteMap.setColor(Color.decode(ColorCode.CC_EMPTY_ZONE));
        gSpriteMap.fillRect(0, 0, size, size);

        // draw center asteroid ground
        gSpriteMap.setColor(Color.decode(ColorCode.CC_GROUND));
        System.out.println("circleDiameter = "+circleDiameter);
        gSpriteMap.fillOval(BORDER_VOID - 1, BORDER_VOID -1, 
            circleDiameter, circleDiameter);

        // draw center blank zone for landing zone
        /*
        gSpriteMap.setColor(Color.decode(ColorCode.CC_EMPTY_ZONE));
        gSpriteMap.fillRect(middleCorrected - 1, middleCorrected - 1, 4, 3);
        
        // draw pixel for landing zone
        gSpriteMap.setColor(Color.decode(ColorCode.CC_LANDING_ZONE));
        gSpriteMap.fillRect(middleCorrected - 1, middleCorrected - 1, 1, 1);
        */

        // draw center ground zone with health crate in the center
        gSpriteMap.setColor(Color.decode(ColorCode.CC_GROUND));
        gSpriteMap.fillRect(middleCorrected - 1, middleCorrected - 1, 3, 3);
        gSpriteMap.setColor(Color.decode(ColorCode.CC_HEALTH_CRATE));
        gSpriteMap.fillRect(middleCorrected, middleCorrected, 1, 1);

        

        // draw random walls, gemmes, blobs, healthcrate, rocks
        // int[] spriteMapPixels = spriteMap.getRGB(0, 0, size, size, new int[size*size], 0, size);
        int[] spriteMapPixels = ( (DataBufferInt) spriteMap.getRaster().getDataBuffer() ).getData();
        int colorCodeGround = Color.decode(ColorCode.CC_GROUND).getRGB();
        Random rand = new Random();
        int randInt;
        for(int i = 0; i < spriteMapPixels.length; i++) {
            if(spriteMapPixels[i] == colorCodeGround) {
                randInt = rand.nextInt(100);
                if(randInt < 14) {
                    spriteMapPixels[i] = Color.decode(getRandomColorCode()).getRGB();
                }
            }
        }

        // clear area around player
        gSpriteMap.setColor(Color.decode(ColorCode.CC_GROUND));
        gSpriteMap.fillRect(middleCorrected - 1, middleCorrected + 3 - 1, 3, 3);

        // draw pixel for player position
        gSpriteMap.setColor(Color.decode(ColorCode.CC_PLAYER));
        gSpriteMap.fillRect(middleCorrected, middleCorrected + 3, 1, 1);
    
        // end generation
        gSpriteMap.dispose();
        Utils.saveBufferedImageAsPNG(spriteMap);
        return spriteMap;
    }

    private void initColorCodeElement() {
        colorCodes = new ArrayList<String>();
        colorCodes.add(ColorCode.CC_GEMME);
        colorCodes.add(ColorCode.CC_GEMME);
        colorCodes.add(ColorCode.CC_HEALTH_CRATE);
        colorCodes.add(ColorCode.CC_ROCK);
        colorCodes.add(ColorCode.CC_ROCK);
        colorCodes.add(ColorCode.CC_WALL);
        colorCodes.add(ColorCode.CC_WALL);
        colorCodes.add(ColorCode.CC_BLOB);
        colorCodes.add(ColorCode.CC_BLOB);
        colorCodes.add(ColorCode.CC_BLOB);
    }

    private String getRandomColorCode() {
        // https://www.baeldung.com/java-random-list-element
        Random rand = new Random();
        String randomColorCode = colorCodes.get(rand.nextInt(colorCodes.size()));
        return randomColorCode;
    }

}
