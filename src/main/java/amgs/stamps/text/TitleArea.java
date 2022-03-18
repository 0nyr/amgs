package amgs.stamps.text;

import amgs.Handler;
import amgs.gfx.Assets;

import java.awt.*;

public class TitleArea extends TextArea {

    public TitleArea(Handler handler, Rectangle bounds, String text, Color textColor) {
        super(handler, bounds, text, textColor, Assets.minecraftia34);
    }

}