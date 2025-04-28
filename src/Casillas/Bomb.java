package Casillas;

public class Bomb extends Casillas {


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
