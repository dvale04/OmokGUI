package omok;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OmokClickListener extends MouseAdapter {
    private BoardPanel boardPanel;
    private StonePanel stonePanel;
    private char currentPlayer = 'O';

    public OmokClickListener(BoardPanel boardPanel, StonePanel stonePanel) {
        super();
        this.boardPanel = boardPanel;
        this.stonePanel = stonePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.boardPanel != null) {
            int row = e.getY() / (boardPanel.getHeight() / BoardPanel.rows);
            int col = e.getX() / (boardPanel.getWidth() / BoardPanel.cols);

            System.out.println("Clicked at: Row=" + row + ", Col=" + col);

            if (row >= 0 && row < BoardPanel.rows && col >= 0 && col < BoardPanel.cols) {
                stonePanel.addStone(row, col, currentPlayer, BoardPanel.rows, BoardPanel.cols);
                stonePanel.setStones(stonePanel.getStones());

                System.out.println("Adding stone at: Row=" + row + ", Col=" + col +
                        ", X=" + stonePanel.getStones().get(0).getX() +
                        ", Y=" + stonePanel.getStones().get(0).getY() +
                        ", Diameter=" + stonePanel.getStones().get(0).getDiameter() +
                        ", Player=" + currentPlayer +
                        ", Color=" + ((currentPlayer == 'X') ? "WHITE" : "BLACK"));

                currentPlayer = (currentPlayer == 'O') ? 'X' : 'O'; // Toggle player's turn
            }
        } else {
            System.err.println("Error");
        }
    }
}

