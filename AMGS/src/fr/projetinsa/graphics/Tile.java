package fr.projetinsa.graphics;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;

public abstract class Tile {
	public static final Tile[] tiles=new Tile[256];
	public static final Tile VOID = new SolidTile(0, 0, 0, Colours.get(000, -1, -1, -1), 0xFF000000); // On fixe les contours de la map comme étant solides pour empêcher le joueur de dortir de la map.
    public static final Tile STONE = new BasicTile(1, 1, 0, Colours.get(-1, 333, 1, -1), 0xFF555555);
    public static final Tile GRASS = new BasicTile(2, 2, 0, Colours.get(-1, 131, 141, -1), 0xFF00FF00);
	public static final Tile WATER = new DynamicTile(3, new int[][] { { 0, 5 }, { 1, 5 }, { 2, 5 }, { 1, 5 } },Colours.get(-1, 004, 115, -1), 0xFF0000FF, 1000);
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelcolour;
	
    public Tile(int id, boolean isSolid, boolean isEmitter, int levelColour) {
        this.id = (byte) id;
        if (tiles[id] != null)
            throw new RuntimeException("Duplicate tile id on " + id);
        this.solid = isSolid;
        this.emitter = isEmitter;
        this.levelcolour = levelColour;
        tiles[id] = this;
    }
	public byte getId() {
		return id;
	}
	public boolean isSolid() {
		return solid;
	}
	public boolean isEmitter() {
		return emitter;
	}

    public abstract void tick();
	public abstract void render(Screen screen, Level level, int y, int x);
	public int getLevelColour() {
		return levelcolour;
	}
}
