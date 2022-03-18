package amgs.projectiles;

import amgs.*;
import amgs.entities.Entity;
import amgs.tiles.*;

import java.awt.image.*;
import java.awt.*;

public abstract class Projectile {

    protected Handler handler;
    protected BufferedImage image;
    protected int width, height;
    protected Entity emitter; // can't harm emmiter
    protected int damage;
    protected double angle, speed, range, distance;
    protected final double xOrigin, yOrigin; // not sure usefull
    protected double x, y, xMove, yMove;
    protected Rectangle bounds;
    protected Boolean hasTouched; // NB: use hasJustTouched() to set to true

    public Projectile(Handler handler, double xOrigin, double yOrigin, double angle) {
        this.handler = handler;
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        x = xOrigin;
        y = yOrigin;
        hasTouched = false;
    
        // default
        emitter = handler.getWorld().getPlayer();
        speed = 5d;
        range = 1000d;
        damage = 10;
        width = Constants.TILE_WIDTH;
        height = Constants.TILE_HEIGHT;
        bounds = new Rectangle(0, 0, width, height); // default hitbox

        // movement
        xMove = speed*Math.cos(angle);
        yMove = speed*Math.sin(angle);
    }

    public void tick() {
        move();
        computeDistanceToOrigin();
    }

    public void render(Graphics g) {
        g.drawImage(image,
			(int)(x - bounds.x), 
			(int)(y - bounds.y), 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
    }

    public abstract void die(int x, int y);

    public void hasJustTouched() {
        if(hasTouched) {
            // hasTouched has already been set to true
            return;
        }
        hasTouched = true;
        die((int) x, (int) y);
    }
    /*
    protected void move() {
        x += dx;
        y += dy;
    }*/

    public void move() {
		if(!checkEntityCollisions(xMove, 0f)) {
			moveX();
		}
		if(!checkEntityCollisions(0f, yMove)) {
			moveY();
		}
    }
    
    // apply damage on entity
    public boolean checkEntityCollisions(double xOffset, double yOffset) {
		boolean thereIsCollision = false;
		for(Entity entity : handler.getWorld().getEntityManager().getEntities()) {
			if(entity.equals(emitter)) {
				continue; // skip collision with emitter
			}
			// apply damage to entity on touch and consider has touched
			if(entity.getCollisionBounds(0f, 0f)
				.intersects(getCollisionBounds(xOffset, yOffset))) {
				thereIsCollision = true;
				entity.hurt(damage);
                hasJustTouched();
			}
		}
		return thereIsCollision;
	}

	public Rectangle getCollisionBounds(double xOffset, double yOffset) {
		return new Rectangle(
			(int)(x + bounds.x + xOffset),
			(int)(y + bounds.y + yOffset),
			bounds.width, bounds.height);
	}

	public void moveX() {
		if(xMove > 0) {	// moving right
			// chech upper and lower right corners of the bounding box
			// NB: division to get the result in terms of tile
			int tempX = (int)((x + xMove + bounds.x + bounds.width)/Constants.TILE_WIDTH);
			if( !collisionWithTileNotEmpty(tempX, (int)((y + bounds.y)/Constants.TILE_HEIGHT)) &&
				!collisionWithTileNotEmpty(tempX, (int)((y + bounds.y + bounds.height)/Constants.TILE_HEIGHT))) {
				x += xMove;
			} else {
                // projectile has touched a solid non-empty tile and considered has touched
                hasJustTouched();
				x = tempX*Constants.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		} else if(xMove < 0) { // moving left
			// check upper and lower left corners of the bounding box
			int tempX = (int)((x + xMove + bounds.x)/Constants.TILE_WIDTH);
			if( !collisionWithTileNotEmpty(tempX, (int)((y + bounds.y)/Constants.TILE_HEIGHT)) &&
				!collisionWithTileNotEmpty(tempX, (int)((y + bounds.y + bounds.height)/Constants.TILE_HEIGHT))) {
				x += xMove;
			} else {
                // projectile has touched a solid non-empty tile and considered has touched
                hasJustTouched();
				x = tempX*Constants.TILE_WIDTH + Constants.TILE_WIDTH - bounds.x;
			}
		}
	}

	public void moveY() {
		if(yMove < 0) { // moving down
			// check upper left and right corners of the bounding box
			int tempY = (int)((y + yMove + bounds.y)/Constants.TILE_HEIGHT);
			if( !collisionWithTileNotEmpty((int)(x + bounds.x)/Constants.TILE_WIDTH, tempY) &&
				!collisionWithTileNotEmpty((int)(x + bounds.x + bounds.width)/Constants.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
                // projectile has touched a solid non-empty tile and considered has touched
                hasJustTouched();
				y = tempY*Constants.TILE_HEIGHT + Constants.TILE_HEIGHT - bounds.y;
			}
		} else if(yMove > 0) { // moving up
			// check lower left and right corners of the bounding box
			int tempY = (int)((y + yMove + bounds.y + bounds.height)/Constants.TILE_HEIGHT);
			if( !collisionWithTileNotEmpty((int)(x + bounds.x)/Constants.TILE_WIDTH, tempY) &&
				!collisionWithTileNotEmpty((int)(x + bounds.x + bounds.width)/Constants.TILE_WIDTH, tempY)) {
				y += yMove;
			} else {
                // projectile has touched a solid non-empty tile and considered has touched
                hasJustTouched();
				y = tempY*Constants.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}
	}

    // collision with tiles
	protected boolean collisionWithTileNotEmpty(int x, int y) {
		boolean isSolidNotEmpty = false;
		Tile currentTile = handler.getWorld().getCurrentLevel().getTile(x, y);
		if((currentTile.isSolid()) && (currentTile.getId() != TileManager.ID_EMPTY_ZONE)) {
			isSolidNotEmpty = true;
		}
		return isSolidNotEmpty;
	}

    protected void computeDistanceToOrigin() {
        double deltaXOrigin = Math.abs(x - xOrigin);
        double deltaYOrigin = Math.abs(y - yOrigin);
        distance = Math.sqrt(Math.pow(deltaXOrigin, 2) + Math.pow(deltaYOrigin, 2)); 
    }

    // GETTERS SETTERS
    public void setEmitter(Entity emitter) {
        this.emitter = emitter;
    }

    public void setBufferedImage(BufferedImage image) {
        this.image = image;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public boolean isOutOfRange() {
        Boolean isOutOfRange = (distance > range) ? true : false;
        return isOutOfRange;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
    }
    
    public boolean hasAlreadyTouched() {
        return hasTouched;
    }

}
