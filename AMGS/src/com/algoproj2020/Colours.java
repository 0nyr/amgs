package com.algoproj2020;

public class Colours {
	/**
	 * Les entiers que prend cette méthode en paramètre sont des entiers compris entre 0 et 555
	 * On utilise la représentation des couleurs que l'on a définit dans AmGS
	 * @param colour1 
	 * @param colour2
	 * @param colour3
	 * @param colour4
	 * @return
	 */
	public static int get(int colour1,int colour2,int colour3,int colour4) {
		return ((get(colour4)<<24)+(get(colour3)<<16)+(get(colour2)<<8)+get(colour1)); 
		/*Dans notre sprite sheet on a quatre nuances de gris différentes numérotées de 1 à 4, 
		 *de la couleur la plus claire à la couleur la plus foncée. Chacune de ces nuances est codée sur 8 bits. 
		 */
	}

	public static int get(int colour) {
			if(colour<0)return 255;// If the colour is strictly negative then it is considered transparent.
			int r=colour/100%10;// We retrieve the right bit
			int g=colour/10%10;// We retrieve the central bit
			int b=colour%10;// We retrieve the left bit
			return r*36+g*6+b;// On retourne l'index du tableau colour associé à cette couleur.
		}
}
