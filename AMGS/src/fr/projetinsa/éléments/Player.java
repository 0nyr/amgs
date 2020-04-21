package fr.projetinsa.éléments;

import com.algoproj2020.Colours;
import com.algoproj2020.Level;
import com.algoproj2020.Screen;
import com.algoproj2020.UserInputs;

import fr.projetinsa.graphics.Font;
import fr.projetinsa.graphics.Sprite;

public class Player extends Mob {
	
	private UserInputs input;// Key handler
	private int colour=Colours.get(-1,500,555,0);// Ici on contrôle les couleurs du sprite du joueur.
	//Colours.get(couleur des parties noirs,.., couleur des parties blanches) 
	/*Dans le sprite du joueur, la nuance de gris clair prendra la couleur définit en RGB de la manière suivante:
	 * R=5*255/5
	 * G=3*255/5
	 * B=0*255/5*/
	protected boolean isSwimming = false;// Is the player swimming?
    private int tickCount = 0;// How many times this player has been updated
    private String username;// Username of the player
    
    /**
     * Constructor of the class
     * @param level the level in which is the player
     * @param x the x coordinate of the player 
     * @param y the y coordinate of the player
     * @param input the key handler used to move the player 
     */
	public Player(Level level, int x, int y, UserInputs input, String username) {
		super(level,"Player",x,y,1);
		this.input=input;
		this.username=username;
	}
	/**
	 * Getter for the username attribute
	 */
	public String getUsername() {
        return this.username;
    }
	/**
	 * Updates the informations on the interaction between the player and its environment.
	 */
	@Override
	public void update() {
		int xa=0;/* Indicate in the direction of the player's motion:
		xa= -1 <-> the player is moving to the left; xa=0 the player is not moving along the x axis; xa= 1 the player is moving to the right
		*/
		int ya=0;//idem
		if(input.space.isPressed())shoot();// On tire en appuyant sur la barre espace.
		if(input.up.isPressed()){ya--;}
		if(input.down.isPressed()){ya++;}
		if(input.right.isPressed()){xa++;}
		if(input.left.isPressed()) {xa--;}
		if(xa!=0 || ya!=0) {/* If the keys on corresponding to the movement of the character are pressed, 
		then the character starts moving.*/
			move(xa,ya);
			ismoving=true;
		}else {// Otherwise the character doesn't move.
			ismoving=false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {
		/*If the character goes on tile correponding to water, the he swims.
		 *Remark: we have divided the coordinates of the player by 2^3 in the if statement
		 *because the size of the player sprite is 8 (2^3) pixels.*/
			isSwimming = true;
		}
	    if (isSwimming && level.getTile(this.x >> 3, this.y >> 3).getId() != 3) {
	    /*If the character is already swimming and if he goes on a tile corresponding to something else than water,
	     *then he stops swimming.*/
	    	isSwimming = false;
	    }
	    tickCount++;
	}

	private void shoot() {
		// TODO Auto-generated method stub
		
	}
	public void render(Screen screen) {
		//screen.renderPlayer( x, y, Sprite.pokemon0);
	
		int xTile = 0;// x coordinate of the player sprite 
		int yTile = 28;// y coordinate of the player sprite
		int walkingSpeed = 4;//nombre de mouvement de jambes par
		//plus walking speed diminue plus la fréquence des pas est importante et inversement.
		int flipTop = (numbsteps >> walkingSpeed) & 1;
		/*& 1 renvoie 0 si le nombre est paire et 1 si le nombre est impaire.
		 * */
		int flipBottom = (numbsteps >> walkingSpeed) & 1;
		
		int modifier = 8 * scale;/* ont a besoin du modifier pour adapter le personnage à l'échelle 
		se fait tuile par tuile et. la tailles des tuiles est de 8 pixels d'où le facteur 8*/
		int xOffset = x - modifier / 2;
		int yOffset = y - modifier/2 -4;
		/*Les deux lignes de code ci dessus permettent de créer des coordonnées xOffset et yOffset qui repère 
		 * le point situé entre les pieds du sprite du personnage et non plus le coin supérieur gauche du sprite personnage comme le faisaient 
		 * les coordonnées x et y*/
		if (isSwimming) {
			int waterColour = 0;
			yOffset += 4;
			if (tickCount % 60 < 15) {
				waterColour = Colours.get(-1, -1, 350, -1);// très important de comprendre le fonctionnement de cette méthoode
			} else if (15 <= tickCount % 60 && tickCount % 60 < 30) {
				yOffset -= 1;// On fait osciller le personnage de haut en bas 
				waterColour = Colours.get(-1, 350, 250, -1);
			} else if (30 <= tickCount % 60 && tickCount % 60 < 45) {
				waterColour = Colours.get(-1, 250, -1, 350);
			} else {
				yOffset -= 1;// On fait osciller le personnage de haut en bas une deuxième fois pour donner de la rapidité au mouvement.
				waterColour = Colours.get(-1, 350, 250, -1);
			}

		/* La partie ci-dessous s'ocuppe de rendre les mouvements d'eau 
		 * crées par la nage du personnage à la place de son corps lorque celui-ci est dans l'eau.*/
			screen.render(xOffset, yOffset + 3, 0 + 27 * 32, waterColour, 0x00, 1);//très important de comprendre le fonctionnement de cette méthode
			screen.render(xOffset + 8, yOffset + 3, 0 + 27 * 32, waterColour, 0x01, 1);
		}
		if (movingdir == 1 || movingdir==0) {
			/*If the player is moving down, then we display the sprite corresponding to the downward walk of the player*/
				xTile += 2*movingdir;
				// La partie du code ci-dessous ne s'occupe que de rendre le visage du personnage.
				screen.render(xOffset, yOffset, xTile + yTile * 32, colour, 0x00, scale);
				screen.render(xOffset + modifier, yOffset, (xTile + 1) + yTile * 32, colour, 0x00,scale);
			} else if (movingdir > 1) {
				xTile += 4+ ((numbsteps >> walkingSpeed) & 1) * 2;
				//On récupère les sprites de dépalcement horizontaux en fonction du nombre de pas
				flipTop = (movingdir - 1) % 2;
				// si le joueur va à droite (movingdir=3) fliptop =0 s'il va à gauche fliptop=1
				// La partie du code ci-dessous ne s'occupe que de rendre le visage du personnage.
				screen.render(xOffset + (modifier * flipTop), yOffset, xTile + yTile * 32, colour, flipTop, scale);
				screen.render(xOffset + modifier - (modifier * flipTop), yOffset, (xTile + 1) + yTile * 32, colour, flipTop,scale);
			}
		

		if (!isSwimming) {
		// La partie du code ci-dessous s'occupe de rendre le corps du perso quand il ne nage pas
			screen.render(xOffset + (modifier * flipBottom), yOffset + modifier, xTile + (yTile + 1) * 32, colour,flipBottom, scale);
			screen.render(xOffset + modifier - (modifier * flipBottom), yOffset + modifier, (xTile + 1) + (yTile + 1) * 32, colour, flipBottom, scale);
		}
		if (username != null) {
			Font.render(username, screen, xOffset - ((username.length() - 1) / 2 * 8), yOffset - 10,
					Colours.get(-1, -1, -1, 555), 1);
		}
	}
	/**
	 * Test if the player has collided against a solid tile while moving.
	 * @param xa xa=1 <-> mob moves to the right; xa= -1 <-> mob moves to the left;
	 * xa= 0 <-> mob doesn't move
	 * @param ya ya=1 <-> mob moves downward; ya= -1 <-> mob moves upward
	 * ya= 0 <-> mob doesn't move    
	 * @param x Enables to adapt the collision test to the shape of the player.
	 * Indeed, we don't want the collision test to be executed at the borders of the player sprite but on the player's body.
	 * @param y idem
	 */
	public boolean hascollided(int xa, int ya) {
        int xMin = 0;// the coordinate of the player's body on which we focus to detect any collision when he moves to the left.
        int xMax = 7;// the coordinate of the player's body on which we focus to detect any collision when he moves to the right.
        int yMin = 3;// the coordinate of the player's body on which we focus to detect any collision when he moves upward.
        int yMax = 7;// the coordinate of the player's body on which we focus to detect any collision when he moves downward.
        for (int x = xMin; x < xMax; x++) {
            if (isSolidTile(xa, ya, x, yMin)) { // Can the player move upward without any collision?
                return true;
            }
        }
        for (int x = xMin; x < xMax; x++) {// Can the player move downward without any collision?
            if (isSolidTile(xa, ya, x, yMax)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {// Can the player move to the left without any collision?
            if (isSolidTile(xa, ya, xMin, y)) {
                return true;
            }
        }
        for (int y = yMin; y < yMax; y++) {// Can the player move to the right without any collision?
            if (isSolidTile(xa, ya, xMax, y)) {
                return true;
            }
        }
        return false;
    }

}
