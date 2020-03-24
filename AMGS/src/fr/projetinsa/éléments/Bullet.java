package fr.projetinsa.éléments;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.algoproj2020.Colours;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Sprite;
import fr.projetinsa.graphics.SpriteSheetBullet;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.SpriteSheet;
import com.algoproj2020.UserInputs;

public class Bullet extends Entity {
	protected Sprite sprite;   
	double x ;
	double y ;
	
	int direction =0;
	
	int PasDeplacementBullet=1;
	private int colour=Colours.get(-1,111,145,543);
	int [] size = new int[2];
	int [] AreaCovered = new int[4];
	
	BufferedImage image ;
	SpriteSheetBullet ssB;
	
	public Bullet(Level L, double x, double y, int direction, int [] size) {
		super(L);
		this.direction = direction;
		
		this.x = x;
		this.y = y;
		this.size[0]=size[0];
		this.size[1]=size[1];
		
		AreaCovered[0]= (int) x;
		AreaCovered[1]= (int) x + size[0];
		AreaCovered[2]= (int) y;
		AreaCovered[3]= (int) y + size[1];
		
	//	ssB = new SpriteSheetBullet("images/boulette.png");
	}
	
	
	public void update() {
			switch (this.direction) {
				
				case 0:
					this.x=x;
					this.y=y-PasDeplacementBullet;
					
					break;
					
				case 1:
					this.x=x+PasDeplacementBullet;
					this.y=y;
					break;
					
				case 2:
					this.x=x;
					this.y=y+PasDeplacementBullet;
					
					break;
					
				case 3:
					this.x=x-PasDeplacementBullet;
					this.y=y;
					
					break;
					
			}
			
			AreaCovered[0]= (int) x;
			AreaCovered[1]= (int) x + size[0];
			AreaCovered[2]= (int) y;
			AreaCovered[3]= (int) y + size[1];
			
		//	System.out.println(" BULLET "+ AreaCovered[0]+"fff"+AreaCovered[1]+"ddd"+AreaCovered[2]+"rrr"+AreaCovered[3]);

			
	}
	
	
	public void rendu(Screen screen) {
		screen.renderBullet((int)this.x,(int)this.y,Sprite.Bullet1);//screen.renderBullet(0,0,Sprite.Bullet1);
		
	}

	
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	/*
		private UserInputs input;
		private int colour=Colours.get(-1,111,145,543);
		protected boolean isSwimming = false;
	    private int tickCount = 0;
	    private String username;
	    
		public Bullet(Level level, int x, int y, UserInputs input) {
			super(level,"Bullet",x,y,1);
			this.input=input;
			this.username=username;
		}
		public String getUsername() {
	        return this.username;
	    }
		@Override
		public void update() {
			int xa=0;
			int ya=0;

			
			if(input.space.isPressed()) 
			{
			
			}
		}
		
		public void rendu(Screen screen) {
			screen.renderPlayer(x, y, Sprite.pokemon0);
		}
			 /*int xTile = 0;
		        int yTile = 28;
		        int walkingSpeed = 4;
		        int flipTop = (numbsteps >> walkingSpeed) & 1;
		        int flipBottom = (numbsteps >> walkingSpeed) & 1;

		        if (movingdir == 1) {
		            xTile += 2;
		        } else if (movingdir > 1) {
		            xTile += 4 + ((numbsteps >> walkingSpeed) & 1) * 2;
		            flipTop = (movingdir - 1) % 2;
		        }

		        int modifier = 8 * scale;
		        int xOffset = x - modifier / 2;
		        int yOffset = y - modifier / 2 - 4;
		        if (isSwimming) {
		            int waterColour = 0;
		            yOffset += 4;
		            if (tickCount % 60 < 15) {
		                waterColour = Colours.get(-1, -1, 225, -1);
		            } else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
		                yOffset -= 1;
		                waterColour = Colours.get(-1, 225, 115, -1);
		            } else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
		                waterColour = Colours.get(-1, 115, -1, 225);
		            } else {
		                yOffset -= 1;
		                waterColour = Colours.get(-1, 225, 115, -1);
		            }
		            screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour, 0x00, 1);
		            screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColour, 0x01, 1);
		        }
		        screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
		        screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop,
		                scale);

		        if (!isSwimming) {
		            screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,
		                    flipBottom, scale);
		            screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1)
		                    * 32, colour, flipBottom, scale);
		        }
		        if (username != null) {
		            Font.render(username, screen, xOffset - ((username.length() - 1) / 2 * 8), yOffset - 10,
		                    Colours.get(-1, -1, -1, 555), 1);
		        }
		}*/
		
		 /* public boolean hascollided(int xa, int ya) {
		 
	        int xMin = 0;
	        int xMax = 7;
	        int yMin = 3;
	        int yMax = 7;
	        for (int x = xMin; x < xMax; x++) {
	            if (isSolidTile(xa, ya, x, yMin)) {
	                return true;
	            }
	        }
	        for (int x = xMin; x < xMax; x++) {
	            if (isSolidTile(xa, ya, x, yMax)) {
	                return true;
	            }
	        }
	        for (int y = yMin; y < yMax; y++) {
	            if (isSolidTile(xa, ya, xMin, y)) {
	                return true;
	            }
	        }
	        for (int y = yMin; y < yMax; y++) {
	            if (isSolidTile(xa, ya, xMax, y)) {
	                return true;
	            }
	        }
	        return false;
	    }
	    */

	