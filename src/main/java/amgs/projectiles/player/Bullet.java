package amgs.projectiles.player;

import amgs.*;
import amgs.gfx.*;
import amgs.projectiles.*;

import java.awt.*;

public class Bullet extends Projectile {

    public Bullet(Handler handler, double xOrigin, double yOrigin, double angle) {
        super(handler, xOrigin, yOrigin, angle);
        setBufferedImage(Assets.bullet);

        // specify collision box area, WARN: Use float division, not integer one !
        // WARN: here, bounds.x and y are at center top corner of sprite
        bounds.x = - (int)((Constants.TILE_WIDTH*(8f/16f))/2);
		bounds.y = - (int)((Constants.TILE_HEIGHT*(8f/16f))/2);
		bounds.width = (int)(Constants.TILE_WIDTH*(8f/16f));
		bounds.height = (int)(Constants.TILE_HEIGHT*(8f/16f));
    }

    @Override
    public void render(Graphics g) {
        // WARN: due to different kind of shifting, the computation differ from usually
        g.drawImage(image,
            (int)(x + bounds.x - Constants.TILE_WIDTH*(4f/16f) 
            - handler.getGameCamera().getXOffset()), 
            (int)(y + bounds.y - Constants.TILE_HEIGHT*(4f/16f) 
            - handler.getGameCamera().getYOffset()), 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
        testDisplayBounds(g);
    }

    @Override
    public void die(int x, int y) {
        
    }

    private void testDisplayBounds(Graphics g) {
		if(Constants.isTest) {
            g.setColor(Color.pink);
            g.fillRect(
                (int)(x + bounds.x - handler.getGameCamera().getXOffset()),
                (int)(y + bounds.y - handler.getGameCamera().getYOffset()),
                bounds.width, bounds.height);
		}
	}

}
