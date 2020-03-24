package fr.projetinsa.éléments;
import java.util.LinkedList;




import com.algoproj2020.Colours;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Sprite;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;


public class AsteroidManager extends Entity {

	LinkedList<Asteroid> AsteroidList = new LinkedList<Asteroid>();
	Screen screen; 
	Level L;
	Asteroid Astero;
	int Timer =0; 
	
	int [] size;
	
	
	public void addAsteroid(int [] size) {
		
		int x=20;
		int y =20;
		
		if(Math.random()<0.5) {
			if(Math.random()<0.5) {
				x=0;
				y=(int)(size[1]/2+ Math.random()*504-size[1]);
			}else {
				x=504-size[0];
				y=(int)(size[1]/2+ Math.random()*504-size[1]);
				
			}
		}else {
			// Si math.random > 0.5 alors l'alien apparaitra sur l axe y droit ou gauche
			if(Math.random()<0.5) {
			y=0;
		x =(int)(size[0]/2+Math.random()*504-size[0]);
		y =0;
		} else {
		x =(int)(size[0]/2 + Math.random()*504-size[0]);
		y =(int) (504-size[1]);
		}
			
	}
		
		AsteroidList.add(new Asteroid(L,x,y, size));
	}
	
	
	
	
	
	
	
	
	public void RemoveAsteroid(int p) {
		this.AsteroidList.remove(p);
	}
	
	
	
	
	
	public AsteroidManager(Level L, Screen screen,int [] size) {
		super(L);
		this.screen = screen;
		this.size = size;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	public void update() {
		Timer++;
		if(Timer>600) {
			if(Timer==1081) {
		this.addAsteroid(this.size);
			Timer=681;
		}
		}
		for(int i =0; i<AsteroidList.size();i++) {
			Astero = AsteroidList.get(i);
			Astero.update();
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	

	public void rendu(Screen screen) {
		for(int i =0; i<AsteroidList.size();i++) {
			Astero = AsteroidList.get(i);
			Astero.rendu(screen);
		}
	}

}
