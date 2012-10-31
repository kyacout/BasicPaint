package shape;

import java.awt.Color;
import java.util.InputMismatchException;

public class Rectangle extends Polygon implements Cloneable {
	public Rectangle(Point position, int width, int height) {
		super(new int[]{position.getX(), position.getX() + width, position.getX() + width, position.getX()},
				new int[]{position.getY(), position.getY(), position.getY() + height, position.getY() + height});
		
		if ((n != 4) || (Xs[0] != Xs[3]) || (Xs[1] != Xs[2]) || 
				(Ys[0] != Ys[1]) || (Ys[2] != Ys[3]))
			throw new InputMismatchException();
	}
	
	public Rectangle(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		if ((n != 4) || (Xs[0] != Xs[3]) || (Xs[1] != Xs[2]) || 
				(Ys[0] != Ys[1]) || (Ys[2] != Ys[3]))
			throw new InputMismatchException();
	}
	
	public Point getPos() {
		return new Point(Xs[0], Ys[0]);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
