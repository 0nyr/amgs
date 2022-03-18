package amgs.stamps.text;

import amgs.*;

import java.awt.*;

public class ScoreArea extends TextArea {

    public static final int WIDTH = 20*Constants.TILE_WIDTH;
    public static final int HEIGHT = Constants.TILE_HEIGHT;

    public ScoreArea(Handler handler, int x, int y, String text, Color backgroundColor, Color color) {
        super(handler, new Rectangle(x, y, WIDTH, HEIGHT), text, backgroundColor, color);
    }

    @Override
    public void tick() {

    }

}
