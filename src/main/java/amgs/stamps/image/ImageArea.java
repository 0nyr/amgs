package amgs.stamps.image;

import amgs.*;
import amgs.stamps.*;

import java.awt.*;
import java.awt.image.*;

public class ImageArea extends Stamp {

    public ImageArea(Handler handler, BufferedImage img, int x, int y) {
        super(handler, new Rectangle(x, y, img.getWidth(), img.getHeight()));
        this.image = img;
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image,
            bounds.x, bounds.y, 
            image.getWidth(), image.getHeight(), null);
    }

}
