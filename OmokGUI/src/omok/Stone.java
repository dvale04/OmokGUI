package omok;
import java.awt.Color;
import java.awt.Graphics;

public class Stone {

	private int x; 
	private int y; 
	private int diameter; 
	private Color color;
	
	public Stone(int x, int y, int diameter, Color color) {
	
		this.x = x;
		this.y = y;
		this.diameter = diameter;
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDiameter() {
		return diameter;
	}

	public Color getColor() {
		return color;
	} 
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}
	
}
