package amgs.entities;

import amgs.*;
import amgs.entities.creatures.Player;

import java.awt.*;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	protected boolean active;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		active = true;
		health = Constants.DEFAULT_HEALTH;
		bounds = new Rectangle(0, 0, width, height); // default hitbox
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		boolean thereIsCollision = false;
		for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
			if(entity.equals(this)) {
				continue; // skip collision with itself
			}
			// apply damage to game health on touch and suicide
			if(entity.getCollisionBounds(0f, 0f)
				.intersects(getCollisionBounds(xOffset, yOffset))) {
				thereIsCollision = true;
				if((this instanceof TouchDmgHealth) &&
					(entity instanceof Player)) {
					// apply damage to game health
					TouchDmgHealth tdh = (TouchDmgHealth)this;
					tdh.damageGameHealth();
					// suicide
					this.suicide();
				}
			}
		}
		return thereIsCollision;
	}

	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle(
			(int)(x + bounds.x + xOffset),
			(int)(y + bounds.y + yOffset),
			bounds.width, bounds.height);
	}

	public void hurt(int dmg) {
		health -= dmg;
		if(health <= 0) {
			die((int)x, (int)y);
			active = false;
		}
	}

	public abstract void die(int x, int y);

	public void suicide() {
		hurt(health);
	}

	// GETTERS SETTERS
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}
}
