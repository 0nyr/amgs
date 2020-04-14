package amgs.entities.stationary;

import amgs.*;
import amgs.gfx.*;

import java.awt.*;

public class Rock extends StationaryEntity {

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
    
        // specify collision box area, WARN: Use float division, not integer one !
		bounds.x = (int)(Constants.TILE_WIDTH*(1f/16f));
		bounds.y = (int)(Constants.TILE_HEIGHT*(8f/16f));
		bounds.width = (int)(Constants.TILE_WIDTH*(14f/16f));
		bounds.height = (int)(Constants.TILE_HEIGHT*(7f/16f));
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, 
            (int)(x - handler.getGameCamera().getXOffset()),
            (int)(y - handler.getGameCamera().getYOffset()), 
            width, height, null);
    }
}