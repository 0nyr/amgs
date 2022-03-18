package amgs.guns;

import amgs.*;
import amgs.hud.*;
import amgs.input.*;
import amgs.projectiles.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.geom.*;

public abstract class Gun {

    protected Handler handler;
    protected String name;
    protected BufferedImage image;
    protected Reticle reticle;
    protected AffineTransform at;
    protected long lastShootTimer, shootCooldown, deltaShootTimer;
    protected ProjectileManager projectileManager;
    protected MouseManager mouseManager;

    public Gun(Handler handler) {
        this.handler = handler;
        projectileManager = handler.getWorld().getProjectileManager();
        mouseManager = handler.getGame().getMouseManager();
        reticle = new Reticle(handler);

        // shoot
		shootCooldown = 400;
        deltaShootTimer = shootCooldown;	// allow att from start
        
        // graphics
        at = new AffineTransform();
    }

    public void tick() {
        reticle.tick();
        // check is shoot 
        checkShoot();
    }

    public void render(Graphics g) {
        reticle.render(g);
    }

    protected void checkShoot() {
        deltaShootTimer += System.currentTimeMillis() - lastShootTimer;
		lastShootTimer = System.currentTimeMillis();
		if(deltaShootTimer < shootCooldown) {
			return;	// return if too short after previous shot
        }
        reticle.setCanShoot(true);
        
        if(!mouseManager.isLeftPressed()) {
            return; // stop there, no shoot to perform
        }

        deltaShootTimer = 0;
        shoot();
        reticle.setCanShoot(false);
    }

    protected abstract void shoot();

    // GETTERS SETTERS
    public void setBufferedImage(BufferedImage image) {
        this.image = image;
    }

}
