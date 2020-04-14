package amgs.states;

import amgs.*;
import amgs.ui.*;
import amgs.gfx.*;

import java.awt.*;

public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		// WARN: Don't forget to add the uiManager to the MouseManager
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUIManager(uiManager); 

		// add uiObjects
		UIObject startButton = new UIGeneralButton(
			handler.getDisplayWidth()/2 - Assets.button[0].getWidth()/2,
			handler.getDisplayHeight()/2 - Assets.button[0].getHeight()/2,
			Assets.button,
			"START",
			new ClickListener() {

				@Override
				public void onClick() {
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					StateManager.setState(handler.getGame().gameState);
				}
			});
		uiManager.addObject(startButton);
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}

}
