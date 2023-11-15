package omok;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OmokClickListener extends MouseAdapter {
    private BoardPanel boardPanel;
    private StonePanel stonePanel;
    private char currentPlayer = 'O';
    private OmokLogic omokLogic;
    private boolean isMoveInProgress = false;

    public OmokClickListener(BoardPanel boardPanel, StonePanel stonePanel, OmokLogic omokLogic) {
        super();
        this.boardPanel = boardPanel;
        this.stonePanel = stonePanel;
        this.omokLogic = omokLogic;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!isMoveInProgress) {
            handleMouseClick(e);
        }
    }

    private void handleMouseClick(MouseEvent e) {
        if (boardPanel != null) {
            int row = e.getY() / (boardPanel.getHeight() / BoardPanel.rows);
            int col = e.getX() / (boardPanel.getWidth() / BoardPanel.cols);

            if (isValidMove(row, col)) {
                isMoveInProgress = true; // Set the flag to indicate a move is in progress
                performMove(row, col);
                isMoveInProgress = false; // Reset the flag after the move is processed
            } else {
                System.err.println("Invalid move at Row=" + row + ", Col=" + col);
            }
        } else {
            System.err.println("Error: BoardPanel is null");
        }
    }

    private boolean isValidMove(int row, int col) {
        // Add validation logic here if needed
        return row >= 0 && row < BoardPanel.rows && col >= 0 && col < BoardPanel.cols;
    }

    private void performMove(int row, int col) {
        stonePanel.addStone(row, col, currentPlayer, BoardPanel.rows, BoardPanel.cols);
        currentPlayer = (currentPlayer == 'O') ? 'X' : 'O'; // Toggle player's turn
        omokLogic.checkGameStatus(row, col);
    }
}
