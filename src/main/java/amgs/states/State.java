package amgs.states;

import amgs.*;
import amgs.background.*;

import java.awt.*;

public abstract class State {
	
	protected Handler handler;

	protected Background background;
	
	public State(Handler handler){
		this.handler = handler;

		// default background
		background = new FixedBg(handler);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
