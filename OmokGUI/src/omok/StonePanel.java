package omok;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class StonePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private List<Stone> stones = new LinkedList<Stone>();
	
	public StonePanel() {
        this.setPreferredSize(new Dimension(400, 400)); // Adjust the size as needed
    }
	
	public void addStone(int row, int col, char player, int rows, int cols) {
	    if (row >= 0 && row < rows && col >= 0 && col < cols) {
	        int rowHeight = getHeight() / rows;
	        int colWidth = getWidth() / cols;
	        int diameter = (int) (Math.min(rowHeight, colWidth) * 0.8); 

	      
	        int x = col * colWidth + (colWidth - diameter) / 2;
	        int y = row * rowHeight + (rowHeight - diameter) / 2;

	        System.out.println("Adding stone at: Row=" + row + ", Col=" + col +
	                ", X=" + x + ", Y=" + y + ", Diameter=" + diameter + ", Player=" + player);

	        Stone newStone = new Stone(x, y, diameter, (player == 'X') ? Color.WHITE : Color.BLACK);
	        stones.add(newStone);
	        repaint();

	        System.out.println("Stones in the list: " + stones.size());
	        for (Stone stone : stones) {
	            System.out.println("Stone in the list: X=" + stone.getX() + ", Y=" + stone.getY() +
	                    ", Diameter=" + stone.getDiameter());
	            System.out.println("Adding stone at: Row=" + row + ", Col=" + col +
	                    ", X=" + x + ", Y=" + y + ", Diameter=" + diameter + ", Player=" + player +
	                    ", Color=" + ((player == 'X') ? "WHITE" : "BLACK"));
	        }

	    }
	}

        public List<Stone> getStones() {
            return stones;
        }
        public void setStones(List<Stone> stones) {
            this.stones = stones;
            repaint();
        }
    
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Stone s : stones) {
			s.draw(g);
		}	
	
		
	
		
	}
}