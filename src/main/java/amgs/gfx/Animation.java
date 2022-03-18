package amgs.gfx;

import java.awt.image.*;

public class Animation {

    private int speed, index;
    private long lastTime, deltaTimeSinceLastTick;
    private BufferedImage[] frames;

    public Animation(int speed, BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        deltaTimeSinceLastTick = 0;
        lastTime = System.currentTimeMillis();
    }

    public void tick() {
        deltaTimeSinceLastTick += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(deltaTimeSinceLastTick > speed) {
            index++;
            deltaTimeSinceLastTick = 0;
            if(index >= frames.length) {
                index = 0;
            }
        }
    }

    public BufferedImage getCurrentFrame() {
        return frames[index];
    }
}
