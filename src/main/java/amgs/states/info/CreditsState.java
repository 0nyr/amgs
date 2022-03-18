package amgs.states.info;

import amgs.Constants;
import amgs.Handler;
import amgs.gfx.*;
import amgs.stamps.text.*;
import amgs.stamps.text.TextArea;
import amgs.states.*;
import amgs.ui.*;

import java.awt.*;

public class CreditsState extends InfoState {

    public CreditsState(Handler handler) {
        super(handler);
    }

    @Override
    protected void initStamps() {
        // add title
        Rectangle titleBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*6/2),
            (int)(handler.getDisplayHeight()/5),
            Constants.TILE_HEIGHT*6,
            Constants.TILE_HEIGHT);
        TitleArea titleStamp = new TitleArea(handler, titleBounds, 
            "CREDITS", new Color(101, 221, 248));
        stampManager.addObject(titleStamp);

        // add description
        Rectangle descriptionBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*25/2),
            (int)((handler.getDisplayHeight()/5)*2),
            Constants.TILE_HEIGHT*25,
            Constants.TILE_HEIGHT);
        String textDescription = 
            "< An INSA Lyon SCAN 2A computer science project by >";
        TextArea description = new TextArea(handler, descriptionBounds, 
            textDescription, Color.WHITE);
        stampManager.addObject(description);

        int textWidth = 20;

        // add Onyr
        Rectangle onyrBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*textWidth/2),
            (int)((handler.getDisplayHeight()/5)*3),
            Constants.TILE_HEIGHT*20,
            Constants.TILE_HEIGHT);
        String onyrText = 
            "Onyr / Florian Rascoussier --- Lead Dev, Artist, Sound";
        TextArea onyr = new TextArea(handler, onyrBounds, 
            onyrText, new Color(68, 5, 5), new Color(221, 54, 54));
        stampManager.addObject(onyr);

        // add Martin
        Rectangle martinBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*textWidth/2),
            (int)((handler.getDisplayHeight()/5)*3 + Constants.TILE_HEIGHT + 20),
            Constants.TILE_HEIGHT*20,
            Constants.TILE_HEIGHT);
        String martinText = 
            "Martin Mallet - Dev";
        TextArea martin = new TextArea(handler, martinBounds, 
            martinText, new Color(27, 46, 54), new Color(39, 143, 187));
        stampManager.addObject(martin);

        // add Gregoire
        Rectangle gregoireBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*textWidth/2),
            (int)((handler.getDisplayHeight()/5)*3 + Constants.TILE_HEIGHT*2 + 20*2),
            Constants.TILE_HEIGHT*20,
            Constants.TILE_HEIGHT);
        String gregoireText = 
            "Gregoire Claus - Dev";
        TextArea gregoire = new TextArea(handler, gregoireBounds, 
            gregoireText, new Color(27, 46, 54), new Color(39, 143, 187));
        stampManager.addObject(gregoire);

        // add musics credits
        Rectangle musicsBounds = new Rectangle(
            (int)(handler.getDisplayWidth()/2 - Constants.TILE_HEIGHT*textWidth/2),
            (int)((handler.getDisplayHeight()/5)*3 + Constants.TILE_HEIGHT*3 + 20*3),
            Constants.TILE_HEIGHT*20,
            Constants.TILE_HEIGHT);
        String musicsText = 
            "Remix <Escape from Antarctica> by OSCILLIAN feat. ULTRABOSS";
        TextArea musicsCredits = new TextArea(handler, musicsBounds, 
            musicsText, Color.WHITE);
        stampManager.addObject(musicsCredits);
    }

    @Override
    protected void initUiObjects() {
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
