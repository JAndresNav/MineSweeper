package Tiles;

public class Bomb extends Tile {


    public Bomb(){

    }

    @Override
    public void reveal() {
        new Explode();
    }

    @Override
    public boolean ShowTile() {
        return false;
    }

    @Override
    public String toString() {
        return "Bomb{}";
    }
}
