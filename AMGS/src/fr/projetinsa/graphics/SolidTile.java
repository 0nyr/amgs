package fr.projetinsa.graphics;

public class SolidTile extends BasicTile {

    public SolidTile(int id, int x, int y, int tileColour, int levelColour) {
        super(id, x, y, tileColour, levelColour);
        this.solid = true;
    }

}

