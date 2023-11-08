package omok;

import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.*;

public class BoardPanel extends JPanel {
	public static void main(String[] args) {
	}
    private static final long serialVersionUID = 1L;
	private Board board;
    private boolean isBlack = true; 

    private JComboBox<String> opponentSelector;
    private JButton playButton;

    public BoardPanel(Board board) {
        this.board = board;

      
        String[] opponents = {"Human", "Computer"};
        opponentSelector = new JComboBox<>(opponents);

      
        playButton = new JButton("Play");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedOpponent = (String) opponentSelector.getSelectedItem();
                JOptionPane.showMessageDialog(null, "Selected opponent: " + selectedOpponent);
            }
        }
       );
        setLayout(new FlowLayout());
        add(opponentSelector);
        add(playButton);

        ClickListener listener = new ClickListener();
        this.addMouseListener(listener);
    }
    
    protected void paintComponent(Graphics g) {
    	
    }
    public class ClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            int width = getWidth() / board.getSize();
            int height = getHeight() / board.getSize();
            
            int x = e.getX() / width;
            int y = e.getY() / height;
            
            if (board.placeStone(x, y, isBlack ? 'B' : 'W')) {
                isBlack = !isBlack;
                repaint();
                if (board.checkForWin(x, y, isBlack ? 'B' : 'W')) {
                    JOptionPane.showMessageDialog(null, "Player " + (isBlack ? "Black" : "White") + " is winner.");
                    board.reset();
                }
            }
        }
    }





