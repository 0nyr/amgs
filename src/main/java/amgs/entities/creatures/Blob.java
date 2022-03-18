package amgs.entities.creatures;

import amgs.*;
import amgs.Handler;
import amgs.entities.TouchDmgHealth;
import amgs.gfx.*;
import amgs.items.*;

import java.awt.*;

public class Blob extends Creature implements TouchDmgHealth {

    private Animation anim;

    public Blob(Handler handler, float x, float y) {
        super(handler, x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
        
        anim = new Animation(500, Assets.blob);
        setSpeed(1.5f);
        setHealth(10);

        // specify collision box area, WARN: Use float division, not integer one !
		bounds.x = (int)(Constants.TILE_WIDTH*(1f/16f));
		bounds.y = (int)(Constants.TILE_HEIGHT*(4f/16f));
		bounds.width = (int)(Constants.TILE_WIDTH*(14f/16f));
		bounds.height = (int)(Constants.TILE_HEIGHT*(9f/16f));
    }

    @Override
    public void tick() {
        anim.tick();
        // movement
        setXYMoveTowardsPlayer();
        move();
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
        ItemManager itemManager = handler.getWorld().getItemManager();
        itemManager.addItem(itemManager.gemmeItem.createNew(x, y));
        handler.getWorld().getGameHud().addToScoreApplyMultiplier(5);

        // play sound
        Assets.blobDeathSound.play();
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
