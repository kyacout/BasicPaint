package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Line extends Polygon implements Cloneable {
	
	public Line(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		
		if (n != 2)
			throw new InputMismatchException();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
