package amgs.states;

import amgs.*;

import java.awt.*;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
}
