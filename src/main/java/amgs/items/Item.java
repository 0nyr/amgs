package amgs.items;

import amgs.*;
import amgs.gfx.*;

import java.awt.*;

public class Item {

    protected Handler handler;
    protected String name;
    protected final int id;
    protected Animation anim;
    protected boolean pickedUp;
    protected int count;
    protected int x, y;
    protected Rectangle bounds; // NB: bounds.x and y at REAL position
    protected Rectangle internalBounds; // represent offset inside sprite for bounding box

    public Item(Handler handler, String name, int id) {
        this.handler = handler;
        this.name = name;
        this.id = id;
        count = 1;
        pickedUp = false;
        bounds = new Rectangle();
        internalBounds = new Rectangle();

        ItemManager.idToitems[id] = this;
    }
    
    public void tick() {
        if(anim == null) {
            return;
        }
        anim.tick();
        detectItemPick();
    }

    public void render(Graphics g) {
        if(handler == null) {
            return;
        }
        if(anim == null) {
            return;
        }
        g.drawImage(anim.getCurrentFrame(),
            (int)(x - handler.getGameCamera().getXOffset()), 
            (int)(y - handler.getGameCamera().getYOffset()), 
            anim.getCurrentFrame().getWidth(), 
            anim.getCurrentFrame().getHeight(), null);
        testDisplayBounds(g);
    }

    private void detectItemPick() {
        if(handler.getWorld().getPlayer()
            .getCollisionBounds(0f, 0f).intersects(bounds)) {
            pickedUp = true;
            handler.getWorld().getPlayer().getInventory().addItem(this);
            if(this instanceof PickUpEffects) {
                PickUpEffects pe = (PickUpEffects)this;
                pe.effectWhenPicked();
            }
        }
    }

    private void testDisplayBounds(Graphics g) {
		if(Constants.isTest) { 
            g.setColor(Color.blue);
            g.fillRect(
                (int)(bounds.x - handler.getGameCamera().getXOffset()),
                (int)(bounds.y - handler.getGameCamera().getYOffset()),
                bounds.width, bounds.height);
		}
	}

    // GETTERS SETTERS
    public void setAnim(Animation anim) {
        this.anim = anim;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setInternalBounds(Rectangle internalBounds) {
        this.internalBounds = internalBounds;
    }

    public Rectangle getInternalBounds() {
        return internalBounds;
    }

    public void setWidthHeight(int width, int height) {
        bounds.width = width;
        bounds.height = height;
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // create copy of current item
    public Item createNew(int x, int y) {
        Item item;
        switch(name) {
            case "gemme":   item = new GemmeItem(handler, id);
                break;
            case "health crate": item = new HealthCrate(handler, id);
                break;
            default:        item = new Item(handler, name, id);
                break;
        }
        item.setAnim(anim);
        item.setInternalBounds(internalBounds);
        item.setPosition(x, y);
        // The new bounds rectangle has to be modified with new x and y
        Rectangle newBounds = new Rectangle();
        newBounds.x = x + internalBounds.x;
        newBounds.y = y + internalBounds.y;
        newBounds.width = internalBounds.width;
        newBounds.height = internalBounds.height;
        item.setBounds(newBounds);
        return item;
    }

}