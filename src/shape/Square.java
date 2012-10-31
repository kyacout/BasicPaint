package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Square extends Rectangle implements Cloneable {
	public Square(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		
		if (width != height)
			throw new InputMismatchException();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
