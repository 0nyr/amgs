package amgs.entities.flying;

import amgs.*;
import amgs.Handler;
import amgs.entities.TouchDmgHealth;
import amgs.gfx.*;
import amgs.items.*;

import java.awt.*;

public class Krug extends FlyingCreature implements TouchDmgHealth {

    private Animation anim;

    private long lastTimer, cooldown, deltaTimer;
    private float xLast, yLast;

    public Krug(Handler handler, float x, float y) {
        super(handler, x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
        
        anim = new Animation(500, Assets.krug);
        setSpeed( 1.5f + (float)(Math.random()));
        setHealth(10);

        // specify collision box area, WARN: Use float division, not integer one !
		bounds.x = (int)(Constants.TILE_WIDTH*(1f/16f));
		bounds.y = (int)(Constants.TILE_HEIGHT*(1f/16f));
		bounds.width = (int)(Constants.TILE_WIDTH*(14f/16f));
        bounds.height = (int)(Constants.TILE_HEIGHT*(14f/16f));
        
        // dispawn
        cooldown = 500; // dispawn if no movement 
        deltaTimer = 0; // consider movement from start
        xLast = x;
        yLast = y;
    }

    @Override
    public void tick() {
        anim.tick();
        // movement
        setXYMoveTowardsPlayer();
        move();
        checkNotImmobile();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(),
			(int)(x - handler.getGameCamera().getXOffset()), 
			(int)(y - handler.getGameCamera().getYOffset()), 
            width, height, null);
            
        testDisplayBounds(g);
    }

    @Override
    public void die(int x, int y) {
        if(!isActive()) {
            return; // already dead
        }
        ItemManager itemManager = handler.getWorld().getItemManager();
        itemManager.addItem(itemManager.gemmeItem.createNew(x, y));
        handler.getWorld().getGameHud().addToScoreApplyMultiplier(5);
        // count this enemy in enemy killed number
        handler.getWorld().getGameHud().oneEnemyWasKilled();

        // play sound
        Assets.krugDeathSound.play();
    }

    @Override
    public void damageGameHealth() {
        if(health <= 0) {
            return;
        }
        handler.getWorld().getGameHud().addHealth(-10);

        // play sound
        Assets.playerHurtSound.play();
    }

    private void setXYMoveTowardsPlayer() {
        // coordinates player
        double xp = handler.getWorld().getPlayer().getX();
        double yp = handler.getWorld().getPlayer().getY();
        // trigonometry
        double hypotenuse = Math.sqrt(
            Math.pow((xp - x), 2) + Math.pow((yp - y), 2));
        double adjacentSide = (xp - x);
        double oppositeSize = -(yp - y);
        // compute movement
        xMove = (float)((adjacentSide/hypotenuse)*speed);
        yMove = (float)(-(oppositeSize/hypotenuse)*speed);
    }

    private void checkNotImmobile() {
        deltaTimer += System.currentTimeMillis() - lastTimer;
        lastTimer = System.currentTimeMillis();
        
        if(xLast == x && yLast == y) {
            // no movement, continue increment deltaTimer
            if(deltaTimer > cooldown) {
                // no movement for a long time => dispawn
                this.suicide();
            }
        } else {
            // effective movement, reset deltaTimer
            deltaTimer = 0;
        }

        xLast = x;
        yLast = y;
    }

    private void testDisplayBounds(Graphics g) {
		if(Constants.isTest) { //Constants.isTest
            g.setColor(Color.green);
            g.fillRect(
                (int)(getCollisionBounds(0f, 0f).x - handler.getGameCamera().getXOffset()),
                (int)(getCollisionBounds(0f, 0f).y - handler.getGameCamera().getYOffset()),
                getCollisionBounds(0f, 0f).width, getCollisionBounds(0f, 0f).height);
		}
	}

}
