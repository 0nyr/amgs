package fr.projetinsa.éléments;

import com.algoproj2020.Level;

import fr.projetinsa.graphics.Sprite;
import fr.projetinsa.graphics.Tile;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected int speed;
	protected int numbsteps=0;
	protected String name;
	protected boolean ismoving;
	protected int movingdir;// 0 up; 1 down; 2 left; 3 right.
	protected int scale=1;
	
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name=name;
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	/**
	 * 
	 * @param xa xa=1 <-> mob moves to the right; xa= -1 <-> mob moves to the left;
	 * xa= 0 <-> mob doesn't move
	 * @param ya xa=1 <-> mob moves downward; ya= -1 <-> mob moves upward
	 * ya= 0 <-> mob doesn't move
	 */
	public void move(int xa, int ya) {
		if(xa!=0 && ya!=0) {
			move(xa,0);
			move(0,ya);
			numbsteps--;
			return;
		}
		numbsteps++;
		if(!hascollided(xa,ya)) {// If there is no collision we move,
			
			if(ya<0)movingdir=0;
			if(ya>0)movingdir=1;
			if(xa<0)movingdir=2;
			if(xa>0)movingdir=3;
			x+=xa*speed;
			y+=ya*speed;
		}
	}
	public abstract boolean hascollided(int xa, int ya);
	
	protected boolean isSolidTile(int xa, int ya, int x, int y) {
        if (level == null) {
            return false;
        }
        Tile lastTile = level.getTile((this.x + x) >> 3, (this.y + y) >> 3);
        Tile newTile = level.getTile((this.x + x + xa) >> 3, (this.y + y + ya) >> 3);
        if (!lastTile.equals(newTile) && newTile.isSolid()) {
            return true;
        }
        return false;
    }
	
	public String getname() {
		return name;
	}
	public int getnumbsteps() {
		return numbsteps;
	}
	public int getmovingdir() {
		return movingdir;
	}
	public boolean getismoving() {
		return ismoving;
	}
	public void setismoving(boolean ismoving) {
		this.ismoving = ismoving;
	}
	public void setmovingdir(int movingdir) {
		this.movingdir = movingdir;
	}
	public void setnumbsteps(int numbsteps) {
		this.numbsteps = numbsteps;
	}
	
}
