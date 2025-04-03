import Tiles.*;
import Tiles.Number;

public class MineSweeper {

    public static int alrededor(int[] Tablero, int i,int dirección){



        int fila = i / 8;
        int columna = i % 8;

        int izquierda = 0;
        int derecha = 0;
        int arriba = 0;
        int abajo = 0;

        int arribaIzquierda = 0;
        int arribaDerecha = 0;

        int abajoIzquierda = 0;
        int abajoDerecha = 0;

        if (columna > 0 && Tablero[i - 1] == 1) {
            izquierda = 1;
        }
        if (columna < 7 && Tablero[i + 1] == 1) {
            derecha = 1;
        }
        if (fila > 0 && Tablero[i - 8] == 1) {
            arriba = 1;
        }
        if (fila < 7 && Tablero[i + 8] == 1) {
            abajo = 1;
        }

        if (fila > 0 && columna > 0 && Tablero[i - 9] == 1) {
            arribaIzquierda = 1;
        }
        if (fila > 0 && columna < 7 && Tablero[i - 7] == 1) {
            arribaDerecha = 1;
        }
        if (fila < 7 && columna > 0 && Tablero[i + 7] == 1) {
            abajoIzquierda = 1;
        }
        if (fila < 7 && columna < 7 && Tablero[i + 9] == 1) {
            abajoDerecha = 1;
        }

        return switch (dirección) {
            case 0 -> izquierda;
            case 1 -> derecha;
            case 2 -> arriba;
            case 3 -> abajo;
            case 4 -> arribaIzquierda;
            case 5 -> arribaDerecha;
            case 6 -> abajoIzquierda;
            case 7 -> abajoDerecha;
            default -> 0;
        };
    }

    public static boolean minaRodeada(int[] Tablero, int i){

        boolean izquierda = false;
        boolean derecha = false;
        boolean arriba = false;
        boolean abajo = false;

        final int IZQUIERDA = 0;
        final int DERECHA = 1;
        final int ARRIBA = 2;
        final int ABAJO = 3;


        if (alrededor(Tablero,i,IZQUIERDA) == 1) {
            izquierda = true;
        }
        if (alrededor(Tablero,i,DERECHA) == 1) {
            derecha = true;
        }
        if (alrededor(Tablero,i,ARRIBA) == 1) {
            arriba = true;
        }
        if (alrededor(Tablero,i,ABAJO) == 1) {
            abajo = true;
        }

        return izquierda && derecha && arriba && abajo;
    }

    public static int numbers(int[] Tablero, int i){

        int contador = 0;

        final int IZQUIERDA = 0;
        final int DERECHA = 1;
        final int ARRIBA = 2;
        final int ABAJO = 3;

        final int ARRIBA_IZQUIERDA = 4;
        final int ARRIBA_DERECHA = 5;
        final int ABAJO_IZQUIERDA = 6;
        final int ABAJO_DERECHA = 7;


        if (alrededor(Tablero,i,IZQUIERDA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,DERECHA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,ARRIBA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,ABAJO) == 1) {
            contador++;
        }

        if (alrededor(Tablero,i,ARRIBA_IZQUIERDA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,ARRIBA_DERECHA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,ABAJO_IZQUIERDA) == 1) {
            contador++;
        }
        if (alrededor(Tablero,i,ABAJO_DERECHA) == 1) {
            contador++;
        }

        return contador;
    }

    public static void main(String[] args) {

        final int[] Tablero= new int[64];
        final Tile[] game = new Tile[64];

        int mines = 20;
        int random = 0;

        for (int i = 0; i < Tablero.length; i++) {

            while (true) {
                random = (int)(Math.random() * 2);
                if ((random == 1 && mines != 0) && !minaRodeada(Tablero, i)) {
                    Tablero[i] = random;
                    mines --;
                } else if (random == 1) {
                    continue;
                }else{
                Tablero[i] = random;}
                break;
            }

        }

        for (int i = 0; i < Tablero.length; i++) {
            switch (Tablero[i]) {
                case 1:
                    game[i]= new Bomb();
                    break;
                case 0:
                    if (numbers(Tablero, i) == 0) {
                        game[i] = new Clear();
                    }else{
                        game[i] = new Number(numbers(Tablero,i));
                    }
                    break;
            }

        }

        for (int i = 0; i < Tablero.length; i++) {
            System.out.print("[ ");
            System.out.print(Tablero[i]);
            System.out.print(" ] ");
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }

        for (int i = 0; i < game.length; i++) {
            System.out.print("[ ");
            System.out.print(game[i]);
            System.out.print(" ] ");
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }

    }
}