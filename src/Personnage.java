import javax.swing.*;



import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Personnage {
	
	int vie=5;
	
	
	
int[] PositionPerso = new int[2];
int direction = 0; // direction visée decomposition en avec initiale haut = 0 , rotation horraire

ArrayList <Bullet> BulletShot = new ArrayList<>();

JPanel Appearance = new JPanel();

ArrayList <KeyPressed> KeyUsed1 = new ArrayList<>(); //tableau de base du controle des deplacements

public Personnage() {
	PositionPerso[0] = 640; //position x inititiale
	PositionPerso[1]  = 320; //position y initiale
	
	
    //	0/38 HAUT 1/39 DROIT 2/40 BAS 3/37 GAUCHE
	KeyUsed1.add(new KeyPressed(38,"haut"));
	KeyUsed1.add(new KeyPressed(39,"droit"));
	KeyUsed1.add(new KeyPressed(40,"bas"));
	KeyUsed1.add(new KeyPressed(37,"gauche"));
	
	
	
	Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
	Appearance.setBackground(Color.green);
	
}





public void shoot() {
	System.out.println("space bar");
	this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
	this.Appearance.setBackground(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
	BulletShot.add(new Bullet(this.direction,this.PositionPerso));
}




public void Deplacement() {
	if(KeyUsed1.get(0).getState()==true) {
		
		// mouvement vers le haut uniquement 38
			if(KeyUsed1.get(1).getState()==false && KeyUsed1.get(2).getState()==false &&KeyUsed1.get(3).getState()==false) {
				this.direction = 0;
				if(PositionPerso[1]>0) {
					PositionPerso[1]=PositionPerso[1]-80;
					}
				this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
				 
			
		}
			
			
			
			
			
			//mouvement diagonal droit haut 38 39
			if(KeyUsed1.get(1).getState()==true && KeyUsed1.get(2).getState()==false &&KeyUsed1.get(3).getState()==false) {
				if(PositionPerso[0]<1200 && PositionPerso[1]>0) {
					PositionPerso[0]=PositionPerso[0]+80;
					PositionPerso[1]=PositionPerso[1]-80;
					}
				this.direction = 1;
				this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
				 
				System.out.println("haut droite");
			}
			//mouvement diagonal gauche haut 38 37
			if(KeyUsed1.get(1).getState()==false && KeyUsed1.get(2).getState()==false &&KeyUsed1.get(3).getState()==true) {
				if(PositionPerso[0]>0 && PositionPerso[1]>0) {
					PositionPerso[0]=PositionPerso[0]-80;
					PositionPerso[1]=PositionPerso[1]-80;
					}
				this.direction = 7;
				this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
				 
				System.out.println("haut gauche");
					}
			}
		
		
		
		
		if(KeyUsed1.get(1).getState()==true) {
			
		
		//droite pure
		if(KeyUsed1.get(0).getState()==false && KeyUsed1.get(2).getState()==false &&KeyUsed1.get(3).getState()==false) {
				
				
			if(PositionPerso[0]<1200) {
				PositionPerso[0]=PositionPerso[0]+80;
				}
			this.direction = 2;
			System.out.println("flèche droite");
			this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
			 
		
		}
		
		// droit bas 40 39    2 1
		if(KeyUsed1.get(0).getState()==false && KeyUsed1.get(2).getState()==true &&KeyUsed1.get(3).getState()==false) {
			
			if(PositionPerso[0]<1200 && PositionPerso[1]<640) {
				PositionPerso[0]=PositionPerso[0]+80;
				PositionPerso[1]=PositionPerso[1]+80;
				}
			this.direction = 3;
			this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
			 
			
		}
		}
		
		
		if(KeyUsed1.get(3).getState()==true) {
			if(KeyUsed1.get(0).getState()==false && KeyUsed1.get(2).getState()==false &&KeyUsed1.get(1).getState()==false) {
		//gauche pure/37
				if(PositionPerso[0]>0) {
					PositionPerso[0]=PositionPerso[0]-80;
					}
				this.direction = 6;
				this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
				 
				
			}
				// bas gauche 40 37    2 3
				if(KeyUsed1.get(0).getState()==false && KeyUsed1.get(2).getState()==true &&KeyUsed1.get(1).getState()==false) {
					this.direction = 5;
					if(PositionPerso[0]>0 && PositionPerso[1]<640) {
						PositionPerso[0]=PositionPerso[0]-80;
						PositionPerso[1]=PositionPerso[1]+80;
						}
					this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
					 
					
				}
		}
				
				
				if(KeyUsed1.get(2).getState()==true) {
					
					if(KeyUsed1.get(0).getState()==false && KeyUsed1.get(3).getState()==false &&KeyUsed1.get(1).getState()==false) {
						this.direction = 4;
						//bas pure 40/2

						if(PositionPerso[1]<640) {
							PositionPerso[1]=PositionPerso[1]+80;
							}
						this.Appearance.setBounds(PositionPerso[0],PositionPerso[1],80,80);
						 
					}
				
	
}
}
}
