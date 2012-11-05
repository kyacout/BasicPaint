package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Polygon extends Shape implements Cloneable { 
	private int[] Xs;
	private int[] Ys;
	
	public Polygon(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		this.Xs = Xs;
		this.Ys = Ys;
	}
	
	public int[] getXs() {
		return Xs;
	}
	
	public int[] getYs() {
		return Ys;
	}
	
	public int getNumberOfPoints() {
		return Xs.length;
	}
	
	@Override
	public void draw(Graphics2D g) {
		setDrawingColor(g);
		g.drawPolygon(Xs, Ys, getNumberOfPoints());
	}
	
	@Override
	public void move(int x, int y) {
		int dx = x - getPosition().getX();
		int dy = y - getPosition().getY();
		
		for (int i = 0; i < getNumberOfPoints(); i++) {
			Xs[i] += dx;
			Ys[i] += dy;
		}
		
		updateBoundRect(Xs, Ys);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		int[] newXs = new int[getNumberOfPoints()];
		int[] newYs = new int[getNumberOfPoints()];
		
		for (int i = 0; i < getNumberOfPoints(); i++) {
			newXs[i] = Xs[i];
			newYs[i] = Ys[i];
		}
		
		Polygon t = new Polygon(getColor(), newXs, newYs);
		return t;
	}
}
