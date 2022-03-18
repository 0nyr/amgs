package amgs.background;

import amgs.*;
import java.awt.*;
import amgs.gfx.*;

public class FixedBg extends Background {

    public FixedBg(Handler handler) {
        super(handler);
        setAnimBg(new Animation(800, Assets.immobileBackgroundStars));
    }

    public void render(Graphics g) {
        g.drawImage(animBg.getCurrentFrame(), 0, 0, 
			handler.getScreenWidth(), handler.getScreenHeight(), null);
    }

}
