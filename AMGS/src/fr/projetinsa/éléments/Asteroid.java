package fr.projetinsa.éléments;
import java.util.LinkedList;
import com.algoproj2020.Colours;
import com.algoproj2020.UserInputs;
import fr.projetinsa.graphics.Sprite;
import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;

public class Asteroid extends Entity{
	
	protected Sprite sprite;   
	private double x ;
	private double y ;
	static int [] size = new int[2];
	int [] AreaCovered = new int[4];
	int PasDeplacementAsteroid=1;
	int Timer=0;
	int Counter=0;
	
	
	public Asteroid(Level level, int X, int Y, int[]size) {
		super(level);
		
		x=X;
		y=Y;
		this.size[0]=size[0];
		this.size[1]=size[1];
		
		AreaCovered[0]= (int) x;
		AreaCovered[1]= (int) x + size[0];
		AreaCovered[2]= (int) y;
		AreaCovered[3]= (int) y + size[1];
				
	}






	public void update() {
		
		
			this.displacement();
			
		
	}

	
	public void displacement(){
		Counter++;
		if(Counter==7) {
		double theta=0;
		double r= 0;
		int newx,newy;
		// etape 1: shift de la frame [recentrage] orientation des axes concervée
		// redefission de la position des points:
		newx= (int) (x-(int)(504/2));
		newy = (int) (y-(int)(504/2));
		
		
		// à compter de cet emplacement: on passe en coordonnées polaires centrée au milieu de la frame
		r = (double)(Math.sqrt((newx)*(newx)+newy*newy));
		
		
		
		if(newx!= 0) {
		theta = Math.atan((double)newy/(double)newx);
		}
		else {
			if(newy<0) {
			theta = - Math.PI/2;
			}
			if(newy>0) {
			theta= Math.PI/2;
			}
		}
		
		
		r = r-this.PasDeplacementAsteroid;
		
		
		// transformation des variables en fonction de la vitesse
		newx = (int)((r)* Math.cos(theta));
		newy = (int)((r)* Math.sin(theta));
		

		x= newx+(int)(504/2);
		y= newy+(int)(504/2);
		AreaCovered[0]= (int) x;
		AreaCovered[1]= (int) x + size[0];
		AreaCovered[2]= (int) y;
		AreaCovered[3]= (int) y + size[1];
				
		//System.out.println(" ASTER "+ AreaCovered[0]+"fff"+AreaCovered[1]+"ddd"+AreaCovered[2]+"rrr"+AreaCovered[3]);
		Counter=0;
		}
		}
	




	public void rendu(Screen screen) {
		
		
		screen.renderAstero((int)this.x,(int)this.y,Sprite.Bullet1);
	
	}

}
