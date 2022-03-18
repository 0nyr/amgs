package amgs.hud;

import amgs.*;
import amgs.gfx.*;
import amgs.states.*;

import java.awt.*;
import java.awt.image.*;

public class GameHud {

    private Handler handler;
    private BufferedImage hudBackground;
    private BufferedImage hud;

    // GAME INFO
    private int health;
    private long score;
    private int multiplier;
    private int wave;
    private int totalKilledEnemies;
    private int enemySpawned;
    private int totalWaveEnemies;
    private int timeBetweenPop;

    public GameHud(Handler handler) {
        this.handler = handler;
        hudBackground = Assets.hudBackground;
        hud = new BufferedImage(
            hudBackground.getWidth(), 
            hudBackground.getHeight(), 
            BufferedImage.TYPE_INT_ARGB);
        wave = 0;
        totalKilledEnemies = 0;
        health = 70;
        multiplier = 1;
        enemySpawned = 0;
        totalWaveEnemies = 0;
        timeBetweenPop = 1000;
        score = 0;
    }

    public void tick() {
        detectGameOver();
    }

    public void render(Graphics g) {
        hud = clearAndInit(hud);
        hud = displayHealthBar(hud);
        hud = displayInfo(hud);
        g.drawImage(hud, 20, 20, null);
    }

    private void detectGameOver() {
        if(health <= 0) {
            State gameOver = new GameOverState(handler, score);
            StateManager.setState(gameOver);
        }
    }

    private BufferedImage clearAndInit(BufferedImage image) {
        Graphics gImage = image.createGraphics();
        //gImage.clearRect(0, 0, image.getWidth(), image.getHeight());
        gImage.setColor(new Color(242, 138, 138));
        gImage.drawImage(hudBackground, 0, 0, image.getWidth(), image.getHeight(), null);
        return image;
    }
    
    private BufferedImage displayHealthBar(BufferedImage image) {
        Font font = Assets.minecraftia24;
        Graphics gImage = image.createGraphics();

        // display health bar
        gImage.setColor(new Color(221, 54, 54));
        int computedHealthBarLength = (int)Math.floor(
            (double)(6*Constants.TILE_WIDTH*health)/(Constants.MAX_HEALTH));
        gImage.fillRect(
            (int)(Constants.PIXEL_MULTIPLY*16),
            (int)(Constants.PIXEL_MULTIPLY*5),
            computedHealthBarLength,
            (int)(Constants.PIXEL_MULTIPLY*8));

        // write health point
        gImage.setFont(font);
        FontMetrics fontMetrics = gImage.getFontMetrics();
        gImage.setColor(Color.WHITE);
        String currentHealth = health+" HP";
        gImage.drawString(currentHealth, 
            (int)(Constants.PIXEL_MULTIPLY*18),
            fontMetrics.getAscent() + 10);

        gImage.dispose();
        return image;
    }

    private BufferedImage displayInfo(BufferedImage image) {
        Graphics gImage = image.createGraphics();

        // display score
        Font font = Assets.minecraftia34;
        gImage.setFont(font);
        gImage.setColor(new Color(101, 221, 248));
        String currentScore = score+"";
        gImage.drawString(currentScore, 
            (int)(Constants.PIXEL_MULTIPLY*8),
            (int)(Constants.PIXEL_MULTIPLY*37));

        // display multiplier
        font = Assets.minecraftia24;
        gImage.setFont(font);
        gImage.setColor(new Color(101, 221, 248));
        String currentMultiplier = "x "+multiplier;
        gImage.drawString(currentMultiplier, 
            (int)(Constants.PIXEL_MULTIPLY*6*16),
            (int)(Constants.PIXEL_MULTIPLY*33));

        // display enemy and wave info
        font = Assets.minecraftia24;
        gImage.setFont(font);
        gImage.setColor(new Color(208, 47, 96));
        String enemyInfo = "Krugs "+totalKilledEnemies+
            "/"+totalWaveEnemies+" Wave "+wave;
        gImage.drawString(enemyInfo, 
            (int)(Constants.PIXEL_MULTIPLY*18),
            (int)(Constants.PIXEL_MULTIPLY*47));

        gImage.dispose();
        return image;
    }

    // GETTERS SETTERS
    public void nextWaveInit() {
        // init params for a new wave
        wave++;
        totalKilledEnemies = 0;
        multiplier += 2*wave + 1;
        enemySpawned = 0;
        totalWaveEnemies = (int)(3 + totalWaveEnemies*1.5);
        timeBetweenPop = (int)(timeBetweenPop*(5f/6f));
        if(timeBetweenPop < Constants.MIN_POP_TIME) {
            timeBetweenPop = Constants.MIN_POP_TIME;
        }
    }

    public void incrementMultiplier(int amt) {
        multiplier += amt;
    }

    public void addToScoreApplyMultiplier(int points) {
        score += points*multiplier;
    }

    public void addHealth(int amt) {
        int newHealth = health + amt;
        if(newHealth > 100) {
            newHealth = 100;
        }
        health = newHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getWave() {
        return wave;
    }

    public int getTotalWaveEnemies() {
        return totalWaveEnemies;
    }

    public int getTimeBetweenPop() {
        return timeBetweenPop;
    }

    public boolean canSpawnEnemy() {
        boolean can = (enemySpawned < totalWaveEnemies) ? true : false;
        return can;
    }

    public void oneEnemyHasJustSpawned() {
        enemySpawned++;
    }

    public void oneEnemyWasKilled() {
        totalKilledEnemies++;
        System.out.println(" totalKilledEnemies = "+totalKilledEnemies);
    }

    public int getTotalKilledEnemies() {
        return totalKilledEnemies;
    }

}
