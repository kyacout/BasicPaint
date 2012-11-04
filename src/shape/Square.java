package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Square extends Rectangle implements Cloneable {
	public Square(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		
		if (!isSquare(getWidth(), getHeight()))
			throw new InputMismatchException();
	}
	
	public static boolean isSquare(int width, int height) {
		if (width != height)
			return false;
		return true;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
