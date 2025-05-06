package Casillas;

public class Number extends Casillas {

    private int alrededor; //Numero de minas alrededor.

    public Number(int contador){
        setAlrededor(contador);
    } //Establecemos el numero desde el constructor.

    @Override
    public void reveal() {

    }

    @Override
    public boolean ShowTile() {
        return false;
    } //No se revela hasta que se indique.

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
