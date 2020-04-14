package amgs.entities;

import amgs.*;

import java.util.*;
import java.awt.*;

public class EntityManager {

    private Handler handler;
    // WARN: Don't forget to create at least an Empty arrayList each time
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        // compare entities to determine which to render first
        // compare the y coordinate at their bottom (y + height)
        @Override
        public int compare(Entity a, Entity b) {
            if((a.getY() + a.getHeight()) < (b.getY() + b.getHeight())) {
                return -1;
            }
            return 1;
        }
    };

    public EntityManager(Handler handler) {
        this.handler = handler;
        entities = new ArrayList<Entity>();
    }

    public void tick() {
        for(int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            entity.tick();
        }

    }

    public void render(Graphics g) {
        for(Entity entity : entities) {
            entity.render(g);
        }
        // sort entity from lower y to higher y value
        entities.sort(renderSorter);
    }

    // GETTERS SETTERS
    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

}