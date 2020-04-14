package amgs.tiles;

import amgs.*;
import amgs.gfx.Assets;

public class EmptyZoneTile extends Tile {

    public EmptyZoneTile(int id) {
        super(Assets.emptyZone, id, 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
    }

}