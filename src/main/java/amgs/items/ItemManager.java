package amgs.items;

import amgs.*;
import amgs.tiles.*;

import java.util.*;
import java.awt.*;

public class ItemManager {

    // associates an Item to an Id
    public static Item[] idToitems = new Item[256];
    public Item gemmeItem, healthCrateItem;

    private void init() {
        gemmeItem = new GemmeItem(handler, 7);
        healthCrateItem = new HealthCrate(handler, 8);
    }

    // item manager
    private Handler handler;
    private ArrayList<Item> items;

    public ItemManager(Handler handler) {
        this.handler = handler;
        init();
        items = new ArrayList<Item>();
    }

    public void tick() {
        Iterator<Item> iterator = items.iterator();
        while(iterator.hasNext()) {
            Item item = iterator.next();
            item.tick();
            if(item.isPickedUp()) {
                iterator.remove();
            } else if(itemIsNotOnWalkableTile(item)) {
                iterator.remove();
            }
        }
    }

    public void render(Graphics g) { 
        for(Item item : items) {
            item.render(g);
        }
    }

    private boolean itemIsNotOnWalkableTile(Item item) {
        // detect if item is on not walkable tile
        int x =  (int)((item.getX())/Constants.TILE_WIDTH);
        int y = (int)((item.getY())/Constants.TILE_HEIGHT);
        Tile tile = handler.getWorld().getCurrentLevel().getTile(x, y);
        boolean notWalkable = tile.isSolid();
        return notWalkable;
    }

    // GETTERS SETTERS
    public void addItem(Item item) {
        items.add(item);
    }
    

}
