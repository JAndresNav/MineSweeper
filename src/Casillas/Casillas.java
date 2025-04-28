package Casillas;

public abstract class Casillas {

    boolean revealed;

    public Casillas() {
        revealed = false;
    }//No esta revelada aun

    public abstract void reveal();
    public abstract boolean ShowTile();

}
