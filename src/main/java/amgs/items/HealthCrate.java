package amgs.items;

import amgs.*;
import amgs.gfx.*;
import amgs.hud.*;

import java.awt.*;

public class HealthCrate extends Item implements PickUpEffects {

    public HealthCrate(Handler handler, int id) {
        super(handler, "health crate", id);
        setAnim(new Animation(200, Assets.healthCrate));
        setInternalBounds(new Rectangle(
            (int)(Constants.TILE_WIDTH*(1f/16f)), 
            (int)(Constants.TILE_HEIGHT*(2f/16f)), 
            (int)(Constants.TILE_WIDTH*(14f/16f)), 
            (int)(Constants.TILE_HEIGHT*(12f/16f))
            ));
    }

    public void effectWhenPicked() {
        // add points to score and health
        GameHud gameHud = handler.getWorld().getGameHud();
        gameHud.addToScoreApplyMultiplier(5);
        gameHud.addHealth(10);

        // play sound
        Assets.healthCratePickUpSound.play();
    }

}