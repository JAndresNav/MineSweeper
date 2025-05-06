import javax.swing.*; //
import java.awt.*;
import java.awt.event.*;
import Casillas.*;
import Casillas.Number;


public class GameInterface extends JFrame {

    //Arreglos para los bonotes, las casillas del juego, las bandera y el conteo de casillas reveladas
    private JButton[] buttons = new JButton[64]; //Botones que representan las casillas
    private Casillas[] game;//Representacion de las casillas en el juego
    private boolean[] isFlagged = new boolean[64]; //Arreglo para saber si una casilla esta marcada como bandera
    private int revealedCount = 0; //Conrtador de casillas reveladas

    //Inicializa la interfaz
    public GameInterface(Casillas[] initialGame) {
        this.game = initialGame; //Inicializamos el juego con las casillas dadas

        //Configuramos la ventana del juego
        setTitle("Buscaminas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cerrar del juego al salir
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(8, 8)); //Organiza las casillas 8x8
        buttons = new JButton[64]; //Inicializa el juego de botones
        isFlagged = new boolean[64]; //Inicializa el arreglo de banderas
        revealedCount = 0; //Reiniciamos el contador de casillas reveladas

        //Configuramos cada botón
        for (int i = 0; i < buttons.length; i++) {
            final int index = i; //Casilla actual
            buttons[i] = new JButton(); //Se crea un boton para cada casilla
            buttons[i].setFont(new Font("Arial", Font.BOLD, 16)); //Estilo de la fuente
            buttons[i].setFocusable(false); //Desactivamos el enfoque del botón

            //Manejar el clic de los botones
            buttons[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //Si se hace clic con el izquierdo
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (!isFlagged[index]) {
                            revealTile(index); //Revelamos casilla
                        }
                    } else if (SwingUtilities.isRightMouseButton(e)) {
                        toggleFlag(index); //Cambiamos a bandera
                    }
                }
            });

            gridPanel.add(buttons[i]); //Agregamos el botón al panel
        }

        //Panel para reiniciar el juego o salir
        JPanel controlPanel = new JPanel();
        JButton restartBtn = new JButton("Reiniciar");
        JButton exitBtn = new JButton("Salir");

        //Botones reiniciar o salir
        restartBtn.addActionListener(e -> restartGame());
        exitBtn.addActionListener(e -> System.exit(0));

        controlPanel.add(restartBtn); //Agregamos los botones al panel de control
        controlPanel.add(exitBtn);

        //Agregamos los paneles al tablero
        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        //Configuramos el tamaño y ubicacionn de la ventana
        setSize(500, 550);
        setLocationRelativeTo(null); //Centra la ventana en la pantalla
        setVisible(true); //Se hace visible la ventana
    }

    //Metodo que revela una casilla al hacer clic sobre ella
    private void revealTile(int index) {
        Casillas casillas = game[index]; //Obtenemos la casilla del juego correspondiente

        if (!buttons[index].isEnabled() || isFlagged[index]) return; //Si a esta revelada o tiene una bandera no pasa nada

        if (casillas instanceof Bomb) { //Si la casilla es una bomba
            buttons[index].setText("💣"); //Mostramos un icono de bomba
            buttons[index].setBackground(Color.RED); //Fondo rojo
            revealAll(); //Se revelan todas las casillas
            JOptionPane.showMessageDialog(this, "💥 ¡Perdiste! Era una bomba.");
            disableAll(); //Desactivamos todos los botones
            return;
        }

        buttons[index].setEnabled(false); //Deshabilitamos el botón para evitar más interacciones
        revealedCount++; //Aumentamos el contador de la casilla que fue revelada

        // Mostrar un numero
        if (casillas instanceof Clear) {
            buttons[index].setText("");
        } else if (casillas instanceof Number numberTile) {
            String description = numberTile.toString();
            int count = Integer.parseInt(description.replaceAll("\\D+", "")); //Cambiamos el numero de la casilla
            buttons[index].setText(String.valueOf(count)); //Mostramos el numero
        }

        checkWin(); //Verificamos si gano el jugador
    }

    //Par habiliar y deshabilitar botones que utilizamos
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

    //Ver si el jugador gano y revelar las casillas
    private void checkWin() {
        int nonBombs = 0;
        for (Casillas t : game) {
            if (!(t instanceof Bomb)) nonBombs++;
        }

        if (revealedCount == nonBombs) {
            revealAll();
            JOptionPane.showMessageDialog(this, "🎉 ¡Felicidades! Has ganado.");
            disableAll();
        }
    }

    //Método para reiniciar el juego
    private void restartGame() {
        this.game = MineSweeper.GenerateGame();
        this.revealedCount = 0;
        this.isFlagged = new boolean[64];

        //Se reinicia la interfaz de los botones
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setBackground(null);
        }
    }

    //Cuando sel juego se termina se deshabilitan todos los controles
    private void disableAll() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }

    //Revelación de todas las casillas (el juego ha terminado)
    private void revealAll() {
        for (int i = 0; i < game.length; i++) {
            Casillas casillas = game[i];
            if (casillas instanceof Bomb) {
                buttons[i].setText("B"); //Si es una bomba mostramos B
            } else if (casillas instanceof Number numberTile) {
                String description = numberTile.toString();
                int count = Integer.parseInt(description.replaceAll("\\D+", "")); //Mostramos un numero si existe en la csilla
                buttons[i].setText(String.valueOf(count));
            } else if (casillas instanceof Clear) {
                buttons[i].setText("");
            }
            buttons[i].setEnabled(false);
        }
    }

    //Generamos un nuevo juego
    public static void main(String[] args) {
        Casillas[] game = MineSweeper.GenerateGame();
        SwingUtilities.invokeLater(() -> new GameInterface(game));
    }
}

