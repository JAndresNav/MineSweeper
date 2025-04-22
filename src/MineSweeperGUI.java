import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Tiles.Bomb;
import Tiles.Clear;
import Tiles.Explode;
import Tiles.Flag;
import Tiles.Number;
import Tiles.Tile;

public class MineSweeperGUI extends JFrame {

    private final int SIZE = 8;
    private final JButton[] buttons = new JButton[SIZE * SIZE];
    private final Tile[] game;

    public MineSweeperGUI(Tile[] game) {
        this.game = game;
        initGUI();
    }

    private void initGUI() {
        setTitle("Buscaminas");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(SIZE, SIZE));

        for (int i = 0; i < SIZE * SIZE; i++) {
            final int index = i;
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttons[i].addActionListener(e -> revealTile(index));
            add(buttons[i]);
        }

        setVisible(true);
    }

    private void revealTile(int index) {
        Tile tile = game[index];
        tile.reveal();

        if (tile instanceof Bomb) {
            buttons[index].setText("💣");
            buttons[index].setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "¡BOOM! Perdiste.");
            revealAll();
        } else if (tile instanceof Clear) {
            buttons[index].setText("");
            buttons[index].setBackground(Color.LIGHT_GRAY);
        } else if (tile instanceof Number) {
            Number numTile = (Number) tile;
            String text = numTile.toString().replaceAll("[^0-9]", "");
            buttons[index].setText(text);
            buttons[index].setBackground(Color.WHITE);
        }

        buttons[index].setEnabled(false);
    }

    private void revealAll() {
        for (int i = 0; i < game.length; i++) {
            if (buttons[i].isEnabled()) {
                revealTile(i);
            }
        }
    }

    public static void main(String[] args) {
        // Aquí copiamos el código del main de tu clase MineSweeper
        int[] Tablero= new int[64];
        final Tile[] game = new Tile[64];

        int mines = 20;
        int random;

        for (int i = 0; i < Tablero.length; i++) {
            while (true) {
                random = (int)(Math.random() * 2);
                if ((random == 1 && mines != 0) && !MineSweeper.minaRodeada(Tablero, i)) {
                    Tablero[i] = random;
                    mines --;
                } else if (random == 1) {
                    continue;
                } else {
                    Tablero[i] = random;
                }
                break;
            }
        }

        Tablero = MineSweeper.ShuffleArray(Tablero);

        for (int i = 0; i < Tablero.length; i++) {
            switch (Tablero[i]) {
                case 1:
                    game[i] = new Bomb();
                    break;
                case 0:
                    if (MineSweeper.numbers(Tablero, i) == 0) {
                        game[i] = new Clear();
                    } else {
                        game[i] = new Number(MineSweeper.numbers(Tablero, i));
                    }
                    break;
            }
        }

        // Lanzamos la GUI
        SwingUtilities.invokeLater(() -> new MineSweeperGUI(game));
    }
}
