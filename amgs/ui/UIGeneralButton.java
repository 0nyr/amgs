package amgs.ui;

import amgs.gfx.*;

import java.awt.*;
import java.awt.image.*;

public class UIGeneralButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clickListener;

    public UIGeneralButton(float x, float y, BufferedImage[] images,
        String text, ClickListener clickListener) {
        // width and height from the image used
        super(x, y, images[0].getWidth(), images[0].getHeight());
        this.clickListener = clickListener;
        putTextOnImage(text, images);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        if(hovering) {
            g.drawImage(images[1], (int) x, (int) y, null);
        } else {
            g.drawImage(images[0], (int) x, (int) y, null);
        }
    }

    @Override
    public void onClick() {
        clickListener.onClick();
    }

    private void putTextOnImage(String text, BufferedImage[] images) {
        Font font = Assets.minecraftia24;
        for(BufferedImage image : images) {
            Graphics gImage = image.createGraphics();
            gImage.setFont(font);
            FontMetrics fontMetrics = gImage.getFontMetrics();
            gImage.setColor(Color.WHITE);
            gImage.drawString(text, 50, fontMetrics.getAscent() + 10);
            gImage.dispose();
        }
        this.images = images;

    }

}