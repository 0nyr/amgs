package amgs.items;

import amgs.*;

import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Inventory {

    private Handler handler;
    private ArrayList<Item> inventoryItems;

    private boolean active;
    
    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<Item>();
        active = false;
    }

    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) {
            active = !active;
        }
        if (!active) {
            return;
        }

        System.out.println("");
        System.out.println("### INVENTORY ###");
        for(Item item : inventoryItems) {
            System.out.println(item.getName()+" "+item.getCount());
        }

    }

    public void render(Graphics g) {

    }

    public void addItem(Item newItem) {
        for(Item item : inventoryItems) {
            if(item.getId() == newItem.getId()) {
                item.setCount(item.getCount() + newItem.getCount());
                return;
            }
        }
        // the item is not already in the inventory
        inventoryItems.add(newItem);
    }

}
