package amgs.tiles;

import amgs.*;
import amgs.gfx.Assets;

public class GroundTile extends Tile {

    public GroundTile(int id) {
        super(Assets.ground, id, 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
    }

    @Override
    public boolean isSolid() {
        return false;
    }

}