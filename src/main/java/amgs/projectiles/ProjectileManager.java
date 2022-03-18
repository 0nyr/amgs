package amgs.projectiles;

import amgs.*;

import java.util.*;
import java.awt.*;

public class ProjectileManager {

    private Handler handler;
    // WARN: Don't forget to create at least an Empty arrayList each time
    private ArrayList<Projectile> projectiles;
    private Comparator<Projectile> renderSorter = new Comparator<Projectile>() {
        // compare projectiles to determine which to render first
        // compare the y coordinate at their bottom (y + height)
        @Override
        public int compare(Projectile a, Projectile b) {
            if((a.getY() + a.getHeight()) < (b.getY() + b.getHeight())) {
                return -1;
            }
            return 1;
        }
    };

    public ProjectileManager(Handler handler) {
        this.handler = handler;
        projectiles = new ArrayList<Projectile>();
    }

    public void tick() {
        Iterator<Projectile> iterator = projectiles.iterator();
        while(iterator.hasNext()) {
            Projectile projectile = iterator.next(); // retreive element
            projectile.tick();
            // check for out of range projectiles and remove them
            if(projectile.isOutOfRange()) {
                iterator.remove(); // remove current projectile
            } else if(projectile.hasAlreadyTouched()) {
                // check for projectile that have already touch and remove
                iterator.remove();
            }
        }
    }

    public void render(Graphics g) {
        for(Projectile projectile : projectiles) {
            projectile.render(g);
        }
        // sort projectile from lower y to higher y value
        projectiles.sort(renderSorter);
    }

    // GETTERS SETTERS
    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

}
