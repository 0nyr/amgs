package amgs.entities;

import amgs.*;

import java.awt.*;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

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
			if(entity.getCollisionBounds(0f, 0f)
				.intersects(getCollisionBounds(xOffset, yOffset))) {
				thereIsCollision = true;
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
	
}
