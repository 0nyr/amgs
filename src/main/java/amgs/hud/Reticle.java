package amgs.hud;

import amgs.*;
import amgs.display.*;
import amgs.gfx.*;
import amgs.input.*;

import java.awt.*;
import java.awt.image.*;

public class Reticle {

    private double maxDistance = 100;

    private Handler handler;
    private BufferedImage[] reticles; // [0] can shoot, [1] shoot not possible
    private boolean canShoot; // 0 yes, 1 no
    private MouseManager mouseManager;
    private Display display;
    private double dx, dy; // algebric length between mouse and center screen
    private double angle; // angle between mouse and center screen
    private double distance; // distance between mouse and center scren
    private int x, y; // reticle position

    public Reticle(Handler handler) {
        this.handler = handler;
        mouseManager = handler.getGame().getMouseManager();
        display = handler.getDisplay();
        reticles = Assets.reticles;
        canShoot = true;
    }

    public void tick() {
        setDxDy();
        getMouseAngleWithCenterScreen();
        getMouseDistanceWithCenterScreen();
        limitDistanceAndComputeXY();
    }

    public void render(Graphics g) {
        int ReticleSelector = (canShoot) ? 0 : 1;
        BufferedImage reticle = reticles[ReticleSelector];
        g.drawImage(reticle,
			(int)(x - reticle.getWidth()/2), 
			(int)(y - reticle.getHeight()/2), 
            Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);
    }

    private void setDxDy() {
        dx = mouseManager.getMouseX() - display.getWidth()/2;
        dy = mouseManager.getMouseY() - display.getHeight()/2;
    }

    private void getMouseAngleWithCenterScreen() {
        angle = Math.atan2(dy, dx);
    }

    private void getMouseDistanceWithCenterScreen() {
        distance = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    private void limitDistanceAndComputeXY() {
        if(distance < maxDistance) {
            x = mouseManager.getMouseX();
            y = mouseManager.getMouseY();
        } else {
            x = (int)(display.getWidth()/2 + maxDistance*Math.cos(angle));
            y = (int)(display.getHeight()/2 + maxDistance*Math.sin(angle));
        }
    }

    // GETTERS SETTERS
    public double getAngle() {
        return angle;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

}
