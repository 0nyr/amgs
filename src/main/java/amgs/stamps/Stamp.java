package amgs.stamps;

import amgs.*;

import java.awt.*;
import java.awt.image.*;

public abstract class Stamp {

    protected Rectangle bounds;

    protected Handler handler;
    protected BufferedImage image;

    public Stamp(Handler handler, Rectangle bounds) {
        this.handler = handler;
        this.bounds = bounds;
        image = new BufferedImage(bounds.width, bounds.height, 
            BufferedImage.TYPE_INT_ARGB);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    // GETTERS SETTERS
    public Rectangle getBounds() {
        return bounds;
    }

}
