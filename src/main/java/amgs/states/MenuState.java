package amgs.states;

import amgs.*;
import amgs.ui.*;
import amgs.gfx.*;
import amgs.stamps.*;
import amgs.stamps.image.*;
import amgs.states.info.*;

import java.awt.*;

public class MenuState extends State {

	private StampManager stampManager;
	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);

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
		stampManager.tick();
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		background.render(g);
		stampManager.render(g);
		uiManager.render(g);
	}

	private void initStamps() {
		// add title cover image
		Stamp titleCover = new AnimArea(handler, Assets.titleScreen, 
			(int)(handler.getDisplayWidth()/2 - Assets.titleScreen[0].getWidth()/2),
			(int)(handler.getDisplayHeight()/2 - Assets.titleScreen[0].getHeight()/2));
		stampManager.addObject(titleCover);
	}

	private void initUiObjects() {
	// add start button
		UIObject startButton = new UIGeneralButton(
			handler.getDisplayWidth() - Assets.button[0].getWidth() - 20,
			20,
			Assets.button,
			"START",
			new ClickListener() {

				@Override
				public void onClick() {
					alwaysOnClick();
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					//StateManager.setState(handler.getGame().gameState);
					State gameState = new GameState(handler);
					StateManager.setState(gameState);
				}

				@Override
				public void alwaysOnClick() {
					// play sound
					Assets.buttonClickSound.play();
				}
			});
		uiManager.addObject(startButton);

		// add highest scores button
		UIObject highestScoresButton = new UIGeneralButton(
			20,
			20,
			Assets.button,
			"Scores",
			new ClickListener() {

				@Override
				public void onClick() {
					alwaysOnClick();
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					// go to Highest scores menu
					State scoresMenu = new ScoreState(handler);
					StateManager.setState(scoresMenu);
				}

				@Override
				public void alwaysOnClick() {
					// play sound
					Assets.buttonClickSound.play();
				}
			});
		uiManager.addObject(highestScoresButton);

		// add credits button
		UIObject creditsButton = new UIGeneralButton(
			Assets.button[0].getWidth() + 20*2,
			20,
			Assets.button,
			"Credits",
			new ClickListener() {

				@Override
				public void onClick() {
					alwaysOnClick();
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					// go to Highest scores menu
					State creditsMenu = new CreditsState(handler);
					StateManager.setState(creditsMenu);
				}

				@Override
				public void alwaysOnClick() {
					// play sound
					Assets.buttonClickSound.play();
				}
			});
		uiManager.addObject(creditsButton);

		// add controls button
		UIObject controlsButton = new UIGeneralButton(
			Assets.button[0].getWidth()*2 + 20*3,
			20,
			Assets.button,
			"Controls",
			new ClickListener() {

				@Override
				public void onClick() {
					alwaysOnClick();
					// WARN: Don't forget to unset the UIManager !
					handler.getMouseManager().setUIManager(null);
					// go to Highest scores menu
					State controlsMenu = new ControlsState(handler);
					StateManager.setState(controlsMenu);
				}

				@Override
				public void alwaysOnClick() {
					// play sound
					Assets.buttonClickSound.play();
				}
			});
		uiManager.addObject(controlsButton);
	}

}
