package amgs.entities.creatures;

import amgs.gfx.*;
import amgs.*;
import amgs.input.*;

import java.awt.*;
import java.awt.image.*;

public class Player extends Creature {

	private Animation animDown, animUp, animRight, animLeft;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);

		// specify collision box area, WARN: Use float division, not integer one !
		bounds.x = (int)(Constants.TILE_WIDTH*(4f/16f));
		bounds.y = (int)(Constants.TILE_HEIGHT*(7f/16f));
		bounds.width = (int)(Constants.TILE_WIDTH*(8f/16f));
		bounds.height = (int)(Constants.TILE_HEIGHT*(8f/16f));

		// animations
		animDown = new Animation(500, Assets.player_down);
		animUp = new Animation(500, Assets.player_up);
		animRight = new Animation(500, Assets.player_right);
		animLeft = new Animation(500, Assets.player_left);
	}
	
	@Override
	public void tick() {
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	@Override
	public void render(Graphics g) {
		// WARN: don't forget to apply the GameCamera offset to the player ! 
		g.drawImage(getDirectionalAnimationFrame(),
			(int)(x - handler.getGameCamera().getXOffset()), 
			(int)(y - handler.getGameCamera().getYOffset()), 
			width, height, null);
		
		testDisplayBounds(g); // display hitbox
	}

	public String toString() {
		return "I am a player";
	}

	private BufferedImage getDirectionalAnimationFrame() {
		if(xMove < 0) { // letf
			return animLeft.getCurrentFrame();
		} else if(xMove > 0) { // right
			return animRight.getCurrentFrame();
		} else if(yMove < 0) { // up
			return animUp.getCurrentFrame();
		} else if(yMove > 0) { //down
			return animDown.getCurrentFrame();
		} else { // no motion
			return Assets.player_down[0];
		}
	}

	private void testDisplayBounds(Graphics g) {
		if(Constants.isTest) {
		g.setColor(Color.red);
		g.fillRect(
			(int)(x + bounds.x - handler.getGameCamera().getXOffset()),
			(int)(y + bounds.y - handler.getGameCamera().getYOffset()),
			bounds.width, bounds.height);
		}
	}
	
	private void getInput() {
		// anticipate motion
		xMove = 0;
		yMove = 0;
		
		KeyManager keyManager = handler.getKeyManager();
		if (keyManager.up) {
			yMove = -speed;
		}
		if (keyManager.down) {
			yMove = speed;
		}
		if (keyManager.left) {
			xMove = -speed;
		}
		if (keyManager.right) {
			xMove = speed;
		}
	}
	
	
}



