package shape;

import java.awt.Color;

public class Line extends Polygon implements Cloneable {
	
	public Line(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
