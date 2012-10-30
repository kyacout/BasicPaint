package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Polygon extends Shape {
	protected int[] Xs = new int[3];
	protected int[] Ys = new int[3];
	protected int n;
	
	public Polygon(Color color, int[] Xs, int[] Ys) {
		if (Xs.length != Ys.length)
			throw new InputMismatchException();
		
		n = Xs.length;
		this.color = color;
		Arrays.sort(Xs);
		Arrays.sort(Ys);
		boundRect = new Rectangle(new Point(Xs[0], Ys[0]), Xs[2] - Xs[0], Ys[2] - Ys[0]);
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
