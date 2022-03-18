package amgs.ui;

import amgs.gfx.*;
import amgs.utils.Utils;

import java.awt.*;
import java.awt.image.*;

public class UIGeneralButton extends UIObject {

    private BufferedImage[] images;
    private ClickListener clickListener;

    public UIGeneralButton(float x, float y, BufferedImage[] backgroundImages,
        String text, ClickListener clickListener) {
        // width and height from the image used
        super(x, y, backgroundImages[0].getWidth(), backgroundImages[0].getHeight());
        this.clickListener = clickListener;

        // create new images
        images = new BufferedImage[2];
        images[0] = new BufferedImage(backgroundImages[0].getWidth(),
            backgroundImages[0].getHeight(), BufferedImage.TYPE_INT_ARGB);
        images[1] = new BufferedImage(backgroundImages[1].getWidth(),
            backgroundImages[1].getHeight(), BufferedImage.TYPE_INT_ARGB);
        putTextOnImage(text, images, backgroundImages);
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

    private void putTextOnImage(String text, BufferedImage[] images,
            BufferedImage[] backgroundImages) {
        Font font = Assets.minecraftia24;
        for(int i = 0; i < images.length; i++) {
            BufferedImage image = images[i];
            Graphics gImage = image.createGraphics();

            // draw backgroundImage
            gImage.drawImage(backgroundImages[i], 0, 0, null);

            // draw string in center of button
            gImage.setColor(Color.WHITE);
            Rectangle rect = new Rectangle(0, 0, image.getWidth(), image.getHeight());
            Utils.paintCenterString(gImage, text, rect, font);

            gImage.dispose();
        }
        this.images = images;

    }

}