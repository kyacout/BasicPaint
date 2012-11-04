package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Rectangle extends Polygon implements Cloneable {
	public Rectangle(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		if (!isRectangle(getXs(), getYs(), getNumberOfPoints()))
			throw new InputMismatchException();
	}
	
	public int getWidth() {
		return getBoundRect().getWidth();
	}
	
	public int getHeight() {
		return getBoundRect().getHeight();
	}
	
	public static boolean isRectangle(int[] Xs, int[] Ys, int n) {
		if ((n != 4) || (Xs[0] != Xs[3]) || (Xs[1] != Xs[2]) || 
				(Ys[0] != Ys[1]) || (Ys[2] != Ys[3]))
			return false;
		return true;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
