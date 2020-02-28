package fr.projetinsa.éléments;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;

public class Player extends Mob {
	private UserInputs input;
	private int colour=Colours.get(-1,111,145,543);
	public Player(Level level, int x, int y, UserInputs input) {
		super(level,"Player",x,y,1);
		this.input=input;
	}
	@Override
	public boolean hascollided(int xa, int ya) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update() {
		int xa=0;
		int ya=0;

		if(input.up.isPressed()){ya--;}
		if(input.down.isPressed()){ya++;}
		if(input.right.isPressed()){xa++;}
		if(input.left.isPressed()) {xa--;}
		if(xa!=0 || ya!=0) {
			move(xa,ya);
			ismoving=true;
		}else {
			ismoving=false;
		}
	}

	@Override
	public void rendu(Screen screen) {
		int xtile=0;// coordonnées du coin gauche superieur du perso
		int ytile=28;// à adapter en fonction du sprite
		int modifier=8*scale;
		int xoffset=x-modifier/2;
		int yoffset=y-modifier/2-4;
		int walkingSpeed = 4;
        int flipTop = (numbsteps >> walkingSpeed) & 1;
        int flipBottom = (numbsteps >> walkingSpeed) & 1;
		screen.render(xoffset + (modifier * flipBottom), yoffset + modifier, xtile + (ytile + 1) * 32, colour,flipBottom, scale);
        screen.render(xoffset + modifier - (modifier * flipBottom), yoffset + modifier, (xtile + 1) + (ytile + 1)* 32, colour, flipBottom, scale);
        screen.render(xoffset + (modifier * flipTop), yoffset, xtile + ytile * 32, colour, flipTop, scale);
        screen.render(xoffset + modifier - (modifier * flipTop), yoffset, (xtile + 1) + ytile * 32, colour, flipTop,scale);
	}

}
