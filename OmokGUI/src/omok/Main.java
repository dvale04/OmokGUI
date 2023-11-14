package omok;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	
	  public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	        	BoardPanel boardPanel = new BoardPanel(); 
	            OmokGUI omokGUI = new OmokGUI(boardPanel);
	            
	            //main window
	            JFrame frame = new JFrame("Omok");

	            StonePanel stonePanel = new StonePanel();
	            frame.setContentPane(stonePanel);
	            
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.setJMenuBar(omokGUI.getMenuBar()); 
	            frame.getContentPane().add(omokGUI.getPanel());
	         
	            frame.pack();
	            frame.setVisible(true);
	             
	  
	        });
	    }
	}