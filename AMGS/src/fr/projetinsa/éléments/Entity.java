package fr.projetinsa.éléments;

import com.algoproj2020.Level;
import com.algoproj2020.Screen;

public abstract class Entity {
	public int x,y;
	protected Level level;
	
	public Entity(Level level) {
		this.init(level);
	}
	public final void init(Level level) {
		this.level=level;
	}
	public abstract void update();
	
	public abstract void rendu (Screen screen);
}
