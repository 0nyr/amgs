package amgs.background;

import amgs.*;
import amgs.gfx.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GasCloudsBg extends Background {

    public GasCloudsBg(Handler handler) {
        super(handler);
        setAnimBg(new Animation(1200, Assets.gasCloudsBg));
    }

    public void tick() {
        animBg.tick();
    }

    public void render(Graphics g) {
        BufferedImage currentFrame = animBg.getCurrentFrame();
        g.drawImage(currentFrame,
            (int)(handler.getScreenWidth()/2 - currentFrame.getWidth()/2
            - handler.getGameCamera().getXOffset()/4
            + handler.getWorld().getSpawnPlayerX()),
            (int)(handler.getScreenHeight()/2 - currentFrame.getHeight()/2
            - handler.getGameCamera().getYOffset()/4
            + handler.getWorld().getSpawnPlayerY()),
            currentFrame.getWidth(),
            currentFrame.getHeight(), null);
    }

}