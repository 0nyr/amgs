package amgs.entities.flying;

import amgs.*;
import amgs.entities.EntityManager;
import amgs.levels.*;

public class KrugSpawner {

    private Handler handler;
    private long lastTimer, cooldown, deltaTimer;
    private double minRadiusSpawn;
    private double xCenterMap, yCenterMap;
    private EntityManager entityManager;

    public KrugSpawner(Handler handler) {
        this.handler = handler;
        cooldown = handler.getWorld().getGameHud().getTimeBetweenPop();
        deltaTimer = 0; // don't allow spawn from start

        minRadiusSpawn = ((LevelGenerator.BASE_RADIUS + 
            handler.getWorld().getGameHud().getWave())*2 
            )*Constants.TILE_WIDTH 
            + LevelGenerator.OVAL_CORRECTOR*Constants.TILE_WIDTH/2;
        xCenterMap = ((LevelGenerator.BASE_RADIUS + 
        handler.getWorld().getGameHud().getWave())*2 
        )*Constants.TILE_WIDTH 
        + LevelGenerator.OVAL_CORRECTOR*Constants.TILE_WIDTH/2;
        yCenterMap = xCenterMap;
        
        entityManager = handler.getWorld().getEntityManager();
    }

    public void tick() {
        spawnIfPossible();
    }

    private void spawnIfPossible() {
		deltaTimer += System.currentTimeMillis() - lastTimer;
		lastTimer = System.currentTimeMillis();
		if(deltaTimer < cooldown) {
			return;	// return if too short after previous spawn
        }
        if(!handler.getWorld().getGameHud().canSpawnEnemy()) {
            return; // all enemies have already spawn
        }

        deltaTimer = 0;
        handler.getWorld().getGameHud().oneEnemyHasJustSpawned();
        // get random circular coordinates
        double randomAngle = Math.random()*Math.PI*2;
        double randomDistance = - Math.random()*100;
        double distance = minRadiusSpawn + randomDistance;
        // convert to castesian
        double xFromMapCenter = distance*Math.cos(randomAngle);
        double yFromMapCenter = distance*Math.sin(randomAngle);
        double x = xCenterMap + xFromMapCenter;
        double y = yCenterMap + yFromMapCenter;
        entityManager.addEntity(new Krug(handler, (int) x, (int) y));

	}

}
