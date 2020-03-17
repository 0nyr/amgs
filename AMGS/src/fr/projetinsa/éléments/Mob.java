package fr.projetinsa.éléments;

import com.algoproj2020.Level;

import fr.projetinsa.graphics.Sprite;
import fr.projetinsa.graphics.Tile;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;       
	protected int speed;// the number of pixels traveled by the mob in one step.
	protected int numbsteps=0;// variable used to store the number of steps done by the player
	protected String name;// name of the mob
	protected boolean ismoving;// true if the player is moving false if not
	protected int movingdir;// 0 up; 1 down; 2 left; 3 right.
	protected int scale=1;
	
	/**
	 * Constructor of the class
	 * @param level the level in which is the player
     * @param x the x coordinate of the player 
     * @param y the y coordinate of the player
	 * @param speed number of pixels traveled by the mob in one step.
	 */
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name=name;
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	
	/**
	 * Performs the change of coordinates corresponding to the player's displacement.
	 * @param xa xa=1 <-> mob moves to the right; xa= -1 <-> mob moves to the left;
	 * xa= 0 <-> mob doesn't move
	 * @param ya ya=1 <-> mob moves downward; ya= -1 <-> mob moves upward
	 * ya= 0 <-> mob doesn't move
	 */
	public void move(int xa, int ya) {
		if(xa!=0 && ya!=0) {// If the player moves at the same time along the x axis and the y axis,
			move(xa,0);// then he moves first along the x axis
			move(0,ya);// and then along the y axis. (however these motions will be simultaneous during the render)
			numbsteps--;/* We just make sure that the number of steps is not counted twice.
			*Indeed we will consider that a diagonal displacement requires only one steps.*/
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
	/**
	 * Detects a possible collision between the player and the square he is moving towards.
	 * @param xa xa=1 <-> mob moves to the right; xa= -1 <-> mob moves to the left;
	 * xa= 0 <-> mob doesn't move
	 * @param ya ya=1 <-> mob moves downward; ya= -1 <-> mob moves upward
	 * ya= 0 <-> mob doesn't move    
	 * @param x Enables to adapt the collision test to the shape of the player.
	 * Indeed, we don't want the collision test to be executed at the borders of the player sprite but on the player's body.
	 * @param y idem
	 * @return
	 */
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
	/**
	 * Getter for the name attribute
	 * @return
	 */
	public String getname() {
		return name;
	}
	/**
	 * Getter for the numbsteps attribute
	 * @return
	 */
	public int getnumbsteps() {
		return numbsteps;
	}
	/**
	 * Getter for the movingdir attribute
	 * @return
	 */
	public int getmovingdir() {
		return movingdir;
	}
	/**
	 * Getter for the ismoving attribute
	 * @return
	 */
	public boolean getismoving() {
		return ismoving;
	}
	/**
	 * Setter for the ismoving attribute
	 * @return
	 */
	public void setismoving(boolean ismoving) {
		this.ismoving = ismoving;
	}
	/**
	 * Setter for the movingdir attribute
	 * @return
	 */
	public void setmovingdir(int movingdir) {
		this.movingdir = movingdir;
	}
	/**
	 * Setter for the numbsteps attribute
	 * @return
	 */
	public void setnumbsteps(int numbsteps) {
		this.numbsteps = numbsteps;
	}
	
}
