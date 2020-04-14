package amgs.tiles;

import amgs.*;
import amgs.gfx.Assets;

public class WallTile extends Tile {

    public WallTile(int id) {
        super(Assets.wall, id, 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
    }

}