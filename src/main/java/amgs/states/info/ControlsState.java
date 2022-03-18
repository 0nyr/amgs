package amgs.states.info;

import amgs.Handler;
import amgs.gfx.*;
import amgs.stamps.*;
import amgs.stamps.image.*;
import amgs.states.*;
import amgs.ui.*;

public class ControlsState extends InfoState {

    public ControlsState(Handler handler) {
        super(handler);
    }

    @Override
    protected void initStamps() {
        // add controls cover anim
        Stamp controlsCover = new AnimArea(handler, Assets.controls, 
			(int)(handler.getDisplayWidth()/2 - Assets.controls[0].getWidth()/2),
			(int)(handler.getDisplayHeight()/2 - Assets.controls[0].getHeight()/2));
		stampManager.addObject(controlsCover);
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