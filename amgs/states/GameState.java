package amgs.states;

import amgs.*;
import amgs.world.*;

import java.awt.*;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		
		world = new World(handler, "/res/worlds/world1.txt");
		handler.setWorld(world); // initiate handler.world
	}
	
	@Override
	public void tick() {
		world.tick();
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}

}
