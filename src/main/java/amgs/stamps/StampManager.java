package amgs.stamps;

import amgs.*;

import java.util.*;
import java.awt.*;

public class StampManager {

    private Handler handler;
    private ArrayList<Stamp> stamps;

    public StampManager(Handler handler) {
        this.handler = handler;
        stamps = new ArrayList<Stamp>();
    }

    public void tick() {
        for(Stamp object : stamps) {
            object.tick();
        }
    }

    public void render(Graphics g) {
        for(Stamp object : stamps) {
            object.render(g);
        }
    }

    public void addObject(Stamp object) {
        stamps.add(object);
    }

    public void removeObject(Stamp object) {
        stamps.remove(object);
    }

}
