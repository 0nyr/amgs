package amgs.levels;

import amgs.*;

public class LevelChanger {

    private Handler handler;
    private int totalWaveEnemies;

    public LevelChanger(Handler handler) {
        this.handler = handler;
        totalWaveEnemies = handler.getWorld().getGameHud().getTotalWaveEnemies();
    }

    public void tick() {
        checkIfNextLevel();
    }

    private void checkIfNextLevel() {
        if(handler.getWorld().getGameHud().getTotalKilledEnemies() >= totalWaveEnemies) {
            // next level change
            handler.getWorld().changeToNextLevel();
        }
    }

}
