package com.algoproj2020;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File; 
import java.io.IOException;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.projetinsa.éléments.Player;

public class AMGS extends Canvas implements Runnable {
 
	private static final long serialVersionUID = 1L;
	//////////////////////////////////////////// ATTRIBUTS /////////////////////////////////////////////////////////////////////////////////////
	private GraphicsEnvironment graphicsEnvironment=GraphicsEnvironment.getLocalGraphicsEnvironment();   
	private Rectangle window=graphicsEnvironment.getMaximumWindowBounds();// On récupère les dimensions utiles de l'écran.
	
	public final static int WIDTH=160;//window.width*8/10; //Largeur de la fenêtre = 80% de la largeur de l'écran d'ordinateur.
	public final static int HEIGHT=WIDTH/12*9;//window.height*8/10;
	public final static int SCALE=3; 
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	public static final String NAME="AMG SQUAD";
	
	private JFrame frame; 
	
	public boolean running=false;
	public int UpdatesCount=0; // On compte le nombre de mis à jour pendant l'utilisation du jeu
	
	private BufferedImage image=new BufferedImage(HEIGHT,HEIGHT,BufferedImage.TYPE_INT_RGB);//On instancie une Buffered image
	private int[] pixels =((DataBufferInt) image.getRaster().getDataBuffer()).getData();// On associe cet objet à un tableau d'entier correspondant aux couleurs de chaques pixels
	private int[] colours=new int[6*6*6]; 
	
	private Screen screen;
	public UserInputs inputs;
	public Level level;
	public Player player;
	////////////////////////////////////////// CONSTRUCTEUR (VALIDE) /////////////////////////////////////////////////////////////////////////////////////
	public AMGS() {
			this.setMinimumSize(DIMENSIONS);
			this.setMaximumSize(new Dimension(DIMENSIONS));
			this.setPreferredSize(new Dimension(DIMENSIONS));// On ajuste les dimensions de notre canvas
			frame =new JFrame(NAME);// On créé ue fenêtre
			frame.setLayout(new BorderLayout());// On sélectionne la stratégie d'affichage que l'on veut utiliser pour notre fenêtre.
			frame.add(this,BorderLayout.CENTER);
			frame.pack();// ajuste chaque compsant à sa taille optimale et retaille la fenêtre.
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}
	////////////////////////////////////////// METHODES //////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {//checked
		new AMGS().start();
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
		inputs=new UserInputs(this);
		level=new Level("images/water_test_level.png");
		player=new Player(level, 0, 0, inputs);
		level.addEntity(player);
	}
	
	public synchronized void start() {//checked
		running=true;
		new Thread(this).start();
	}
	public synchronized void stop() {//checked
		running = false;
	}
	
	public void Render() {//checked
		BufferStrategy strat=getBufferStrategy();
		if(strat==null){
			createBufferStrategy(3);
			return;
		}
		
		int xoffset=player.x-(screen.width/2);
		int yoffset=player.y-(screen.height/2);
		
		level.rendertile(screen,xoffset,yoffset);
		level.renderEntities(screen);
		
		for (int y=0; y<screen.height;y++) {
			for(int x=0;x<screen.width;x++) {
				int colourcode=screen.pixels[x+y*screen.width];
				if(colourcode<255)pixels[x+y*65]=colours[colourcode];//pourquoi
				
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
				System.out.println(updates+"updates , "+frames+" frames");
				frames=0;
				updates=0;
			}
		}
		
	}
}

	
	
	