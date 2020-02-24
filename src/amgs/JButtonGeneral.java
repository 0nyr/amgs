package amgs;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class JButtonGeneral extends JButton {
	
	private ImageIcon imgBackground, imgHover, imgClick;
	
	public JButtonGeneral(String buttonName) {
		this.setText(buttonName);
		this.setSize(400, 80);
		// load images
		imgBackground = new ImageIcon("./res/sprites/title_screen_button_400x80.png");
		imgHover = new ImageIcon("./res/sprites/title_screen_button_hover_400x80.png");
		imgClick = new ImageIcon("./res/sprites/title_screen_button_hover_400x80.png");
		// set the images
		this.setIcon(imgBackground);
		this.setRolloverIcon(imgHover);
		this.setPressedIcon(imgClick);
		// disable all the boring default presets on JButtons
		this.setOpaque(false);
		this.setContentAreaFilled(false);
		this.setBorder(null);
		this.setBorderPainted(false);
		this.repaint();
		// center text on top of icon
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		// text effects and set up
		//this.setForeground(new Color( 86, 162, 191));
		this.setForeground(new Color( 200, 200, 200));
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
		this.setFont(minecraftia);
	}
}
