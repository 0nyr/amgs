package amgs.levels;

import amgs.*;
import amgs.display.*;
import amgs.entities.*;
import amgs.entities.creatures.*;
import amgs.entities.stationary.*;
import amgs.gfx.*;
import amgs.items.*;
import amgs.tiles.*;

import java.awt.*;
import java.awt.image.*;

public abstract class Level {

    private int[][] idTiles;  // contains id for Tile, where tiles are located before rendering
    public int width, height;   // in nb of tiles
    private int spawnPlayerX, spawnPlayerY; // in nb of tiles
    private LevelGenerator levelGenerator;

    private Handler handler;

    public Level(Handler handler) {
        this.handler = handler;

        levelGenerator = new LevelGenerator(handler);
        initTileMapAndWorldElements();
    }

    public abstract void tick();

    public void render(Graphics g) {
        // render tiles contained in idTiles
        Tile t;
        Display display = handler.getDisplay();
        GameCamera gameCamera = handler.getGameCamera();
        // compute the range of tiles to display
        int xStart = (int) Math.max(0, 
            gameCamera.getXOffset()/Constants.TILE_WIDTH);
        int xEnd = (int) Math.min(width,
            (gameCamera.getXOffset() + display.getWidth())/Constants.TILE_WIDTH + 1);
        int yStart = (int) Math.max(0, 
            gameCamera.getYOffset()/Constants.TILE_HEIGHT);
        int yEnd = (int) Math.min(height, 
            (gameCamera.getYOffset() + display.getHeight())/Constants.TILE_HEIGHT + 1);
        // first along y then along x due to array implementation
        for(int y = yStart; y < yEnd; y++) {
            for(int x = xStart; x < xEnd; x++) {
                t = getTile(x, y);
                // here we apply the offsets of the GameCamera
                t.render(g, 
                    (int)( x*Constants.TILE_HEIGHT - gameCamera.getXOffset()), 
                    (int)( y*Constants.TILE_HEIGHT - gameCamera.getYOffset()));
            }
        }
    }

    public Tile getTile(int x, int y) {
        // WARN: x, y in tile position !
        // avoid crash if player outside of the map
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return TileManager.ground;
        }
        // convert tile.id into Tile
        Tile tile = TileManager.tiles[this.idTiles[x][y]];
        if(tile == null) {
            return TileManager.emptyZone;
        } else {
            return tile;
        }
    }

    /* WARN: don't forget after to recover data in world such as player start position */
    private void initTileMapAndWorldElements() {
        // init variables
        BufferedImage spriteMap = levelGenerator
            .generateSpriteMapDependingOnWaveNumber();
        width = spriteMap.getWidth();
        height = spriteMap.getHeight();
        idTiles = new int[width][height];
        EntityManager entityManager = handler.getWorld().getEntityManager();
        ItemManager itemManager = handler.getWorld().getItemManager();

        // convert the sprite map into an array of tile ID and add elements to their list
        int[] spriteMapPixels = ( (DataBufferInt) spriteMap.getRaster().getDataBuffer() )
            .getData();
        int xPixel, yPixel, pixelValue;
        for(int i = 0; i < spriteMapPixels.length; i++) {
            // determine pixel position in tile
            xPixel = i%spriteMap.getWidth();
            yPixel = (int) i/width;
            pixelValue = spriteMapPixels[i];

            // determine tile id + additional world element initialisation
                // simple tiles
            if(pixelValue == Color.decode(ColorCode.CC_EMPTY_ZONE).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_EMPTY_ZONE;

            } else if(pixelValue == Color.decode(ColorCode.CC_GROUND).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                
            } else if(pixelValue == Color.decode(ColorCode.CC_WALL).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_WALL;

            } else if(pixelValue == Color.decode(ColorCode.CC_LANDING_ZONE).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_LANDING_ZONE;

                // ground tiles + entity
            } else if(pixelValue == Color.decode(ColorCode.CC_PLAYER).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                spawnPlayerX = xPixel;
                spawnPlayerY = yPixel;

            } else if(pixelValue == Color.decode(ColorCode.CC_BLOB).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                entityManager.addEntity(new Blob(handler, 
                    xPixel*Constants.TILE_WIDTH, 
                    yPixel*Constants.TILE_HEIGHT));

            } else if(pixelValue == Color.decode(ColorCode.CC_ROCK).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                entityManager.addEntity(new Rock(handler, 
                    xPixel*Constants.TILE_WIDTH, 
                    yPixel*Constants.TILE_HEIGHT));

                // items
            } else if(pixelValue == Color.decode(ColorCode.CC_GEMME).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                itemManager.addItem(itemManager.gemmeItem.createNew(
                    xPixel*Constants.TILE_WIDTH, 
                    yPixel*Constants.TILE_HEIGHT));

            } else if(pixelValue == Color.decode(ColorCode.CC_HEALTH_CRATE).getRGB()) {
                idTiles[xPixel][yPixel] = TileManager.ID_GROUND;
                itemManager.addItem(itemManager.healthCrateItem.createNew(
                    xPixel*Constants.TILE_WIDTH, 
                    yPixel*Constants.TILE_HEIGHT));

            } else {
                // by default, a tile is empty void
                idTiles[xPixel][yPixel] = TileManager.ID_EMPTY_ZONE;
            }
        }
    }

    // GETTERS SETTERS
    public int getSpawnPlayerXInTile() {
        return spawnPlayerX;
    }

    public int getSpawnPlayerYInTile() {
        return spawnPlayerY;
    }

    public int getLevelWidthInTile() {
        return width;
    }

    public int getLevelHeightInTile() {
        return height;
    }

}
