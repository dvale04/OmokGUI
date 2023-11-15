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
    public static final long serialVersionUID = 1L;
    public static final int rows = 16;
    public static final int cols = 16;
    private char[][] board;
    private List<Stone> stones;
    private boolean isWhiteTurn = true;

    public BoardPanel() {
        board = new char[rows][cols];
        stones = new ArrayList<>();
        resetBoard();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClick(e);
            }
        });
    }

    private void handleMouseClick(MouseEvent e) {
        int row = e.getY() / (getHeight() / rows);
        int col = e.getX() / (getWidth() / cols);

        // Check if the click is within the board boundaries
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            char currentPlayer = isWhiteTurn ? 'O' : 'X';
            addStone(row, col, currentPlayer);
            isWhiteTurn = !isWhiteTurn;
        }
    }

    public void addStone(int row, int col, char player) {
        if (board[row][col] == ' ') {
            board[row][col] = player;
            int rowHeight = getHeight() / rows;
            int colWidth = getWidth() / cols;
            int x = col * colWidth;
            int y = row * rowHeight;

            stones.add(new Stone(x, y, colWidth, (player == 'X') ? Color.WHITE : Color.BLACK));
            repaint();
        }
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
        Dimension d = getSize();
        int boardWidth = d.width;
        int boardHeight = d.height;

        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, boardWidth, boardHeight);

        g.setColor(Color.BLACK);
        int rowHeight = boardHeight / rows;
        int colWidth = boardWidth / cols;
        for (int i = 0; i < rows; i++)
            g.drawLine(0, i * rowHeight, boardWidth, i * rowHeight);
        for (int j = 0; j < cols; j++)
            g.drawLine(j * colWidth, 0, j * colWidth, boardHeight);

        for (Stone stone : stones) {
            stone.draw(g);
        }
    }


	}

