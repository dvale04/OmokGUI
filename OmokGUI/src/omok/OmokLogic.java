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

    public void placeStone(int x, int y) {
        if (boardPanel.placeStone(x, y, currentPlayer)) {
            List<Stone> updatedStones = new ArrayList<>(stonePanel.getStones());
            updatedStones.add(new Stone(x, y, boardPanel.getStoneDiameter(), 
                                        (currentPlayer == 'X') ? Color.WHITE : Color.BLACK));

            stonePanel.setStones(updatedStones);
            stonePanel.repaint();

            if (boardPanel.checkForWin(x, y, currentPlayer)) {
                showWin();
                resetGame();
            } else if (boardPanel.isFilled()) {
                showDraw();
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == 'O') ? 'X' : 'O';
        omokGUI.updateStatus("Player " + currentPlayer + "'s turn");
    }

    private void showWin() {
        JOptionPane.showMessageDialog(omokGUI.getPanel(), "Player " + currentPlayer + " wins!");
    }

    private void showDraw() {
        JOptionPane.showMessageDialog(omokGUI.getPanel(), "It's a draw!");
    }
}

