package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.InputMismatchException;

public class Polygon extends Shape {
	protected int[] Xs;
	protected int[] Ys;
	protected int n;
	
	public Polygon(int[] Xs, int[] Ys) {
		if (Xs.length != Ys.length)
			throw new InputMismatchException();
		
		n = Xs.length;
		this.Xs = Xs;
		this.Ys = Ys;
	}
	
	public Polygon(Color color, int[] Xs, int[] Ys) {
		this(Xs, Ys);
		int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			minX = Math.min(minX, Xs[i]);
			maxX = Math.max(maxX, Xs[i]);
			maxY = Math.max(maxY, Ys[i]);
			minY = Math.min(minY, Ys[i]);
		}
		
		boundRect = new Rectangle(new Point(minX, minY), maxX - minX, maxY - minY);
		this.color = color;
	}
	
	public int [] getXs(){
		return Xs;
	}
	
	public int [] getYs(){
		return Ys;
	}
	
	@Override
	public void Draw(Graphics2D g) {
		super.Draw(g);
		g.drawPolygon(Xs, Ys, n);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		int[] newXs = new int[n];
		int[] newYs = new int[n];
		
		for (int i = 0; i < n; i++) {
			newXs[i] = Xs[i];
			newYs[i] = Ys[i];
		}
		
		Polygon t = new Polygon(color, newXs, newYs);
		return t;
	}
}
