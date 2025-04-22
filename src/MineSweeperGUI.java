import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Tiles.*;
import Tiles.Number;

public class MineSweeperGUI extends JFrame {

    private JButton[] buttons = new JButton[64];
    private Tile[] game;
    private boolean[] isFlagged = new boolean[64];
    private int revealedCount = 0;

    public MineSweeperGUI(Tile[] initialGame) {
        this.game = initialGame;

        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(8, 8));
        buttons = new JButton[64];
        isFlagged = new boolean[64];
        revealedCount = 0;

        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.BOLD, 16));
            buttons[i].setFocusable(false);

            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (!isFlagged[index]) {
                            revealTile(index);
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        toggleFlag(index);
                    }
                }
            });

            gridPanel.add(buttons[i]);
        }

        JPanel controlPanel = new JPanel();
        JButton restartBtn = new JButton("Reiniciar");
        JButton exitBtn = new JButton("Salir");

        restartBtn.addActionListener(e -> restartGame());
        exitBtn.addActionListener(e -> System.exit(0));

        controlPanel.add(restartBtn);
        controlPanel.add(exitBtn);

        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(500, 550);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void revealTile(int index) {
        Tile tile = game[index];

        if (!buttons[index].isEnabled() || isFlagged[index]) return;

        if (tile instanceof Bomb) {
            buttons[index].setText("💣");
            buttons[index].setBackground(Color.RED);
            revealAll();
            JOptionPane.showMessageDialog(this, "💥 ¡Perdiste! Era una bomba.");
            disableAll();
            return;
        }

        buttons[index].setEnabled(false);
        revealedCount++;

        if (tile instanceof Clear) {
            buttons[index].setText("");
        } else if (tile instanceof Number numberTile) {
            String description = numberTile.toString();
            int count = Integer.parseInt(description.replaceAll("\\D+", ""));
            buttons[index].setText(String.valueOf(count));
        }

        checkWin();
    }

    private void toggleFlag(int index) {
        if (!buttons[index].isEnabled()) return;

        if (isFlagged[index]) {
            buttons[index].setText("");
            isFlagged[index] = false;
        } else {
            buttons[index].setText("F");
            isFlagged[index] = true;
        }
    }

    private void checkWin() {
        int nonBombs = 0;
        for (Tile t : game) {
            if (!(t instanceof Bomb)) nonBombs++;
        }

        if (revealedCount == nonBombs) {
            revealAll();
            JOptionPane.showMessageDialog(this, "🎉 ¡Felicidades! Has ganado.");
            disableAll();
        }
    }

    private void restartGame() {
        this.game = MineSweeper.generarJuego();
        this.revealedCount = 0;
        this.isFlagged = new boolean[64];

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }
    }

    private void disableAll() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    private void revealAll() {
        for (int i = 0; i < game.length; i++) {
            Tile tile = game[i];
            if (tile instanceof Bomb) {
                buttons[i].setText("B");
            } else if (tile instanceof Number numberTile) {
                String description = numberTile.toString();
                int count = Integer.parseInt(description.replaceAll("\\D+", ""));
                buttons[i].setText(String.valueOf(count));
            } else if (tile instanceof Clear) {
                buttons[i].setText("");
            }
            buttons[i].setEnabled(false);
        }
    }

    public static void main(String[] args) {
        Tile[] game = MineSweeper.generarJuego();
        SwingUtilities.invokeLater(() -> new MineSweeperGUI(game));
    }
}
