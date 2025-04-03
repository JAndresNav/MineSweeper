import Tiles.*;

import java.util.Random;

public class MineSweeper {

    public static boolean minaRodeada(int[] Tablero, int i){
        int fila = i / 8;
        int columna = i % 8;

        boolean izquierda = false;
        boolean derecha = false;
        boolean arriba = false;
        boolean abajo = false;

        if (columna > 0 && Tablero[i - 1] == 1) {
            izquierda = true;
        }
        if (columna < 7 && Tablero[i + 1] == 1) {
            derecha = true;
        }
        if (fila > 0 && Tablero[i - 8] == 1) {
            arriba = true;
        }
        if (fila < 7 && Tablero[i + 8] == 1) {
            abajo = true;
        }

        return izquierda && derecha && arriba && abajo;
    }

    public static void main(String[] args) {

        final int[] Tablero= new int[64];
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
            System.out.print("[ ");
            System.out.print(Tablero[i]);
            System.out.print(" ] ");
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }


    }
}