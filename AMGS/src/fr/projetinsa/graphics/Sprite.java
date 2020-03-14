package fr.projetinsa.graphics;

public class Sprite {
	
	public final int SIZE;
	private int x,y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite pokemon0=new Sprite(64,0,0, SpriteSheet.sheet);
	public static Sprite pokemon1=new Sprite(64,1,0, SpriteSheet.sheet);
	public static Sprite pokemon2=new Sprite(64,2,0, SpriteSheet.sheet);
	public static Sprite pokemon3=new Sprite(64,3,0, SpriteSheet.sheet);
	
	public Sprite(int size, int x, int y , SpriteSheet sheet) {
		SIZE=size;
		pixels =new int [size*size];
		this.x=x*size;
		this.y=y*size;
		this.sheet=sheet;
		loadsprite();
	}
	/**
	 * Cette méthode extrait un sprite donné du spritesheet.
	 */
	public void loadsprite() {
		for (int y=0;y<SIZE;y++) {
			for (int x=0;x<SIZE;x++) {
				pixels[x+y*SIZE]=sheet.pixels[(x+this.x)+(y+this.y)*sheet.width];
			}
		}
	}
}
