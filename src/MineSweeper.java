import java.util.Random; //Importamos la clase random para generar numero aleatorios.
import Casillas.*; //Importamos todas las clases del paquete casillas
import Casillas.Number;

// Clase principal que genera y configura el tablero del juego Buscaminas:
public class MineSweeper { //Esqueleto


    //Calcula cuántas minas (valor 1 en el arreglo) rodean una casilla en la posición 'i' del tablero.
    public static int numbers(int[] Tablero, int i) {
        int fila = i / 8; //Calculamos la fila de las casillas
        int columna = i % 8; //Calculamos la columna de las casillas.
        int contador = 0; //Contador de minar alrededor.

        // Se revisan las 8 posiciones adyacentes a la casilla (arriba, abajo, izquierda, derecha y diagonales).
        if (columna > 0 && Tablero[i - 1] == 1) contador++; // izquierda
        if (columna < 7 && Tablero[i + 1] == 1) contador++; // derecha
        if (fila > 0 && Tablero[i - 8] == 1) contador++;    // arriba
        if (fila < 7 && Tablero[i + 8] == 1) contador++;    // abajo
        if (fila > 0 && columna > 0 && Tablero[i - 9] == 1) contador++; // diagonal arriba-izquierda
        if (fila > 0 && columna < 7 && Tablero[i - 7] == 1) contador++; // diagonal arriba-derecha
        if (fila < 7 && columna > 0 && Tablero[i + 7] == 1) contador++; // diagonal abajo-izquierda
        if (fila < 7 && columna < 7 && Tablero[i + 9] == 1) contador++; // diagonal abajo-derecha

        return contador; //Devolvemos la cantidad de minas vecinas.
    }


    //Mezcla aleatoriamente los elementos del arreglo del tablero para distribuir minas al azar:
    public static int[] ShuffleArray(int[] Tablero) {
        Random rand = new Random();
        for (int i = Tablero.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); //Generamos un indice aleatorio entre 0 e i
            int temp = Tablero[i]; //Nos va a cambiar los valores del Array
            Tablero[i] = Tablero[j];
            Tablero[j] = temp;
        }
        return Tablero;
    }

    //Genera el juego de Buscaminas completo como un arreglo de objetos tipo Casilla.
    public static Casillas[] GenerateGame() {
        int[] Tablero = new int[64];  // Arreglo base que representa el estado lógico del tablero (64 casillas).
        Casillas[] game = new Casillas[64]; //Arreglo de objetos que representa el tablero visual del juego.
        int mines = 10; //Numero de minas que se colocaran, puedes poner el numero que quieras que no exceda el tablero

        //Genera aleatoriamente 10 minas (representadas con 1) y el resto con 0 (espacios vacíos).
        for (int i = 0; i < Tablero.length; i++) {
                int random = (int)(Math.random() * 2); //Genera 0 y 1 aleatoriamente.
                if (random == 1 && mines != 0) {
                    //Itera y se actualiza hasta que complete el tablero, si hay un uno fuera del rango ya no se agrega
                    Tablero[i] = 1; //Colocamos una mina
                    mines--; //Reducimos el contador de minas restante.
                } else {
                    Tablero[i] = 0; //Colocamos un espacio vacio
                }
            }

        // Se revuelven las posiciones de las minas para que queden distribuidas aleatoriamente.
        Tablero = ShuffleArray(Tablero);

        // Se genera el arreglo visual del juego con objetos que representan cada tipo de casilla.
        for (int i = 0; i < Tablero.length; i++) {
            //Arreglo de objetos
            if (Tablero[i] == 1) {
                game[i] = new Bomb(); //Si hay una mina se crea un objeto bomb
            } else {
                int num = numbers(Tablero, i); //Se cuentan las minas vecinas
                game[i] = (num == 0) ? new Clear() : new Number(num); // Si hay 0 minas, casilla vacía (Clear); si no, número (Number).
            }
        }

        return game; //Se devuelve el tablero del juego configurado.
    }
}
