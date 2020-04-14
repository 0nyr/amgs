package amgs.tiles;

import java.awt.*;
import java.awt.image.*;

public class Tile {

    protected BufferedImage texture;
    protected final int id;
    protected int width, height;

    public Tile(BufferedImage texture, int id, int width, int height) {
        this.texture = texture;
        this.id = id;
        this.width = width;
        this.height = height;

        TileManager.tiles[id] = this;
    }

    public void tick() {

    }

    public void render(Graphics g, int x, int y) {
        g.drawImage(texture, x, y, width, height, null);
    }

    public boolean isSolid() {
        return true;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
