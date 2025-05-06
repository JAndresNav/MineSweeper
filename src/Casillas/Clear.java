package Casillas;

public class Clear extends Casillas {


    @Override
    public void reveal() { //Simplemente la casilla esta vacia (No hace nada)

    }

    @Override
    public boolean ShowTile() {
        return false; //No se muestra nada hasta que el jugador haga una interaccion.
    }

    @Override
    public String toString() {
        return "Clear{}";
    }
}
