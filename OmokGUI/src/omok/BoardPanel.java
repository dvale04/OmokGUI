package omok;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class BoardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    static final int rows = 16;
    static final int cols = 16;
    private char[][] board;
    private List<Stone> stones;
   

    public BoardPanel() {
        board = new char[rows][cols];
        stones = new ArrayList<>();
        resetBoard();

    }
   

    public void resetBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = ' ';
            }
        }
        stones.clear();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int boardWidth = getWidth();
        int boardHeight = getHeight();

        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, boardWidth, boardHeight);

        g.setColor(Color.BLACK);
        int rowHeight = boardHeight / rows;
        int colWidth = boardWidth / cols;
        for (int i = 0; i < rows; i++)
            g.drawLine(0, i * rowHeight, boardWidth, i * rowHeight);
        for (int j = 0; j < cols; j++)
            g.drawLine(j * colWidth, 0, j * colWidth, boardHeight);

        if (stones != null) {
            for (Stone stone : stones) {
                stone.draw(g);
            }
            }
        }



	public boolean placeStone(int x, int y, char currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}


	public boolean placeStone(int x, int y, char currentPlayer) {
		// TODO Auto-generated method stub
		return false;
	}
    }
