package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
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
		
		Arrays.sort(Xs);
		Arrays.sort(Ys);
		boundRect = new Rectangle(new Point(Xs[0], Ys[0]), Xs[2] - Xs[0], Ys[2] - Ys[0]);
	}
	
	public Polygon(Color color, int[] Xs, int[] Ys) {
		this(Xs, Ys);
		this.color = color;
	}
	
	@Override
	public void Draw(Graphics2D graphics2D) {
		super.Draw(graphics2D);
		graphics2D.drawPolygon(Xs, Ys, n);
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
