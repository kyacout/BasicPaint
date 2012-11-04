package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.InputMismatchException;

public abstract class Shape implements Cloneable {
	
	private BoundRectangle boundRect;
	private Color color;
	
	public Shape(Color color, BoundRectangle boundRect) {
		setColor(color);
		this.boundRect = boundRect;
	}
	
	public Shape(Color color, int[] Xs, int[] Ys) {
		if (Xs.length != Ys.length)
			throw new InputMismatchException();
		
		int minX, maxX, minY, maxY;
		
		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		
		for (int i = 0; i < Xs.length; i++) {
			minX = Math.min(minX, Xs[i]);
			maxX = Math.max(maxX, Xs[i]);
			maxY = Math.max(maxY, Ys[i]);
			minY = Math.min(minY, Ys[i]);
		}
		
		boundRect = new BoundRectangle(new Point(minX, maxX), maxX - minX, maxY - minY);
		setColor(color);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public BoundRectangle getBoundRect() {
		return boundRect;
	}
	
	public Point getPosition() {
		return new Point(boundRect.getPos());
	}
	
	public abstract void Draw(Graphics2D g);
	
	protected void setDrawingColor(Graphics2D g) {
		g.setColor(color);
	}
}
