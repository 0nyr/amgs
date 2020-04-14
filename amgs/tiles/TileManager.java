package amgs.tiles;

public class TileManager {

    // container for the different types of tiles
    public static Tile[] tiles = new Tile[256];

    // tile types id
    public static final int ID_EMPTY_ZONE = 0;
    public static final int ID_SHIP = 1;
    public static final int ID_GROUND = 2;
    public static final int ID_WALL = 4;

    // tile types
    public static Tile emptyZone = new EmptyZoneTile(0);
    public static Tile ship = new LandingZone(1);
    public static Tile ground = new GroundTile(2);
    public static Tile wall = new WallTile(4);

}