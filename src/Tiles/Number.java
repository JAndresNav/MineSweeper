package Tiles;

public class Number extends Tile {

    private int alrededor;

    public Number(int contador){

    }

    @Override
    public void reveal() {

    }

    @Override
    public boolean ShowTile() {
        return false;
    }

    public void setAlrededor(int contador) {
        alrededor = contador;
    }

    @Override
    public String toString() {
        return "Number{" +
                "alrededor=" + alrededor +
                '}';
    }
}
