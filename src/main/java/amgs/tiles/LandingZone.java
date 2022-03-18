package amgs.tiles;

import amgs.*;
import amgs.gfx.Assets;

public class LandingZone extends Tile {

    // WARN: This tile is has a multitile size
    public LandingZone(int id) {
        super(Assets.landingZone, id,
            Constants.TILE_WIDTH*4,
            Constants.TILE_HEIGHT*3);
    }

}