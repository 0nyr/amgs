package com.algoproj2020;

import fr.projetinsa.graphics.SpriteSheet;

public class Screen {

    public static final int MAP_WIDTH = 64;
    public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

    public static final byte BIT_MIRROR_X = 0x01;
    public static final byte BIT_MIRROR_Y = 0x02;

    public int[] pixels;
    /*On repère les pixels dans un tableau 1D. On numérote chaque pixel de la manière suivante:
    -> pixel de coordonnée (n,p) correspond à la case d'indice n+p*width du tableau pixels, i.e les
    pixels sont numérotés de gauche à droite puis de haut en bas dans l'ordre croissant*/

    public int xOffset = 0;
    public int yOffset = 0;

    public int width;
    public int height;

    public SpriteSheet sheet;

    public Screen(int width, int height, SpriteSheet sheet) { 
        this.width = width;
        this.height = height;
        this.sheet = sheet;

        pixels = new int[width * height];
    }

    public void render(int xPos, int yPos, int tile, int colour, int mirrorDir, int scale) {
        xPos -= xOffset;
        yPos -= yOffset;// permet de déplacer la map lorsque le personnnage se déplace

        boolean mirrorX = (mirrorDir & BIT_MIRROR_X) > 0;
        boolean mirrorY = (mirrorDir & BIT_MIRROR_Y) > 0;

        int scaleMap = scale -1;
        int xTile = tile % 32;//tile =xtile +32*ytile avec tile l'indice de la tuile correspondante dans le tableau tiles
        int yTile = tile / 32;
        int tileOffset = (xTile << 3) + (yTile << 3) * sheet.width;//remarque: xTile << 3 revient à multiplier par 8, i.e le nombre de pixel par tile de sprite sheet.
        // si on a un sprite dont les tuiles sont en 4 pixels par tuile, alors on fera xTile<<2
        // ainsi lorsqu'on parcours le
        for (int y = 0; y < 8; y++) {
            int ySheet = y;
            if (mirrorY)
                ySheet = 7 - y;

            int yPixel = y + yPos + (y * scaleMap) - ((scaleMap << 3) / 2);

            for (int x = 0; x < 8; x++) {
                int xSheet = x;
                if (mirrorX)
                    xSheet = 7 - x;
                int xPixel = x + xPos + (x * scaleMap) - ((scaleMap << 3) / 2);
                int col = (colour >> (sheet.pixels[xSheet + ySheet * sheet.width + tileOffset] * 8)) & 255; 
                if (col < 255) {
                    for (int yScale = 0; yScale < scale; yScale++) {
                        if (yPixel + yScale < 0 || yPixel + yScale >= height)
                            continue;
                        for (int xScale = 0; xScale < scale; xScale++) {
                            if (xPixel + xScale < 0 || xPixel + xScale >= width)
                                continue;
                            pixels[(xPixel + xScale) + (yPixel + yScale) * width] = col;
                        }
                    }
                }
            }
        }
    }

    public void setOffset(int xOffset, int yOffset) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
