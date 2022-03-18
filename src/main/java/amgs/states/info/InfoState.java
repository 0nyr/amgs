package amgs.states.info;

import amgs.Handler;
import amgs.stamps.*;
import amgs.states.*;
import amgs.ui.*;

import java.awt.*;

public abstract class InfoState extends State {
    
    protected StampManager stampManager;
    protected UIManager uiManager;

    public InfoState(Handler handler) {
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

    protected abstract void initStamps();

    protected abstract void initUiObjects();

}
