package amgs.states;

import amgs.*;
import amgs.world.*;

import java.awt.*;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		
		world = new World(handler);
		handler.setWorld(world); // initiate handler.world
		world.init("/res/worlds/world1.txt");
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
