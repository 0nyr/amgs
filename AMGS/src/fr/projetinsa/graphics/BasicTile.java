package fr.projetinsa.graphics;

import com.algoproj2020.Level;
import com.algoproj2020.Screen;

public class BasicTile extends Tile {
	protected int tileid;
	protected int tilecolour;
	public BasicTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, false, false, levelColour);
        this.tileid = x + y * 32;
        this.tilecolour = tileColour;
    }
	public void update() {
    }
	
	@Override
	public void render(Screen screen, Level level, int x, int y) {
        screen.render(x, y, tileid, tilecolour, 0x00, 1);
    }

}
