package amgs;

import amgs.display.*;
import amgs.gfx.*;
import amgs.states.*;
import amgs.input.*;
import amgs.scores.*;
import amgs.sound.MusicPlayer;
import amgs.controls.*;

import java.awt.*;
import java.awt.image.*;

public class Game implements Runnable {

	private Display display;
	public String title;
	
	private Thread thread;
	private boolean running = false;
	
	private BufferStrategy bs;
	private Graphics g;
	private Toolkit toolkit;
	
	private State currentState;
	public State gameState;
	public State menuState;
	
	private KeyManager keyManager;
	private MouseManager mouseManager;
	private ControlManager controlManager;
	private GameCamera gameCamera;

	private ScoreManager scoreManager;

	private MusicPlayer musicPlayer;

	private Handler handler;
	
	public Game(String title) {
		this.title = title;
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	public synchronized void start() {
		if(running) {
			// already running thread
		} else {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}
	
	public synchronized void stop() {
		if(running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// already stopped thread
		}
		
	}
	
	// necessary implementation with Runnable
	@Override
	public void run() {
		init();
		
		double amountOfTicks = 60D;
		double timePerTick = 1_000_000_000D / amountOfTicks;
		double fractionTimeOfATick = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		int frames = 0;
		
		while(running) {
			now = System.nanoTime();
			fractionTimeOfATick += (now - lastTime) / timePerTick;	// how much time spent before calling tick/render
			timer += now - lastTime;
			lastTime = now;
			
			while(fractionTimeOfATick >= 1) {
				tick();
				ticks++;
				fractionTimeOfATick--;
				render();
				frames++;
			}
			
			try {
				//Thread.yield();
				Thread.sleep(1);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			if( timer >= 1_000_000_000L) {
				System.out.println("");
				System.out.print("ticks = "+ticks);
				System.out.print(" ,frames = "+frames);
				System.out.println(" ,timer = "+timer);
				ticks = 0;
				timer = 0;
				frames = 0;
			}
			
		}
		
		stop();
	}
	
	private void init() {
		toolkit = Toolkit.getDefaultToolkit();
		display = new Display(title);
		display.getFrame().addKeyListener(keyManager);	//allow keyboard access
		display.getFrame().addMouseListener(mouseManager); //allow mouse access
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		handler = new Handler(this);

		//WARN: controlManager need to be initialised after display !
		controlManager = new ControlManager(keyManager, display);
		Assets.setHandler(handler);
		Assets.init();
		gameCamera = new GameCamera(handler, 0f, 0f);

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		StateManager.setState(menuState);

		scoreManager = new ScoreManager();

		musicPlayer = new MusicPlayer();
	}
	
	// update instances
	private void tick() {
		keyManager.tick();
		controlManager.tick();
		
		State currentState = StateManager.getState();
		if(currentState != null) {
			currentState.tick();
		}
	}
	
	private void render() {
		// https://docs.oracle.com/javase/tutorial/extra/fullscreen/index.html
		// https://stackoverflow.com/questions/13590002/understand-bufferstrategy
		bs = display.getCanvas().getBufferStrategy(); 
		if(bs == null) {
			// at start, need to create buffer screens from the canvas
			display.getCanvas().createBufferStrategy(2);
		} else {

			// more info on render loop: https://docs.oracle.com/javase/7/docs/api/java/awt/image/BufferStrategy.html
			do {
				// The following loop ensures that the contents of the drawing buffer
         		// are consistent in case the underlying surface was recreated
				do {
					try {
						// Get a new graphics context every time through the loop
						// to make sure the strategy is validated
						g = bs.getDrawGraphics();
						// clear screen
						g.clearRect(0, 0, display.getWidth(), display.getHeight());
						// Start drawing here
						currentState = StateManager.getState();
						if(currentState != null) {
							currentState.render(g);
						}					
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						g.dispose();
					}

					// Repeat the rendering if the drawing buffer contents
             		// were restored
				} while(bs.contentsRestored());

				// Display the buffer
				bs.show();
				toolkit.sync();
				
				// Repeat the rendering if the drawing buffer was lost
			} while(bs.contentsLost());
		}
		System.out.print("*");
	}
	
	// getters
	public KeyManager getKeyManager() {
		return keyManager;
	}

	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public GameCamera getGameCamera() {
		return gameCamera;
	}

	public Display getDisplay() {
		return display;
	}

	public ScoreManager getScoreManager() {
		return scoreManager;
	}
	
}







