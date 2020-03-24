package com.algoproj2020;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import fr.projetinsa.graphics.SpriteSheet;
import fr.projetinsa.�l�ments.Asteroid;
import fr.projetinsa.�l�ments.AsteroidManager;
import fr.projetinsa.�l�ments.Bullet;
import fr.projetinsa.�l�ments.CollisitionSensor;
import fr.projetinsa.�l�ments.Controller;
import fr.projetinsa.�l�ments.Heart;
import fr.projetinsa.�l�ments.Player;

public class AMGS extends Canvas implements Runnable {
 
	private static final long serialVersionUID = 1L; 
	//////////////////////////////////////////// ATTRIBUTS /////////////////////////////////////////////////////////////////////////////////////
	private static GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();   
	private static Rectangle window=graphicsEnvironment.getMaximumWindowBounds();// On r�cup�re les dimensions utiles de l'�cran.
	
	public final static int WIDTH=160; //Largeur de la fen�tre = 80% de la largeur de l'�cran d'ordinateur.
	public final static int HEIGHT=WIDTH/12*9;
	public final static int SCALE=3; 
	public static final Dimension DIMENSIONS = new Dimension(HEIGHT*SCALE, HEIGHT*SCALE);
	public static final String NAME="AMG SQUAD";
	
	
	private JFrame frame; 
	
	public boolean running=false;
	public int UpdatesCount=0; // On compte le nombre de mis � jour pendant l'utilisation du jeu
	
	private BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);//On construit une image de largeur WIDTH, de hauteur HEIGHT et de type rouge vert bleu
	private int[] pixels =((DataBufferInt) image.getRaster().getDataBuffer()).getData();// On associe cet objet � un tableau d'entier correspondant aux couleurs de chaques pixels
	//raster=rectangular array of pixels
	//We are converting the image object into an array of pixels
	private int[] colours=new int[6*6*6]; 
	
	public CollisitionSensor CS;
	private Screen screen;
	public UserInputs inputs;
	public Level level;
	public Player player;
	public Controller controller;
	public Heart heart;
	public AsteroidManager AsteroManager;
	private Thread thread;
	int[] sizeBullet = new int [2];
	int[] sizeElement = new int[2];
	////////////////////////////////////////// CONSTRUCTEUR (VALIDE) /////////////////////////////////////////////////////////////////////////////////////
	public AMGS() {
			this.setMinimumSize(DIMENSIONS);
			this.setMaximumSize(new Dimension(DIMENSIONS));
			this.setPreferredSize(new Dimension(DIMENSIONS));// On ajuste les dimensions de notre canvas
			frame =new JFrame(NAME);
			frame.setLayout(new BorderLayout());// On s�lectionne la strat�gie d'affichage que l'on veut utiliser pour notre fen�tre (BorderLayout).
			frame.add(this,BorderLayout.CENTER);
			frame.pack();// ajuste chaque compsant � sa taille optimale et retaille la fen�tre.
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
	}
	
	////////////////////////////////////////// METHODES //////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {//checked
		new AMGS().start();//on appelle la m�thode start
		//test t=new test();
	}
	
	public void init() {//checked
		int index =0; 
		for(int r=0;r<6;r++) {
			for(int g=0;g<6;g++) {
				for(int b=0;b<6;b++) {
					int rr=(r*255/5);
					int gg=(g*255/5);
					int bb=(b*255/5);
					colours[index++]=rr<<16 | gg<<8 | bb;
				}
			}
		}
		screen=new Screen(WIDTH,HEIGHT,new SpriteSheet("images/sprite_sheet.png"));
		inputs=new UserInputs(this, frame);
		level=new Level("images/water_test_level.png");
		player=new Player(level, 0, 0, inputs);
	//	B1 = new Bullet(level,0,0);
		level.addEntity(player);
		
		sizeElement[0]= 30; sizeElement[1] = 30;
		sizeBullet[0]= 10; sizeBullet[1] = 10;
		
		AsteroManager = new AsteroidManager(this.level,this.screen,sizeElement);
		controller = new Controller(this.level, this.player,this.screen,this.sizeBullet);
		heart = new Heart(this.level,252,252,sizeElement);
		level.addEntity(controller);
		level.addEntity(AsteroManager);
		level.addEntity(heart);
		
		CS = new CollisitionSensor(this.player,AsteroManager,controller,heart);
	}
	
	public synchronized void start() {//checked
		running=true;
		thread=new Thread(this);// On associe un thread � la classe AMGS
		thread.start();//On lance le thread associ� � la classe AMGS
	}
	public synchronized void stop() {//checked
		running = false;
		try {
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	public void Render() {//checked
		
		BufferStrategy strat=getBufferStrategy();
		if(strat==null){
			createBufferStrategy(3);//triple buffering so that we get two backup frames.
			return;
		}
		
		int xoffset=player.x-(screen.width/2);
		int yoffset=player.y-(screen.height/2);
		
		level.rendertile(screen,xoffset,yoffset);
		level.renderEntities(screen);
	
		
		for (int y=0; y<screen.height;y++) {
			for(int x=0;x<screen.width;x++) {
				int colourcode=screen.pixels[x+y*screen.width];// on r�cup�re les donn�es de screen.pixels 
				if(colourcode<255)pixels[x+y*WIDTH]=colours[colourcode];//pour les mettre dans le tableau pixels
				
			}
		}
		Graphics g=strat.getDrawGraphics();
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		g.dispose();
		strat.show();

	}	 
	public void Updt() {//checked
		UpdatesCount++;
		level.Updt();
		controller.update();
		AsteroManager.update();
		CS.CollisitionDetection();
	
		
	}
	@Override
	public void run() {//checked
		long offtime=System.nanoTime() ;
		double nsperUpdt = 1000000000D/60D;
		
		int updates=0;
		int frames=0;
		
		long Timer=System.currentTimeMillis();
		double deltat=0;
		
		init();

		while(running) {
			long now = System.nanoTime();
			deltat+=(now-offtime)/nsperUpdt;
			offtime=now;
			boolean shouldRender=true;
			
			while(deltat>=1) {
				updates++;
				Updt();
				deltat-=1;
				shouldRender=true;
			}
			try {
				Thread.sleep(2);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				Render();
			}
			if(System.currentTimeMillis()-Timer>=1000) {
				Timer+=1000;
				//System.out.println(updates+"updates , "+frames+" frames");
				frames=0;
				updates=0;
			}
		}
		
	}
}

	
	
	