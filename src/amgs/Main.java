package amgs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class Main {

	static GraphicsDevice device = GraphicsEnvironment
			.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static JPanel panel = new JPanel();

	public static void main(String[] args) {
		
		// recover size of the window of the game so as to maximize it
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		int windowWidth = (int)(env.getMaximumWindowBounds().getWidth());
		int windowHeight = (int)(env.getMaximumWindowBounds().getHeight());
		System.out.println("windowWidth = "+windowWidth);
		System.out.println("windowWidth = "+windowWidth);

		final JFrame frame = new JFrame("Display Mode");
		frame.setSize(windowWidth, windowHeight);
		frame.setLocation(0, 0);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setBackground(new Color( 15, 18, 20));
		
		// The JButtons to control the main window: frame
		int windowWidthPlaceButtons = (int)(windowWidth/10);
		JButtonGeneral btn1 = new JButtonGeneral("Full-Screen");
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				device.setFullScreenWindow(frame);
				panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			}
		});
		btn1.setLocation(windowWidthPlaceButtons+10, 10);
		
		JButtonGeneral btn2 = new JButtonGeneral("Normal");
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				device.setFullScreenWindow(null);
			}
		});
		btn2.setLocation(windowWidthPlaceButtons+10+10+400, 10);
		
		JButtonGeneral btn3 = new JButtonGeneral("Exit");
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("*** Program exited ***");
				System.exit(0);
			}
		});
		btn3.setLocation(windowWidthPlaceButtons+10+10+400*2, 10);
		
		JButtonGeneral btn4 = new JButtonGeneral("test");
		btn4.setLocation(windowWidthPlaceButtons+10+10+400*3, 10);
		
		JLabel titleScreen = new JLabel();
		titleScreen.setSize(1280, 720);
		titleScreen.setLocation(windowWidth/2-1280/2, windowHeight/2-720/2);
		titleScreen.setIcon(new ImageIcon("./res/sprites/title_screen_v1_1280x720.png"));
		
		// try to load font
		Font minecraftia;
		try {
			minecraftia = Font.createFont(0, new File("./res/fonts/minecraftia/Minecraftia-Regular.ttf"));
			System.out.println("font loaded");
		} catch (FontFormatException e1) {
			minecraftia = new Font("Arial", Font.BOLD, 24);
			e1.printStackTrace();
			System.out.println(e1);
		} catch (IOException e1) {
			minecraftia = new Font("Arial", Font.BOLD, 24);
			e1.printStackTrace();
			System.out.println(e1);
		}
		minecraftia = minecraftia.deriveFont(Font.PLAIN, 24f);
		System.out.println("minecraftia.size = "+minecraftia.getSize());
		
		
		JLabel gameInfo = new JLabel("AMGS prealpha version 1.0.0 #24/02/2020");
		gameInfo.setSize(700, 40);
		gameInfo.setFont(minecraftia);
		gameInfo.setLocation(windowWidth/2-1280/2, windowHeight/2-720/2-40);
		gameInfo.setForeground(new Color( 86, 162, 191));
		
		
		//JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// create main JPanel
		
		panel.setBackground(new Color( 15, 18, 20));
		panel.setSize(windowWidth, windowHeight);	// maximize the size of the main panel
		panel.setLocation(0, 0);
		panel.setLayout(null);
		// add all the components to the main panel
		panel.add(btn1);
		panel.add(btn2);
		panel.add(btn3);
		panel.add(btn4);
		panel.add(titleScreen);
		panel.add(gameInfo);
		// add the panels to the main frame
		frame.add(panel);

		//frame.pack();
		frame.setVisible(true);

	}
}
