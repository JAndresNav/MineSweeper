package Tiles;

public abstract class Tile {

    boolean revealed;

    public Tile() {
        revealed = false;
    }

    public abstract void reveal();
    public abstract boolean ShowTile();

}
