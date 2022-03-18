package amgs.background;

import amgs.*;
import amgs.gfx.*;

import java.awt.*;

public abstract class Background {

    protected Handler handler;
    protected Animation animBg;

    public Background(Handler handler) {
        this.handler = handler;
    }

    public void tick() {
        animBg.tick();
    }

    public abstract void render(Graphics g);

    // SETTERS
    public void setAnimBg(Animation animBg) {
        this.animBg = animBg;
    }

}