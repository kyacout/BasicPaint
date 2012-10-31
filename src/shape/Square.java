package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Square extends Rectangle implements Cloneable {
	public Square(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		
		int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
		for (int i = 0; i < n; i++) {
			minX = Math.min(minX, Xs[i]);
			maxX = Math.max(maxX, Xs[i]);
			maxY = Math.max(maxY, Ys[i]);
			minY = Math.min(minY, Ys[i]);
		}
		
		if (maxX - minX != maxY - minY)
			throw new InputMismatchException();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
