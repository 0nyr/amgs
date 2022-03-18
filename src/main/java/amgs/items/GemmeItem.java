package amgs.items;

import amgs.*;
import amgs.gfx.*;
import amgs.hud.*;

import java.awt.*;

public class GemmeItem extends Item implements PickUpEffects {

    /* NB: when creating a new item:
    -> Add a new entry to Item.createNew() in the switch statement
    -> Extends Item
    -> Implements PickUpEffect
    -> add a new entry and ID in ItemManager.init() */

    public GemmeItem(Handler handler, int id) {
        super(handler, "gemme", id);
        setAnim(new Animation(200, Assets.smallGemme));
        setInternalBounds(new Rectangle(
            (int)(Constants.TILE_WIDTH*(6f/16f)), 
            (int)(Constants.TILE_HEIGHT*(3f/16f)), 
            (int)(Constants.TILE_WIDTH*(6f/16f)), 
            (int)(Constants.TILE_HEIGHT*(10f/16f))
            ));
    }

    public void effectWhenPicked() {
        // add points to score and multiplier
        GameHud gameHud = handler.getWorld().getGameHud();
        gameHud.incrementMultiplier(1);
        gameHud.addToScoreApplyMultiplier(5);

        // play sound
        Assets.gemmePickUpSound.play();
    }

}