import java.util.Random;
import Casillas.*;
import Casillas.Number;

public class MineSweeper { //Esqueleto

    public static int numbers(int[] Tablero, int i) {
        int fila = i / 8;
        int columna = i % 8;
        int contador = 0;

        if (columna > 0 && Tablero[i - 1] == 1) contador++; // izquierda
        if (columna < 7 && Tablero[i + 1] == 1) contador++; // derecha
        if (fila > 0 && Tablero[i - 8] == 1) contador++;    // arriba
        if (fila < 7 && Tablero[i + 8] == 1) contador++;    // abajo
        if (fila > 0 && columna > 0 && Tablero[i - 9] == 1) contador++; // diagonal arriba-izquierda
        if (fila > 0 && columna < 7 && Tablero[i - 7] == 1) contador++; // diagonal arriba-derecha
        if (fila < 7 && columna > 0 && Tablero[i + 7] == 1) contador++; // diagonal abajo-izquierda
        if (fila < 7 && columna < 7 && Tablero[i + 9] == 1) contador++; // diagonal abajo-derecha

        return contador;
    }

    /*public static int alrededor(int[] Tablero, int indice, int dirección) { //Busca si hay una mina en la direccion que se le manda
        //Se determina la fila y la columna para que se pueda buscar más facil y no reutilizar formulas
        int fila = indice / 8;
        int columna = indice % 8;

        switch (dirección) {
            case 0 -> { return (columna > 0 && Tablero[indice - 1] == 1) ? 1 : 0; } //Izquierdo central
            case 1 -> { return (columna < 7 && Tablero[indice + 1] == 1) ? 1 : 0; } //Derecho central
            case 2 -> { return (fila > 0 && Tablero[indice - 8] == 1) ? 1 : 0; } //Superior
            case 3 -> { return (fila < 7 && Tablero[indice + 8] == 1) ? 1 : 0; } //inferior
            case 4 -> { return (fila > 0 && columna > 0 && Tablero[indice - 9] == 1) ? 1 : 0; } //Izquieda superior
            case 5 -> { return (fila > 0 && columna < 7 && Tablero[indice - 7] == 1) ? 1 : 0; } //derecha superior
            case 6 -> { return (fila < 7 && columna > 0 && Tablero[indice + 7] == 1) ? 1 : 0; } // izquierda inferior
            case 7 -> { return (fila < 7 && columna < 7 && Tablero[indice + 9] == 1) ? 1 : 0; } //derecha inferior
            default -> { return 0; }
        }
    }

    /*public static boolean minaRodeada(int[] Tablero, int i) { //No puede estar
        return alrededor(Tablero, i, 0) == 1 &&
                alrededor(Tablero, i, 1) == 1 &&
                alrededor(Tablero, i, 2) == 1 &&
                alrededor(Tablero, i, 3) == 1;
    }

    public static int numbers(int[] Tablero, int i) {
        int contador = 0;
        for (int d = 0; d < 8; d++) {
            contador += alrededor(Tablero, i, d);
        }
        return contador;
    } */

    public static int[] ShuffleArray(int[] Tablero) {
        //Mezclar el tablero
        Random rand = new Random();
        for (int i = Tablero.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = Tablero[i]; //Nos va a cambiar los valores del Array
            Tablero[i] = Tablero[j];
            Tablero[j] = temp;
        }
        return Tablero;
    }

    public static Casillas[] generarJuego() {
        int[] Tablero = new int[64];
        Casillas[] game = new Casillas[64]; //Pendiente
        int mines = 10; //Limite de minas, puedes poner el numero que quieras que no exceda el tablero

        for (int i = 0; i < Tablero.length; i++) {
            //while (true) {
                //Ciclo infinito que nos asegura que simepre haya valores en todas las casillas
                int random = (int)(Math.random() * 2);
                if (random == 1 && mines != 0 /*&& !minaRodeada(Tablero, i)*/) {
                    //Itera y se actualiza hasta que complete el tablero, si hay un uno fuera del rango ya no se agrega
                    Tablero[i] = 1;
                    mines--;
                    //Le resta la mina hasta no tener más,
                //} else if (random == 1) {
                  //  continue; //obligamos a seguir generando numeros random
                } else {
                    Tablero[i] = 0;
                }
                //break;
            }
        //}

        Tablero = ShuffleArray(Tablero);
        //Revuelve el tablero

        for (int i = 0; i < Tablero.length; i++) {
            //Arreglo de objetos
            if (Tablero[i] == 1) {
                game[i] = new Bomb();
            } else {
                int num = numbers(Tablero, i);
                game[i] = (num == 0) ? new Clear() : new Number(num);
            }
        }

        return game;
    }
}
