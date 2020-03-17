package com.algoproj2020;

public class Colours {
	public static int get(int colour1,int colour2,int colour3,int colour4) {
		return ((get(colour4)<<24)+(get(colour3)<<16)+(get(colour2)<<8)+get(colour1));
	}

	public static int get(int colour) {
			if(colour<0)return 255;// If the colour is strictly negative then it is considered transparent.
			int r=colour/100%10;// We retrieve the right bit
			int g=colour/10%10;// We retrieve the central bit
			int b=colour%10;// We retrieve the left bit
			return r*36+g*6+b;// On retourne l'index du tableau colour associé à cette couleur.
		}
}
