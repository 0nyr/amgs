package amgs.sound;

import amgs.utils.*;

import javax.sound.sampled.*;

public class MusicPlayer implements Runnable {

    private Thread thread;
    private Clip clip;

    public MusicPlayer() {
        clip = Utils.loadSound("/res/sounds/musics/eons_of_darkness_remix.wav");
        thread = new Thread(this);
		thread.start();
    }

    @Override
    public void run() {
        playMusic();

    }

    public synchronized void playMusic() {
        try {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

        } catch(Exception e) {
            System.out.println(" Problem with MusicPlayer ");
            e.printStackTrace();
        }
    }

}
