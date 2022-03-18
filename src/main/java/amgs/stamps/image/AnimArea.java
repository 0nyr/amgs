package amgs.stamps.image;

import amgs.Handler;
import amgs.gfx.*;
import amgs.stamps.*;

import java.awt.*;
import java.awt.image.*;

public class AnimArea extends Stamp {

    private Animation anim;

    public AnimArea(Handler handler, BufferedImage[] imgs, int x, int y) {
        super(handler, new Rectangle(x, y, imgs[0].getWidth(), imgs[0].getHeight()));
        
        anim = new Animation(1200, imgs);
    }

    @Override
    public void tick() {
        anim.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(anim.getCurrentFrame(),
			bounds.x, bounds.y, 
            bounds.width, bounds.height, null);

    }

}
