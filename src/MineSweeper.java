import java.util.Random;
import Casillas.*;
import Casillas.Number;

public class MineSweeper { // Esqueleto / Backend de la aplicación


    public static int numbers(int[] Tablero, int i) {
        int fila = i / 8;
        int columna = i % 8;
        int contador = 0;

        // Verifica las 8 direcciones alrededor de la casilla
        if (columna > 0 && Tablero[i - 1] == 1) contador++;                     // izquierda
        if (columna < 7 && Tablero[i + 1] == 1) contador++;                     // derecha
        if (fila > 0 && Tablero[i - 8] == 1) contador++;                        // arriba
        if (fila < 7 && Tablero[i + 8] == 1) contador++;                        // abajo
        if (fila > 0 && columna > 0 && Tablero[i - 9] == 1) contador++;         // diagonal arriba-izquierda
        if (fila > 0 && columna < 7 && Tablero[i - 7] == 1) contador++;         // diagonal arriba-derecha
        if (fila < 7 && columna > 0 && Tablero[i + 7] == 1) contador++;         // diagonal abajo-izquierda
        if (fila < 7 && columna < 7 && Tablero[i + 9] == 1) contador++;         // diagonal abajo-derecha

        return contador;
    }


    public static int[] shuffleArray(int[] Tablero) { //Mezcla aleatoriamente los elementos de un arreglo de enteros
        Random rand = new Random();
        for (int i = Tablero.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); //elige el siguiente elemento aleatorio
            int temp = Tablero[i];      // Intercambia valores
            Tablero[i] = Tablero[j];
            Tablero[j] = temp;
        }
        return Tablero;
    }

    public static Casillas[] generateGame() {
        int[] tablero = new int[64];         // Representación interna del tablero (1 = mina, 0 = casilla libre)
        Casillas[] game = new Casillas[64];  // Arreglo de objetos que representa el juego final
        int mines = 10;                      // Número de minas a colocar

        // Genera el tablero con minas distribuidas aleatoriamente
        for (int i = 0; i < tablero.length; i++) {
            int random = (int)(Math.random() * 2); // Genera 0 o 1 aleatoriamente

            if (random == 1 && mines != 0 /*&& !minaRodeada(tablero, i)*/) {
                tablero[i] = 1; // Coloca mina
                mines--;
            } else {
                tablero[i] = 0; // Casilla vacía
            }
        }

        // Mezcla el tablero para distribuir aleatoriamente las minas (ahora si)
        tablero = shuffleArray(tablero);

        // Usando el tablero de enteros, agrega las respectivos objetos (mina, numero o celda vacía) a la lista
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == 1) {
                game[i] = new Bomb(); // Casilla con mina
            } else {
                int num = numbers(tablero, i); // Calcula minas vecinas
                game[i] = (num == 0) ? new Clear() : new Number(num); // Casilla vacía o con número (sustituto de if)
            }
        }

        return game; // Devuelve el arreglo completo de casillas
    }
}
