package amgs.world;

import amgs.*;
import amgs.background.*;
import amgs.display.*;
import amgs.entities.EntityManager;
import amgs.entities.creatures.Player;
import amgs.entities.stationary.Rock;
import amgs.gfx.GameCamera;
import amgs.tiles.*;
import amgs.utils.*;

import java.awt.*;

public class World {

    public int width, height;   // in nb of tiles

    private Handler handler;
    private int[][] idtTiles;  // contains id for Tile
    private int spawnPlayerX, spawnPlayerY; // in nb of tiles
    private EntityManager entityManager;

    // backgrounds
    private Background fixedBg, nebulaeBg, gasCloudsBg;

    public World(Handler handler, String path) {
        this.handler = handler;

        entityManager = new EntityManager(handler);
        entityManager.addEntity(new Rock(handler, 
            10*Constants.TILE_WIDTH, 
            12*Constants.TILE_HEIGHT));

        loadWorld(path);

        System.out.println(spawnPlayerX +" "+ spawnPlayerY);
        entityManager.addEntity(new Player(
            handler, 
            spawnPlayerX*Constants.TILE_WIDTH, 
            spawnPlayerY*Constants.TILE_HEIGHT)); 
        
        fixedBg = new FixedBg(handler);
        gasCloudsBg = new GasCloudsBg(handler);
        nebulaeBg = new NebulaeBg(handler);
    }

    public void tick() {
        fixedBg.tick();
        gasCloudsBg.tick();
        nebulaeBg.tick();
        entityManager.tick();
    }

    public void render(Graphics g) {
        // render backgrounds
        fixedBg.render(g);
        gasCloudsBg.render(g);
        nebulaeBg.render(g);

        // render tiles
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

        // render entities
        entityManager.render(g);
    }

    public Tile getTile(int x, int y) {
        // avoid crash if player outside of the map
        if(x < 0 || y < 0 || x >= width || y >= height) {
            return TileManager.ground;
        }
        // convert tile.id into Tile
        Tile tile = TileManager.tiles[this.idtTiles[x][y]];
        if(tile == null) {
            return TileManager.emptyZone;
        } else {
            return tile;
        }
    }

    private void loadWorld(String path) {
        String worldFile = Utils.loadFileAsString(path);
        String[] tokens = worldFile.split("\\s+");

        // recover NB_HEADER_INFO data values from world file
        final int NB_HEADER_INFO = 4;

        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnPlayerX = Utils.parseInt(tokens[2]);
        spawnPlayerY = Utils.parseInt(tokens[3]);

        idtTiles = new int[width][height];
        for(int y=0; y<height; y++) {
            for(int x=0; x<width; x++) {
                idtTiles[x][y] = Utils.parseInt(
                    // get ids for every String token after header
                    tokens[(x + y*width) + NB_HEADER_INFO]);
            }
        }
    }

    // getters and setters
    public int getSpawnPlayerX() {
        return spawnPlayerX;
    }

    public int getSpawnPlayerY() {
        return spawnPlayerY;
    }

    public int getWorldWidthInTile() {
        return width;
    }

    public int getWorldHeightInTile() {
        return height;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
