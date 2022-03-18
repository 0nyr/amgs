package amgs.stamps;

import amgs.*;
import amgs.gfx.*;
import amgs.scores.HighScore;
import amgs.states.*;

import java.awt.event.*;
import java.awt.*;

public class TextFieldSave extends Stamp {

    public static final int WIDTH = 20*Constants.TILE_WIDTH;
    public static final int HEIGHT = Constants.TILE_HEIGHT;

    private final int MAX_STRING_LENGTH = 32;

    private boolean[] justPressed;
    private String inputString;
    private Font font;
    private Color backgroundColor;
    private int finalScore;

    public TextFieldSave(Handler handler, int x, int y, int finalScore) {
        super(handler, new Rectangle(
            x, y, WIDTH, HEIGHT));
        this.finalScore = finalScore;
        justPressed = handler.getGame().getKeyManager().getJustPressed();
        inputString = "";
        font = Assets.minecraftia34;
        backgroundColor = new Color(15, 91, 123);
    }

    public void tick() {
        addKeyJustPressedToInputString();
    }

    public void render(Graphics g) {
        Graphics gImage = image.createGraphics();

        // paint a colored background and clear last pain
        gImage.clearRect(0, 0, image.getWidth(), image.getHeight());
        gImage.setColor(backgroundColor);
        gImage.fillRect(0, 0, image.getWidth(), image.getHeight());

        // write input string
        gImage.setFont(font);
        gImage.setColor(new Color(101, 221, 248));
        String displayString = "Name : "+inputString;
        gImage.drawString(displayString, 
            (int)(Constants.PIXEL_MULTIPLY*6),
            (int)(Constants.PIXEL_MULTIPLY*20));

        gImage.dispose();

        g.drawImage(image,
            bounds.x, bounds.y, 
            image.getWidth(), image.getHeight(), null);
    }

    private void addKeyJustPressedToInputString() {
        for(int i = 0; i < justPressed.length; i++) {
            if (justPressed[KeyEvent.VK_ENTER]) {
                // Save the inputString to the score file
                HighScore highScore = new HighScore(inputString, finalScore);
                handler.getGame().getScoreManager().addHighScore(highScore);
                handler.getGame().getScoreManager().saveHighScores();
                // go to Highest scores menu
                State scoresMenu = new ScoreState(handler);
                StateManager.setState(scoresMenu);
                return;
            } else if(justPressed[KeyEvent.VK_BACK_SPACE]) {
                // delete last character
                try {
                    inputString = inputString.substring(0, inputString.length()-1);
                } catch (Exception e) {
                    // if string is empty, error can occur
                }
                return;
            } else if(justPressed[i]) {
                if(inputString.length() < MAX_STRING_LENGTH) {
                    // convert the index i into a char 
                char c = Character.toChars(i)[0];
                inputString = inputString+c;
                return;
                }
            }
        }
    }

}
