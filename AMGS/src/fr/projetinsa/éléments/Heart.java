package fr.projetinsa.éléments;

import com.algoproj2020.Level;
import com.algoproj2020.Screen;

import fr.projetinsa.graphics.Sprite;

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

public class Heart extends Entity{

protected Sprite sprite; 
int x=0;
int y=0;
int [] AreaCovered = new int[4];
Level L;
int[] size = new int[2];


public Heart(Level L, double x, double y, int [] size) {
	super(L);
	
	this.x = (int) x;
	this.y = (int) y;
	this.size[0]=size[0];
	this.size[1]=size[1];
	
	AreaCovered[0]= (int) x;
	AreaCovered[1]= (int) x + size[0];
	AreaCovered[2]= (int) y;
	AreaCovered[3]= (int) y + size[1];
	
//	ssB = new SpriteSheetBullet("images/boulette.png");
}


@Override
public void update() {
	
}


@Override
public void rendu(Screen screen) {
	screen.renderBullet((int)this.x,(int)this.y,Sprite.Heart);
}
}
