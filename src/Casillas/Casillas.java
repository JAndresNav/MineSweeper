package Casillas;

public abstract class Casillas {

    boolean revealed; //Nos indica si ya fue revelada la casilla.

    public Casillas() {
        revealed = false; //Por defecto ninguna casilla esta revelada
    }//No esta revelada aun

    public abstract void reveal(); //Metodo que nos define que pasa al revelar una casilla
    public abstract boolean ShowTile(); //Metodo que nos dice si tiene que mostrarse en pantalla o no

}
