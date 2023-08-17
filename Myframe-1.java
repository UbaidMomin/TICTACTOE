import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

class Myframe extends Frame {
    Button[][] buttons = new Button[3][3];
    boolean isXsTurn = true;
    boolean gameOver = false;
    int xWins = 0;
    int oWins = 0;

    public Myframe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        //setBackground(Color.RED);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 36));
                add(buttons[i][j]);
                final int row = i;
                final int col = j;

                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!gameOver) {
                            Button clickedButton = (Button) e.getSource();
                            if (buttons[row][col] == clickedButton) {
                                if (buttons[row][col].getLabel().equals("")) {
                                    String symbol = isXsTurn ? "X" : "O";
                                    buttons[row][col].setLabel(symbol);
                                    if(isXsTurn)
                                    	buttons[row][col].setBackground(Color.RED);
                                    else
                                    	buttons[row][col].setBackground(Color.BLUE);
                                    isXsTurn = !isXsTurn;

                                    if (checkWinner(symbol)) {
                                        gameOver = true;
                                        if (symbol.equals("X")) {
                                            xWins++;
                                        } else {
                                            oWins++;
                                        }
                                        showWinnerDialog(symbol);
                                    } else if (checkDraw()) {
                                        gameOver = true;
                                        showDrawDialog();
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }

        setLocationRelativeTo(null);
        setVisible(true);
        setBackground(Color.GREEN);
        addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        displayWinCounts();
		        System.exit(0);
		    }
		});

    }

    private boolean checkWinner(String symbol) {
        for (int i = 0; i < 3; i++) {
            if (buttons[i][0].getLabel().equals(symbol) &&
                buttons[i][1].getLabel().equals(symbol) &&
                buttons[i][2].getLabel().equals(symbol)) {
                return true;
            }

            if (buttons[0][i].getLabel().equals(symbol) &&
                buttons[1][i].getLabel().equals(symbol) &&
                buttons[2][i].getLabel().equals(symbol)) {
                return true;
            }
        }

        if (buttons[0][0].getLabel().equals(symbol) &&
            buttons[1][1].getLabel().equals(symbol) &&
            buttons[2][2].getLabel().equals(symbol)) {
            return true;
        }

        if (buttons[0][2].getLabel().equals(symbol) &&
            buttons[1][1].getLabel().equals(symbol) &&
            buttons[2][0].getLabel().equals(symbol)) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinnerDialog(String winner) {
        String message = "Player " + winner + " wins!\nDo you want to play again?";
        int choice = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
			displayWinCounts();
            System.exit(0);
        }
    }

    private void showDrawDialog() {
        String message = "It's a draw!\nDo you want to play again?";
        int choice = JOptionPane.showConfirmDialog(this, message, "Game Over", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            displayWinCounts();
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setLabel("");
            }
        }
        isXsTurn = true;
        gameOver = false;
    }

    private void displayWinCounts() {
        System.out.println("X wins: " + xWins);
        System.out.println("O wins: " + oWins);
    }

    public static void main(String[] args) {
       Myframe f = new Myframe();
	   //f.setBackground(Color.GREEN);
    }
}
