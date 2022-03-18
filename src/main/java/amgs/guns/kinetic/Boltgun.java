package amgs.guns.kinetic;

import amgs.*;
import amgs.gfx.*;
import amgs.guns.*;
import amgs.projectiles.player.Bullet;

public class Boltgun extends Gun {

    public Boltgun(Handler handler) {
        super(handler);
        
        setBufferedImage(Assets.gun);
    }

    @Override
    protected void shoot() {
        projectileManager.addProjectile(
            new Bullet(handler, 
                handler.getWorld().getPlayer().getX() + handler.getWorld().getPlayer().getWidth()/2, 
                handler.getWorld().getPlayer().getY() + handler.getWorld().getPlayer().getHeight()/2,
                reticle.getAngle())
            );
        
        // play sound
        Assets.boltgunShootSound.play();
    }

}
