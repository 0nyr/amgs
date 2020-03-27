package fr.projetinsa.�l�ments;

import com.algoproj2020.Level;
import com.algoproj2020.Screen;

public abstract class Entity {
	public int x,y;// Les coordonn�es du personnage sur la map.
	protected Level level;
	
	public Entity(Level level) {
		this.init(level);
	}
	public final void init(Level level) {
		this.level=level;
	}
	public abstract void update();
	
	public abstract void render (Screen screen);
}
