package Casillas;

public class Bomb extends Casillas {


    //Constructor vacio.
    public Bomb(){

    }

    @Override
    public void reveal() {
        //Cuando se revela una bomba crea una instancia de explode.
        new Explode();
    }

    @Override
    public boolean ShowTile() {
        return false; //Nos indica que esta casilla aun no ha sido revelada
    }

    @Override
    public String toString() {
        return "Bomb{}"; //ToString que es una instancias de la clase bomb
    }
}
