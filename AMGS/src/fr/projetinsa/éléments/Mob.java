package fr.projetinsa.éléments;

import com.algoproj2020.Level;

public abstract class Mob extends Entity {
	protected int speed;
	protected int numbsteps=0;
	protected String name;
	protected boolean ismoving;
	protected int movingdir;
	protected int scale=1;
	public Mob(Level level, String name, int x, int y, int speed) {
		super(level);
		this.name=name;
		this.x=x;
		this.y=y;
		this.speed=speed;
	}
	public void move(int xa, int ya) {
		if(xa!=0 && ya!=0) {
			move(xa,0);
			move(0,ya);
			numbsteps--;
			return;
		}
		numbsteps++;
		if(!hascollided(xa,ya)) {
			if(ya<0)movingdir=0;
			if(ya>0)movingdir=1;
			if(xa<0)movingdir=2;
			if(xa>0)movingdir=3;
			x+=xa*speed;
			y+=ya*speed;
		}
	}
	public abstract boolean hascollided(int xa, int ya);
	public String getname() {
		return name;
	}
}
