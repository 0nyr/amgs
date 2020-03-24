package fr.projetinsa.éléments;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Font;
import fr.projetinsa.graphics.Sprite;

public class Player extends Mob {
	
	UserInputs input;
	private int colour=Colours.get(-1,111,145,543);
	protected boolean isSwimming = false;
    private int tickCount = 0;
    private String username;
    int direction;
    
	public Player(Level level, int x, int y, UserInputs input) {
		super(level,"Player",x,y,1);
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

		
		if(input.up.isPressed()){ya=ya-2;
		direction=0;
		}
		if(input.down.isPressed()){ya=ya+2;
		direction=2;}
		if(input.right.isPressed()){xa=xa+2;
		direction=1;}
		if(input.left.isPressed()) {xa=xa-2;
		direction=3;}
		if(xa!=0 || ya!=0) {
			move(xa,ya);
			ismoving=true;
		}else {
			ismoving=false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
			
			isSwimming = true;
		}
	    if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
	    	isSwimming = false;
	    }
	    tickCount++;
	    
	}

	
	public void rendu(Screen screen) {
	
		 int xTile = 0;
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
	}



	public boolean hascollided(int xa, int ya) {
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

}
