package omok;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public class OmokLogic {
    private BoardPanel boardPanel;
    private StonePanel stonePanel;
    private OmokGUI omokGUI;
    private char currentPlayer;

    public OmokLogic(BoardPanel boardPanel, StonePanel stonePanel, OmokGUI omokGUI) {
        this.boardPanel = boardPanel;
        this.stonePanel = stonePanel;
        this.omokGUI = omokGUI;
        this.currentPlayer = 'O'; // Starting player
    }

    public void resetGame() {
        boardPanel.resetBoard();
        stonePanel.setStones(new ArrayList<>()); // Clear stones in StonePanel
        stonePanel.repaint();
        currentPlayer = 'O'; // Reset to starting player
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
        omokGUI.updateStatus("Player " + currentPlayer + "'s turn");
    }


    void checkGameStatus(int x, int y) {
        if (checkForWin(x, y)) {
            resetGame();
        } else if (isBoardFilled()) {
            showDraw();
            resetGame();
        }
    }

    private boolean checkForWin(int x, int y) {
        if (checkHorizontal(x, y) || checkVertical(x, y) || checkDiagonal(x, y)) {
            // Display win message and reset game
            JOptionPane.showMessageDialog(omokGUI.getPanel(), "Player " + currentPlayer + " wins!");
            int choice = JOptionPane.showConfirmDialog(omokGUI.getPanel(), "Start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                boardPanel.resetBoard();
                stonePanel.setStones(new ArrayList<>());
                stonePanel.repaint();
                resetGame();
            }
            return true;
        }
        return false;
    }
    
    private boolean checkHorizontal(int x, int y) {
        int count = countStonesInDirection(x, y, 0, 1) + countStonesInDirection(x, y, 0, -1) + 1;
        return count >= 5;
    }

    private boolean checkVertical(int x, int y) {
        int count = countStonesInDirection(x, y, 1, 0) + countStonesInDirection(x, y, -1, 0) + 1;
        return count >= 5;
    }

    private boolean checkDiagonal(int x, int y) {
        int count1 = countStonesInDirection(x, y, 1, 1) + countStonesInDirection(x, y, -1, -1) + 1;
        int count2 = countStonesInDirection(x, y, 1, -1) + countStonesInDirection(x, y, -1, 1) + 1;
        return count1 >= 5 || count2 >= 5;
    }

    private int countStonesInDirection(int x, int y, int dx, int dy) {
        Color playerColor = stonePanel.getStones().stream()
                .filter(stone -> stone.getX() == x && stone.getY() == y)
                .findFirst()
                .map(Stone::getColor)
                .orElse(Color.BLACK);  // Provide a default color

        int count = 0;
        int currentX = x + dx;
        int currentY = y + dy;

        while (isValidPosition(currentX, currentY) &&
                stonePanel.getStones().stream()
                        .anyMatch(stone -> stone.getX() == x && stone.getY() == y &&
                                stone.getColor() == playerColor)) {
            count++;
            currentX += dx;
            currentY += dy;
        }

        return count;
    }


    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < BoardPanel.rows && y >= 0 && y < BoardPanel.cols;
    }

    private boolean isBoardFilled() {
        return stonePanel.getStones().size() == BoardPanel.rows * BoardPanel.cols;
    }

    private void showWin() {
    	 omokGUI.showWinMessage(currentPlayer);
    	 resetGame();
    }

    private void showDraw() {
         omokGUI.showDrawMessage();
        }
}


