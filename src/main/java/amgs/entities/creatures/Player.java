package amgs.entities.creatures;

import amgs.gfx.*;
import amgs.guns.*;
import amgs.guns.kinetic.Boltgun;
import amgs.*;
import amgs.entities.Entity;
import amgs.input.*;
import amgs.items.*;

import java.awt.*;
import java.awt.image.*;

public class Player extends Creature {

	private Animation animDown, animUp, animRight, animLeft;
	private long lastAttTimer, attCooldown, deltaAttTimer;
	private Rectangle attBox;
	private Inventory inventory;
	private Gun gun;
	
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

		// attack
		attCooldown = 800;
		deltaAttTimer = attCooldown;	// allow att from start
		gun = new Boltgun(handler);

		// inventory
		inventory = new Inventory(handler);
	}
	
	@Override
	public void tick() {
		// animations
		animDown.tick();
		animUp.tick();
		animRight.tick();
		animLeft.tick();
		// movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// close combat attack
		checkAttack();
		// range combat
		gun.tick();
		// inventory
		inventory.tick();
	}
	
	@Override
	public void render(Graphics g) {
		// WARN: don't forget to apply the GameCamera offset to the player ! 
		g.drawImage(getDirectionalAnimationFrame(),
			(int)(x - handler.getGameCamera().getXOffset()), 
			(int)(y - handler.getGameCamera().getYOffset()), 
			width, height, null);
		
		testDisplayBounds(g); // display hitbox

		displayAttBox(g);	// display att box when attacking

		// range combat
		gun.render(g);

		inventory.render(g);
	}

	public String toString() {
		return "I am a player";
	}

	@Override
	public void die(int x, int y) {
		System.out.println("you lost");
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

	private void displayAttBox(Graphics g) {
		// display attBox if att was just performed
		if(deltaAttTimer < 400) {
			g.setColor(Color.orange);
			g.fillRect(
				(int)(attBox.x - handler.getGameCamera().getXOffset()), 
				(int)(attBox.y - handler.getGameCamera().getYOffset()), 
				attBox.width,
				attBox.height);
		}
	} 
	
	private void checkAttack() {
		deltaAttTimer += System.currentTimeMillis() - lastAttTimer;
		lastAttTimer = System.currentTimeMillis();
		if(deltaAttTimer < attCooldown) {
			return;	// return if too short after previous att
		}

		Rectangle collisionBoxPlayer = getCollisionBounds(0f, 0f);
		attBox = new Rectangle();
		int attBoxSize = 20;
		attBox.width = attBoxSize;
		attBox.height = attBoxSize;

		
		if(handler.getKeyManager().aUp) {
			attBox.x = collisionBoxPlayer.x 
				+ collisionBoxPlayer.width/2 - attBoxSize/2;
			attBox.y = collisionBoxPlayer.y - attBoxSize;
		} else if(handler.getKeyManager().aDown) {
			attBox.x = collisionBoxPlayer.x 
				+ collisionBoxPlayer.width/2 - attBoxSize/2;
			attBox.y = collisionBoxPlayer.y 
				+ collisionBoxPlayer.height;
		} else if(handler.getKeyManager().aLeft) {
			attBox.x = collisionBoxPlayer.x - attBoxSize;
			attBox.y = collisionBoxPlayer.y 
				+ collisionBoxPlayer.height/2 - attBoxSize/2;
		} else if(handler.getKeyManager().aRight) {
			attBox.x = collisionBoxPlayer.x 
				+ collisionBoxPlayer.width;
			attBox.y = collisionBoxPlayer.y 
				+ collisionBoxPlayer.height/2 - attBoxSize/2;
		} else {
			return; // stop there, no close combat att to perform
		}
		

		deltaAttTimer = 0;
		for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
			if (entity.equals(this)) {
				continue;
			}
			if(entity.getCollisionBounds(0f, 0f).intersects(attBox)) {
				System.out.println("");
				System.out.println("entity "+entity.getCollisionBounds(0f, 0f).toString());
				System.out.println("player hitbox "+attBox.toString());
				entity.hurt(10); //default health = 30 hp
				return;	// only hurt one entity at a time
			}
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
	
	// GETTERS SETTERS
	public Inventory getInventory() {
		return inventory;
	}

}



