package shape;

import java.awt.Color;

public class Triangle extends Polygon implements Cloneable {
	public Triangle(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
