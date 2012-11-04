package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Triangle extends Polygon implements Cloneable {
	public Triangle(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		
		if (getNumberOfPoints() != 3)
			throw new InputMismatchException();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
