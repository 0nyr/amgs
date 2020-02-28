package com.algoproj2020;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class test extends JFrame {
	public pane pane;
	public test() {
		pane =new pane();
		pane.setBounds(0,0,this.getWidth(),this.getHeight());
		
		setSize(500,500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(pane);
		setVisible(true);
		repaint();
	}
	public BufferedImage loadImage(String path) {
		BufferedImage image = null;
		try { 
			image=ImageIO.read(new File(path));
			
		}catch(IOException e){
			System.out.println("image not found");
			e.printStackTrace();
		}
		return image;
	}
	public class pane extends JPanel{	
		public void paintComponent(Graphics g) {
			try {
				Image spritesheet =ImageIO.read(new File("images/spriteitems.png"));
				g.drawImage(spritesheet,0,0,getWidth(),getHeight(),this);
				//g.drawImage(loadImage("images/spritesheet.png"),0,0,200,200,this);
			}catch(IOException e){
				System.out.println("image not found");
				e.printStackTrace();
			}
		}
		
	}
}

