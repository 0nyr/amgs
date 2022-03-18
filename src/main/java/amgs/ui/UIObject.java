package amgs.ui;

import java.awt.*;
import java.awt.event.*;

import amgs.gfx.Assets;

public abstract class UIObject {

    protected float x, y;
    protected int width, height;
    protected Rectangle bounds;
    protected boolean hovering;
    protected boolean wasHovering;

    public UIObject(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        hovering = false;
        wasHovering = false;
        bounds = new Rectangle((int) x, (int) y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void onClick();

    public void onMouseMove(MouseEvent e) {
        if(bounds.contains(e.getX(), e.getY())) {
            hovering = true;
            if(!wasHovering) {
                wasHovering = true;

                // play sound
                Assets.buttonHoverSound.play();
            }
        } else {
            hovering = false;
            wasHovering = false;
        }
    }

    public void onMouseRelease(MouseEvent e) {
        // hovering over UIObject a,d mouse is release = click
        if(hovering) {
            onClick();
        }
    }

}