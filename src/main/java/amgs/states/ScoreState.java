package amgs.states;

import amgs.Constants;
import amgs.Handler;
import amgs.gfx.*;
import amgs.stamps.*;
import amgs.stamps.text.*;
import amgs.ui.*;

import java.awt.*;

public class ScoreState extends State {

    private StampManager stampManager;
    private UIManager uiManager;

    public ScoreState(Handler handler) {
        super(handler);

        stampManager = new StampManager(handler);
        initHighestScoresDisplay();

        // WARN: Don't forget to add the uiManager to the MouseManager
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
        initUiObjects();
    }

    @Override
    public void tick() {
        background.tick();
        stampManager.tick();
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        background.render(g);
        stampManager.render(g);
        uiManager.render(g);
    }

    private void initHighestScoresDisplay() {
        // add all the high score stamps
        String[] allScoreStrings = handler.getGame().getScoreManager()
            .get10HighestScoresToStrings();
        for(int i = 0; i < allScoreStrings.length; i++) {
            int x = handler.getDisplayWidth()/2 - ScoreArea.WIDTH/2;
            int y = (handler.getDisplayHeight()/11)*(i + 1);
            String text = allScoreStrings[i];
            Color backgroundColor = new Color(22, 133 - 5*i, 177 - 10*i);
            Color color = Color.WHITE;
            ScoreArea scoreArea = new ScoreArea(handler, x, y, text, backgroundColor, color);
            stampManager.addObject(scoreArea);
        }

        // add title
        Rectangle titleBounds = new Rectangle(
            handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*6/2,
            20,
            Constants.TILE_HEIGHT*6,
            Constants.TILE_HEIGHT);
        TitleArea titleStamp = new TitleArea(handler, titleBounds, 
            "HIGH SCORES", new Color(101, 221, 248));
        stampManager.addObject(titleStamp);
    }

    private void initUiObjects() {
        // add "go to main menu" button
		UIObject backToMainMenuButton = new UIGeneralButton(
			20,
			20,
			Assets.button,
			"> Menu",
			new ClickListener() {

				@Override
				public void onClick() {
                    alwaysOnClick();
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					// Return to main menu
                    State menuState = new MenuState(handler);
                    StateManager.setState(menuState);
                }
                
                @Override
				public void alwaysOnClick() {
					// play sound
					Assets.buttonClickSound.play();
				}
			});
		uiManager.addObject(backToMainMenuButton);
    }

}
