package fr.projetinsa.éléments;
import java.util.LinkedList;




import com.algoproj2020.Colours;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Sprite;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;


public class CollisitionSensor {
	AsteroidManager AM; //ASTEROID MANAGER
	Controller CT; // BULLET MANAGER
	Heart HT;
	Player player;
    int counter =0;
    int LifeIni=3;
    
	
	public CollisitionSensor(Player player,AsteroidManager AM, Controller CT,Heart HT) {
		System.out.println("you have 3 lifes");
		this.player = player;
		this.AM = AM;
		this.CT = CT;
		this.HT = HT;
	}
	
	public void CollisitionDetection() {
		Asteroid astero;
		int k = 0;
		int i = 0;
			while( k<AM.AsteroidList.size()) {
			astero = AM.AsteroidList.get(k);
			
			while(i<CT.UnderControl.size()) {
				if(((astero.AreaCovered[0]>=CT.UnderControl.get(i).AreaCovered[0]) 
						//xmin astero > x min bullet
						&& (astero.AreaCovered[0]<=CT.UnderControl.get(i).AreaCovered[1]))
						//xmin this <x min objet
						|| ((astero.AreaCovered[1]>=CT.UnderControl.get(i).AreaCovered[0])
								//xmaxthis < x min objet et xmi b this < xmax objet
								&& (astero.AreaCovered[1]<=CT.UnderControl.get(i).AreaCovered[1])))
						{
							//xmax this > x min objet et xmax this < xmax objet
								
						if(((astero.AreaCovered[2]>=CT.UnderControl.get(i).AreaCovered[2])
								
								&& (astero.AreaCovered[2]<=CT.UnderControl.get(i).AreaCovered[3]))
								|| ((astero.AreaCovered[3]>=CT.UnderControl.get(i).AreaCovered[2]) && 
								(astero.AreaCovered[3]<=CT.UnderControl.get(i).AreaCovered[3]))){
						AM.AsteroidList.remove(k);
						CT.UnderControl.remove(i);
					
						i=CT.UnderControl.size();
						System.out.println("good");
						}
				
			}
			i++;
			}
			i=0;
			k++;
			}
		
			int p=0;
			while( p<AM.AsteroidList.size()) {
				astero = AM.AsteroidList.get(p);
					if(((astero.AreaCovered[0]>=HT.AreaCovered[0]) 
							//xmin astero > x min bullet
							&& (astero.AreaCovered[0]<=HT.AreaCovered[1]))
							//xmin this <x min objet
							|| ((astero.AreaCovered[1]>=HT.AreaCovered[0])
									//xmaxthis < x min objet et xmi b this < xmax objet
									&& (astero.AreaCovered[1]<=HT.AreaCovered[1])))
							{
								//xmax this > x min objet et xmax this < xmax objet
									
							if(((astero.AreaCovered[2]>=HT.AreaCovered[2])
									
									&& (astero.AreaCovered[2]<=HT.AreaCovered[3]))
									|| ((astero.AreaCovered[3]>=HT.AreaCovered[2]) && 
									(astero.AreaCovered[3]<=HT.AreaCovered[3]))){
								LifeIni--;
								if(LifeIni>=1) {
							System.out.println("You lost 1 life : "+LifeIni+" left.");
							
						AM.AsteroidList.clear();
						CT.UnderControl.clear();
						p=AM.AsteroidList.size()+1;
					
						AM.Timer=0;
								}
								if(LifeIni==0) {
									System.out.println("Game Over");
									player.x=0;
									player.y=0;
									AM.AsteroidList.clear();
									CT.UnderControl.clear();
									p=AM.AsteroidList.size()+1;
								}
							}
					
				}
					p++;
				}
			
			
				}
		

}
		
