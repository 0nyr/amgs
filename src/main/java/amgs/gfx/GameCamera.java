package amgs.gfx;

import amgs.*;
import amgs.display.Display;
import amgs.entities.*;

public class GameCamera {

    // how far of do we draw tiles from their original position
    private float xOffset, yOffset;
    private Handler handler;

    public GameCamera(Handler handler, float xOffset, float yOffset) {
        this.handler = handler;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void move(float xAmt, float yAmt) {
        xOffset += xAmt;
        yOffset += yAmt;
        checkBlankSpace();
    }

    public void centerOnEntity(Entity entity) {
        Display display = handler.getDisplay();
        // WARN: center on the center of the entity !, not its default value
        xOffset = entity.getX() - display.getWidth()/2 + entity.getWidth()/2;
        yOffset = entity.getY() - display.getHeight()/2 + entity.getHeight()/2;
        checkBlankSpace();
    }

    private void checkBlankSpace() {
        // limit the camera to the game area, NOT USED HERE
        if(Constants.isCameraLimitedToGameArea) {
            if(xOffset < 0) {
                xOffset = 0;
            } else if(xOffset > 
                handler.getWorld().getCurrentLevel()
                .getLevelWidthInTile()*Constants.TILE_WIDTH 
                - handler.getDisplayWidth()) 
                    {
                xOffset = handler.getWorld().getCurrentLevel()
                .getLevelWidthInTile()*Constants.TILE_WIDTH 
                    - handler.getDisplayWidth();
            }
    
            if(yOffset < 0) {
                yOffset = 0;
            } else if(yOffset > handler.getWorld().getCurrentLevel()
                .getLevelHeightInTile()*Constants.TILE_HEIGHT
                - handler.getDisplayHeight())
                    {
                yOffset = handler.getWorld().getCurrentLevel()
                    .getLevelHeightInTile()*Constants.TILE_HEIGHT
                    - handler.getDisplayHeight();
            }
        }
    }

    // getters and setters
    public float getXOffset() {
        return xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
