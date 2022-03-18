package amgs.stamps.text;

import amgs.stamps.*;
import amgs.*;
import amgs.gfx.*;
import amgs.utils.*;

import java.awt.*;
import java.awt.image.*;

public class TextArea extends Stamp {
    // a text area with transparent background

    protected Font font;

    public TextArea(Handler handler, Rectangle bounds, String text, Color textColor) {
        super(handler, bounds);
        font = Assets.minecraftia24;
        image = initImage(image, text, textColor);
    }

    public TextArea(Handler handler, Rectangle bounds, String text, Color backgroundColor ,Color textColor) {
        super(handler, bounds);
        font = Assets.minecraftia24;
        image = paintBackgroundColor(image, backgroundColor);
        image = initImage(image, text, textColor);
    }

    public TextArea(Handler handler, Rectangle bounds, String text, Color textColor, Font font) {
        super(handler, bounds);
        this.font = font;
        image = initImage(image, text, textColor);
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

    protected BufferedImage initImage(BufferedImage img, String text, Color textColor) {
        Graphics gImg = img.createGraphics();

        // write text at center of img
        gImg.setColor(textColor);
        //Utils.drawCenteredString(gImg, text, bounds, font);
        Rectangle rect = new Rectangle(0, 0, bounds.width, bounds.height);
        Utils.paintCenterString(gImg, text, rect, font);

        return img;
    }

    protected BufferedImage paintBackgroundColor(BufferedImage img, Color backgroundColor) {
        Graphics gImg = img.createGraphics();

        // paint background
        gImg.setColor(backgroundColor);
        gImg.fillRect(0, 0, image.getWidth(), image.getHeight());

        return img;
    }

}
