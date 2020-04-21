package fr.projetinsa.éléments;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;

public class Spaceship extends Entity{
	
	int x,y;
	int scale =1;
	
	public Spaceship(Level level, int x,int y) {
		super(level);
		this.x=x;
		this.y=y;
	}
	
	@Override
	public void update() {		
	}

	@Override
	public void render(Screen screen) {
		int xTile=0;
		int yTile=1;
		int modifier = 8 * scale; 
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier/2 -4;
		int colour=Colours.get(-1,005, 555, 0);
		/*screen.render(xOffset , yOffset, xTile + yTile * 32, colour, 0x00, scale);
		screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour, 0x00,scale);
		screen.render(xOffset , yOffset + modifier, xTile + (yTile + 1) * 32, colour,0x00, scale);
		screen.render(xOffset + modifier, yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, 0x00, scale);*/

		for(int i=0;i<3;i++) {
			for(int j=0;j<5;j++) {
				screen.render(xOffset +j*modifier, yOffset +i*modifier, (xTile+j) + (yTile +i)* 32, colour, 0x00, scale);
				screen.render(xOffset + (j+1)*modifier, yOffset+i*modifier, (xTile +j+1) + (yTile+i) * 32, colour, 0x00,scale);
				screen.render(xOffset +j*modifier , yOffset + (i+1)*modifier, (xTile+j) + (yTile +i+1) * 32, colour,0x00, scale);
				screen.render(xOffset + (j+1)*modifier, yOffset +(i+1)*modifier, (xTile +j+1) + (yTile+i+1) * 32, colour, 0x00, scale);
			}
		}
	}

}
