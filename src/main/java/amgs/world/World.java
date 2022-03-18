package amgs.world;

import amgs.*;
import amgs.background.*;
import amgs.entities.*;
import amgs.entities.creatures.*;
import amgs.entities.flying.*;
import amgs.hud.*;
import amgs.items.*;
import amgs.levels.*;
import amgs.levels.general.*;
import amgs.projectiles.*;

import java.awt.*;

public class World {

    private Handler handler;
    private EntityManager entityManager;
    private ItemManager itemManager;
    private ProjectileManager projectileManager;
    private Player player;
    private GameHud gameHub;
    private Level currentLevel;
    private KrugSpawner krugSpawner;
    private LevelChanger levelChanger;

    // backgrounds
    private Background fixedBg, nebulaeBg, gasCloudsBg;

    public World(Handler handler) {
        this.handler = handler;
    }

    /* WARN: initialisation has to be done outside the constructor since we need to use
        handler.getWorld during the initialisation for many elements. Hence the 
        world element of Handler has to be initialised first in GameState before
        calling this init method */
    public void init(String path) {
        entityManager = new EntityManager(handler);

        itemManager = new ItemManager(handler);

        projectileManager = new ProjectileManager(handler);

        gameHub = new GameHud(handler);
        gameHub.nextWaveInit();

        currentLevel = new GeneralLevel(handler);

        krugSpawner = new KrugSpawner(handler);

        player = new Player(
            handler, 
            currentLevel.getSpawnPlayerXInTile()*Constants.TILE_WIDTH, 
            currentLevel.getSpawnPlayerYInTile()*Constants.TILE_HEIGHT);
        entityManager.addEntity(player); 
        
        fixedBg = new FixedBg(handler);
        gasCloudsBg = new GasCloudsBg(handler);
        nebulaeBg = new NebulaeBg(handler);

        levelChanger = new LevelChanger(handler);
    }

    public void tick() {
        fixedBg.tick();
        gasCloudsBg.tick();
        nebulaeBg.tick();
        currentLevel.tick();
        itemManager.tick();
        entityManager.tick();
        projectileManager.tick();
        gameHub.tick();
        // objects with tick() method only
        krugSpawner.tick();
        levelChanger.tick();
    }

    public void render(Graphics g) {
        // render backgrounds
        fixedBg.render(g);
        gasCloudsBg.render(g);
        nebulaeBg.render(g);

        // render tiles
        currentLevel.render(g);

        // render items
        itemManager.render(g);

        // render entities
        entityManager.render(g);

        // render projectiles
        projectileManager.render(g);

        // render HUD
        gameHub.render(g);
    }

    public void changeToNextLevel() {
        // continuity between levels
        gameHub.nextWaveInit();

        // create new instances
        entityManager = new EntityManager(handler);

        itemManager = new ItemManager(handler);

        projectileManager = new ProjectileManager(handler);

        currentLevel = new GeneralLevel(handler);

        krugSpawner = new KrugSpawner(handler);

        player = new Player(
            handler, 
            currentLevel.getSpawnPlayerXInTile()*Constants.TILE_WIDTH, 
            currentLevel.getSpawnPlayerYInTile()*Constants.TILE_HEIGHT);
        entityManager.addEntity(player);
        
        fixedBg = new FixedBg(handler);
        gasCloudsBg = new GasCloudsBg(handler);
        nebulaeBg = new NebulaeBg(handler);

        // not sure will work
        levelChanger = new LevelChanger(handler);
    }

    // GETTERS SETTERS
    public Level getCurrentLevel() {
        return currentLevel;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public ItemManager getItemManager() {
        return itemManager;
    }

    public Player getPlayer() {
        return player;
    }

    public GameHud getGameHud() {
        return gameHub;
    }

    public ProjectileManager getProjectileManager() {
        return projectileManager;
    }

    public LevelChanger getLevelChanger() {
        return levelChanger;
    }

}
