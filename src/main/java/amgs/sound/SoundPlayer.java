package amgs.sound;

import amgs.utils.*;

import javax.sound.sampled.*;

public class SoundPlayer {

    private Clip clip;
    
    public SoundPlayer(String pathFromJarRoot) {
        clip = Utils.loadSound(pathFromJarRoot);
    }

    // GETTERS SETTERS
    public void play() {
        // StackOverflow : https://stackoverflow.com/questions/10645594/what-audio-format-should-i-use-for-java 
        if (clip.isRunning()) {
            clip.stop();   // Stop the player if it is still running
        }
        clip.setFramePosition(0); // rewind to the beginning
        clip.start();     // Start playing
    
    }
    

}
