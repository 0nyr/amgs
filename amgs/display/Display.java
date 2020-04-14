package amgs.display;

import java.awt.*;
import javax.swing.*;

public class Display {

	private GraphicsDevice gDevice = GraphicsEnvironment
		.getLocalGraphicsEnvironment().getScreenDevices()[0];
	private GraphicsEnvironment gEnvironment = GraphicsEnvironment
		.getLocalGraphicsEnvironment();

	private JFrame frame;
	private Canvas canvas;
	
	private String title;
	private int width, height;
	private final int DEFAULT_WIDTH = 1280, DEFAULT_HEIGHT = 720;
	
	public Display(String title) {
		this.title = title;
		// recover size of the window of the game so as to maximize it
		try {
			this.width = (int)(gEnvironment.getMaximumWindowBounds().getWidth());
			this.height = (int)(gEnvironment.getMaximumWindowBounds().getHeight());
		} catch (Exception e) {
			e.printStackTrace();
			this.width = DEFAULT_WIDTH;
			this.height = DEFAULT_HEIGHT;
		}
		initDisplay();
	}
	
	private void initDisplay() {
		frame = new JFrame(title);
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setUndecorated(true);
		//frame.setBackground(new Color( 15, 18, 20));
		frame.setIgnoreRepaint(true);
		frame.setVisible(true);
		
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		//canvas.setMaximumSize(new Dimension(width, height));
		//canvas.setMinimumSize(new Dimension(width, height));
		canvas.setFocusable(false);	// remove KeyListener bug
		canvas.setBackground(new Color( 15, 18, 20));
		
		frame.add(canvas);
		frame.pack();


	}

	public Canvas getCanvas(){
		return canvas;
	}
	
	public JFrame getFrame(){
		return frame;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public void setWidth(int newWidth) {
		this.width = newWidth;
	}

	public void setHeight(int newHeight) {
		this.height = newHeight;
	}

	public GraphicsDevice getGDevice() {
		return gDevice;
	}

	public GraphicsEnvironment getGEnvironment() {
		return gEnvironment;
	}

}
