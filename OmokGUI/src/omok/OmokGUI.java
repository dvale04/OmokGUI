package omok;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

public class OmokGUI {
	 private JComboBox<String> opponentComboBox;
	 private StonePanel stonePanel;
	 private BoardPanel boardPanel;
	 private JPanel panel = new JPanel();
	 private JMenuBar menuBar;
	 private OmokClickListener omokClickListener;
	 private OmokLogic omokLogic;
	
	 
    public OmokGUI(BoardPanel boardPanel) {
    	 this.boardPanel = boardPanel;
    	
    	
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 400));
        panel.add(new JLabel("Welcome!"), BorderLayout.NORTH);
       
        stonePanel = new StonePanel();

        
        menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");
        gameMenu.setMnemonic(KeyEvent.VK_G);
		menuBar.add(gameMenu);

        
        JMenuItem playItem = new JMenuItem("Play", KeyEvent.VK_P);
        playItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
        		InputEvent.ALT_DOWN_MASK));
        playItem.getAccessibleContext().setAccessibleDescription(
        "Play a new game");
        playItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String selectedOpponent = (String) opponentComboBox.getSelectedItem();
        		JOptionPane.showMessageDialog(panel, "Playing against " + selectedOpponent);
        		 int choice = JOptionPane.showConfirmDialog(panel, "Start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
	                if (choice == JOptionPane.YES_OPTION) {
	                    boardPanel.resetBoard();
	                    boardPanel.repaint(); 
	                }
        }
        });
        
        JMenuItem aboutItem = new JMenuItem("About");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        gameMenu.add(playItem);
        gameMenu.add(aboutItem);
        gameMenu.addSeparator();
        gameMenu.add(exitItem);
        
       
        
        String[] opponents = { "Human", 
				   "Computer", 
				   "Strategy",
				   "P2P"};
        opponentComboBox = new JComboBox<>(opponents);
        panel.add(opponentComboBox, BorderLayout.NORTH);

        
        JToolBar toolBar = new JToolBar();
        JButton playToolbarButton = new JButton("Play");

        playToolbarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOpponent = (String) opponentComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(panel, "Opponent: " + selectedOpponent);
                int choice = JOptionPane.showConfirmDialog(panel, "Start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    boardPanel.resetBoard();
            }
        }
        });
       
        toolBar.add(playToolbarButton);
        toolBar.add(new JLabel("  Opponent: "));
        toolBar.add(opponentComboBox);
 
        panel.add(toolBar, BorderLayout.NORTH);
    
     
        this.omokLogic = new OmokLogic(boardPanel, stonePanel, this);
        this.omokClickListener = new OmokClickListener(boardPanel, stonePanel, this.omokLogic);

        boardPanel.addMouseListener(omokClickListener);
        
        panel.add(boardPanel, BorderLayout.CENTER);
      
   
        
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Place 5 stones in a row to win.");
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        int dialogOption = JOptionPane.showConfirmDialog
        		  (panel, "Are you sure you want to exit?",
        		    "Exit Game", JOptionPane.YES_NO_OPTION);
        		if (dialogOption == JOptionPane.YES_OPTION)
        		{
        			SwingUtilities.getWindowAncestor(panel).dispose();
        		}
        	}
        });
    }
    public void showWinMessage(char winner) {
    JOptionPane.showMessageDialog(panel, "Player " + winner + " wins!");
    int choice = JOptionPane.showConfirmDialog(panel, "Start a new game?", "New Game", JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION) {
        boardPanel.resetBoard();
        stonePanel.setStones(new ArrayList<>()); // Clear stones in StonePanel
        stonePanel.repaint();
        omokLogic.resetGame(); // Reset the game logic
    }
}

    public void showDrawMessage() {
        JOptionPane.showMessageDialog(panel, "It's a draw!");
    }
     
    public JPanel getPanel() {
        return panel;
    }
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new OmokGUI(new BoardPanel()));
    }

	public void updateStatus(String string) {
		// TODO Auto-generated method stub
		
	}
}
