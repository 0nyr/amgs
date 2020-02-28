

import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;

//FINAL
public class map extends JFrame implements KeyListener{
	
	public JPanel Panelmap = new JPanel();
	public JLayeredPane ManagementPane = new JLayeredPane();
	//public JPanel GlobPanelmap = new JPanel();
	int [][] position = new int[64][36];
	Personnage Perso = new Personnage();
	
	public map(){
		
		// definition of the map itself
		this.setLayout(null);
		Panelmap.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBounds(0,0,1400,900);
		Panelmap.setSize(1280,720);
		Panelmap.setBackground(new Color(0,41,178));
		
		
		ManagementPane.add(Panelmap,new Integer(-100));
		ManagementPane.add(Perso.Appearance,new Integer(0));
	    this.setContentPane(ManagementPane);
		this.setVisible(true);
		
		this.addKeyListener(this);
	}

	// 0-37gauche, 1-38haut, 2-39droit, 3-40bas,
	
	public void keyTyped(KeyEvent e) {
	
	
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	

	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		//		0/38 HAUT 1/39 DROIT 2/40 BAS 3/37 GAUCHE
		case 39:
			Perso.KeyUsed1.get(1).setState(true);	
		break;	
		
		case 38:
			Perso.KeyUsed1.get(0).setState(true);	
		break;	
		
		case 37:
			Perso.KeyUsed1.get(3).setState(true);	
		break;	
		
		case 40:
			Perso.KeyUsed1.get(2).setState(true);	
		break;	}
		Perso.Deplacement();
		this.repaint();
		
	}


	
	
	
	
	
	
	
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==32) {
			Perso.shoot();
			
			
			for(int i =0; i<Perso.BulletShot.size();i++) {
				ManagementPane.add(Perso.BulletShot.get(i).BulletAppearance,new Integer(0));
			}
		}
		switch(e.getKeyCode()) {
		//37 G      40B    39D    38H    32ESPACE
		//1 HAUT 2 DROITE 3 BAS 4 GAUCHE
		case 39:
			Perso.KeyUsed1.get(1).setState(false);	
		break;	
		
		case 38:
			Perso.KeyUsed1.get(0).setState(false);	
		break;	
		
		case 37:
			Perso.KeyUsed1.get(3).setState(false);	
		break;	
		
		case 40:
			Perso.KeyUsed1.get(2).setState(false);	
		
			break;
		
		
		}
		this.repaint();
	}
	
	
	
	//}
	
}
