package fr.projetinsa.éléments;



import java.util.LinkedList;



import com.algoproj2020.Colours;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Sprite;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;


// Controller made for bullets
public class Controller extends Entity  {
	LinkedList<Bullet> UnderControl = new LinkedList<Bullet>();
	
	Bullet TempBullet;
	Level L;
	int Size = 40;
	Screen screen;
	Player Perso;
	double rithm=0;
	int [] size = new int[2]; 
	
	
	public Controller(Level L,Player Perso, Screen screen,int []size) {
		super(L);
		this.Perso = Perso;
		this.screen = screen;
		this.size[0]= size[0];
		this.size[1]= size[1];
	}
	
	
	public void rendu(Screen screen) {
		for(int i =0; i<UnderControl.size();i++) {
			TempBullet = UnderControl.get(i);
			TempBullet.rendu(screen);
			//System.out.println("performed");
		}
	}
	
	
	public void addBullet() {
		int Xx =0;
		int Yy =0;
		switch (Perso.direction) {
		
		case 0:
			Xx=Perso.x;
			Yy=Perso.y-20;
			//System.out.print("2000");
			break;
			
		case 1:
			Xx=Perso.x+4;
			Yy=Perso.y-2;
			break;
			
		case 2:
			Xx=Perso.x+2;
			Yy=Perso.y+5;
			
			break;
			
		case 3:
			Xx=Perso.x-4;
			Yy=Perso.y-2;
			
			break;
		}
		
		UnderControl.add(new Bullet(L,Xx,Yy,Perso.direction,size));
	//	System.out.println("P1");
		
	}
	
	public void removeBullet(Bullet Block) {
		UnderControl.remove(Block);

}

	
	// ici je triche un peu pour le timer des tires
	public void update() {
		for(int i =0; i<UnderControl.size();i++) {
			TempBullet = UnderControl.get(i);
			TempBullet.update();
			if(TempBullet.x<0) {
				this.removeBullet(TempBullet);
			}
			if(TempBullet.x>510) {
				this.removeBullet(TempBullet);
			}
			if(TempBullet.y<0) {
				this.removeBullet(TempBullet);
			}
			if(TempBullet.y>510) {
				this.removeBullet(TempBullet);
			}
		}
		//System.out.println(Perso.input.space.isPressed());
		if(!Perso.input.space.isPressed()){
			rithm=0;
		}
		if(Perso.input.space.isPressed()){
			if(rithm==0) {
				
			    		
			this.addBullet();
			//System.out.println("bullet added");
			}
			rithm++;
			if(rithm==100) {
				rithm =0;
			}
		
	}
	}
}
