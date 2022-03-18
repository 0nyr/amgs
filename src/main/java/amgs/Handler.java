package amgs;

import amgs.world.*;
import amgs.display.*;
import amgs.gfx.*;
import amgs.input.*;

import java.awt.*;

// WARN: Cannot use Handler before world is constructed in GameState

public class Handler {

    private Game game;
    private World world;

    public Handler(Game game) {
        this.game = game;
    }

    // getters
    public Game getGame() {
        return game;
    }

    public World getWorld() {
        return world;
    }

    public Display getDisplay() {
        return game.getDisplay();
    }

    public int getDisplayWidth() {
        return game.getDisplay().getWidth();
    }

    public int getDisplayHeight() {
        return game.getDisplay().getHeight();
    }

    public int getScreenWidth() {
        Dimension screenSize = Toolkit
                    .getDefaultToolkit().getScreenSize();
        return screenSize.width;
    }

    public int getScreenHeight() {
        Dimension screenSize = Toolkit
                    .getDefaultToolkit().getScreenSize();
        return screenSize.height;
    }

    public GameCamera getGameCamera() {
        return game.getGameCamera();
    }

    public KeyManager getKeyManager() {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager() {
        return game.getMouseManager();
    }

    //setters
    // WARN: we cannot initialise world in the constructor !
    public void setWorld(World world) {
        this.world = world;
    }
}