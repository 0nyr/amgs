package amgs.states;

import amgs.*;
import amgs.gfx.Assets;
import amgs.stamps.*;
import amgs.stamps.text.*;
import amgs.stamps.text.TextArea;
import amgs.ui.*;

import java.awt.*;

public class GameOverState extends State {

    private long finalScore;

    private StampManager stampManager;
    private UIManager uiManager;

    private int width, height;

    private TextFieldSave textFieldSave;

    public GameOverState(Handler handler, long finalScore) {
        super(handler);
        this.finalScore = finalScore;

        width = handler.getDisplayWidth();
        height = handler.getDisplayHeight();

        textFieldSave = new TextFieldSave(handler, 
            handler.getDisplayWidth()/2 - TextFieldSave.WIDTH/2, 
            (int)((handler.getDisplayHeight()/4)*3 - TextFieldSave.HEIGHT/2),
            (int) finalScore);

        stampManager = new StampManager(handler);
        initStamps();

        // WARN: Don't forget to add the uiManager to the MouseManager
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager);
        initUiObjects();
    }

    @Override
	public void tick() {
        background.tick();
        textFieldSave.tick();
        stampManager.tick();
        uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
        background.render(g);
        textFieldSave.render(g);
        stampManager.render(g);
        uiManager.render(g);
    }

    private void initStamps() {
        // add title
        // WARN: handler.getDisplayHeight not working
        Rectangle titleBounds = new Rectangle(
            (int)(width/2 - (Constants.TILE_WIDTH*6)/2),
            (int)(height/4), 
            Constants.TILE_HEIGHT*6, Constants.TILE_HEIGHT);
        TitleArea titleStamp = new TitleArea(handler, titleBounds, 
            "GAME OVER", Color.WHITE);
        stampManager.addObject(titleStamp);

        // add description
        Rectangle descriptionBounds = new Rectangle(
            (int)(width/2 - Constants.TILE_HEIGHT*25/2),
            (int)(height/4 + 2*Constants.TILE_HEIGHT),
            Constants.TILE_HEIGHT*25,
            Constants.TILE_HEIGHT);
        String textDescription = 
            "< You can save your new score or go back to main menu >";
        TextArea description = new TextArea(handler, descriptionBounds, 
            textDescription, Color.WHITE);
        stampManager.addObject(description);

        // add final score
        Rectangle scoreBounds = new Rectangle(
            (int)(width/2 - Constants.TILE_HEIGHT*6/2),
            (int)(height/2 - Constants.TILE_HEIGHT),
            Constants.TILE_HEIGHT*6,
            Constants.TILE_HEIGHT);
        TitleArea scoreStamp = new TitleArea(handler, scoreBounds, 
            "< "+finalScore+" >", new Color(101, 221, 248));
        stampManager.addObject(scoreStamp);
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
