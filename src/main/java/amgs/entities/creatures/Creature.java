package amgs.entities.creatures;

import amgs.*;

import amgs.entities.*;

public abstract class Creature extends Entity {
	
	public static final float DEFAULT_SPEED = 3f;
	// public static final int DEFAULT_WIDTH = 16*7, DEFAULT_HEIGHT = 16*7;
	
	protected float speed;
	protected float xMove, yMove;
	
	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		speed = DEFAULT_SPEED;
	}
	
	// movement and collisions
	public void move() {
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
	}

	public void moveX() {
		if(xMove > 0) {	// moving right
			// chech upper and lower right corners of the bounding box
			// NB: division to get the result in terms of tile
			int tempX = (int)((x + xMove + bounds.x + bounds.width)/Constants.TILE_WIDTH);
			if( !collisionWithTile(tempX, (int)((y + bounds.y)/Constants.TILE_HEIGHT)) &&
				!collisionWithTile(tempX, (int)((y + bounds.y + bounds.height)/Constants.TILE_HEIGHT))) {
				x += xMove;
			} else {
				x = tempX*Constants.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} else if(xMove < 0) { // moving left
			// check upper and lower left corners of the bounding box
			int tempX = (int)((x + xMove + bounds.x)/Constants.TILE_WIDTH);
			if( !collisionWithTile(tempX, (int)((y + bounds.y)/Constants.TILE_HEIGHT)) &&
				!collisionWithTile(tempX, (int)((y + bounds.y + bounds.height)/Constants.TILE_HEIGHT))) {
				x += xMove;
			} else {
				x = tempX*Constants.TILE_WIDTH + Constants.TILE_WIDTH - bounds.x;
			}
		}
	}

	public void moveY() {
		if(yMove < 0) { // moving down
			// check upper left and right corners of the bounding box
			int tempY = (int)((y + yMove + bounds.y)/Constants.TILE_HEIGHT);
			if( !collisionWithTile((int)(x + bounds.x)/Constants.TILE_WIDTH, tempY) &&
				!collisionWithTile((int)(x + bounds.x + bounds.width)/Constants.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = tempY*Constants.TILE_HEIGHT + Constants.TILE_HEIGHT - bounds.y;
			}
		} else if(yMove > 0) { // moving up
			// check lower left and right corners of the bounding box
			int tempY = (int)((y + yMove + bounds.y + bounds.height)/Constants.TILE_HEIGHT);
			if( !collisionWithTile((int)(x + bounds.x)/Constants.TILE_WIDTH, tempY) &&
				!collisionWithTile((int)(x + bounds.x + bounds.width)/Constants.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
				y = tempY*Constants.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getCurrentLevel().getTile(x, y).isSolid();
	}
	
	// GETTERS SETTERS
	public float getSpeed() {
		return speed;
	}
	
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public float getXMove() {
		return xMove;
	}
	
	public void setXMove(float xMove) {
		this.xMove = xMove;
	}
	
	public float getYMove() {
		return yMove;
	}
	
	public void setYMove(float yMove) {
		this.yMove = yMove;
	}
	
}
