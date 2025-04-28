package Casillas;

public class Number extends Casillas {

    private int alrededor;

    public Number(int contador){
        setAlrededor(contador);
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
