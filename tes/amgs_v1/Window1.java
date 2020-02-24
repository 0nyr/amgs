import javax.swing.*;
import java.awt.*;
import java.util.Observer;
import java.util.Observable;

// NB: DON'T forget to compile everything that is needed to run the code, not just the current one !!!

@SuppressWarnings("serial")
public class Window1 extends JFrame implements Observer {
	
	// global variables
		GlobalVariables g1;
	
	// recover size of the window of the game so as to maximize it
	GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	GraphicsDevice screen = env.getDefaultScreenDevice();
	GraphicsConfiguration config = screen.getDefaultConfiguration();
	
	// attributes
	int windowWidth = (int)(env.getMaximumWindowBounds().getWidth());
	int windowHeight = (int)(env.getMaximumWindowBounds().getHeight());
	
	// components
	JPanel0 p0 = new JPanel0(windowWidth, windowHeight);	//main container, background for the entire game
	JPanel1 p1 = new JPanel1(windowWidth, windowHeight, g1);	//corresponds to panel 1
	JPanel2 p2 = new JPanel2(windowWidth, windowHeight);
	
	public Window1(String Name, GlobalVariables g1) {
		super(Name);
		this.g1 = g1;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLayout(null);
		
		/*System.out.println("screen size: "+config.getBounds());	//returns : screen size: java.awt.Rectangle[x=0,y=0,width=1920,height=1080]
		System.out.println("usable size: "+env.getMaximumWindowBounds()); //returns: usable size: java.awt.Rectangle[x=53,y=27,width=1867,height=1053]
		System.out.println("getX : "+env.getMaximumWindowBounds().getX());				//returns: getX : 53.0
		System.out.println("getY : "+env.getMaximumWindowBounds().getY());				//getY : 27.0
		System.out.println("getWidth : "+env.getMaximumWindowBounds().getWidth());		//getWidth : 1867.0
		System.out.println("getHeight : "+env.getMaximumWindowBounds().getHeight());	//getHeight : 1053.0 */
		
		// set location from screen data
		setLocation((int)env.getMaximumWindowBounds().getX(), (int)env.getMaximumWindowBounds().getY());
		// set size so as to use max free space on screen
		setSize((int)env.getMaximumWindowBounds().getWidth(), (int)env.getMaximumWindowBounds().getHeight());
		
		// add panels
		p0.add(p1);	//don't forget to add every sub panels to the main one
		add(p0); //WARN: DON'T forget to finally add the global container to the window... boulet/20
		
		
	}
	
	public void update(Observable obj, Object arg) {
		System.out.println(obj);
		System.out.println(arg);
        System.out.println("SecondNewsReader got The news:"+(String)arg);
        p0.add(p2);
    }
	
	
}
