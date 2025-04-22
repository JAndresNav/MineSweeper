import java.util.Random;
import Tiles.*;
import Tiles.Number;

public class MineSweeper {

    public static int alrededor(int[] Tablero, int i, int dirección) {
        int fila = i / 8;
        int columna = i % 8;

        switch (dirección) {
            case 0 -> { return (columna > 0 && Tablero[i - 1] == 1) ? 1 : 0; }
            case 1 -> { return (columna < 7 && Tablero[i + 1] == 1) ? 1 : 0; }
            case 2 -> { return (fila > 0 && Tablero[i - 8] == 1) ? 1 : 0; }
            case 3 -> { return (fila < 7 && Tablero[i + 8] == 1) ? 1 : 0; }
            case 4 -> { return (fila > 0 && columna > 0 && Tablero[i - 9] == 1) ? 1 : 0; }
            case 5 -> { return (fila > 0 && columna < 7 && Tablero[i - 7] == 1) ? 1 : 0; }
            case 6 -> { return (fila < 7 && columna > 0 && Tablero[i + 7] == 1) ? 1 : 0; }
            case 7 -> { return (fila < 7 && columna < 7 && Tablero[i + 9] == 1) ? 1 : 0; }
            default -> { return 0; }
        }
    }

    public static boolean minaRodeada(int[] Tablero, int i) {
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
    }

    public static int[] ShuffleArray(int[] Tablero) {
        Random rand = new Random();
        for (int i = Tablero.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = Tablero[i];
            Tablero[i] = Tablero[j];
            Tablero[j] = temp;
        }
        return Tablero;
    }

    public static Tile[] generarJuego() {
        int[] Tablero = new int[64];
        Tile[] game = new Tile[64];
        int mines = 10;

        for (int i = 0; i < Tablero.length; i++) {
            while (true) {
                int random = (int)(Math.random() * 2);
                if (random == 1 && mines != 0 && !minaRodeada(Tablero, i)) {
                    Tablero[i] = 1;
                    mines--;
                } else if (random == 1) {
                    continue;
                } else {
                    Tablero[i] = 0;
                }
                break;
            }
        }

        Tablero = ShuffleArray(Tablero);

        for (int i = 0; i < Tablero.length; i++) {
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
