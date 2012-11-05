package shape;

import java.awt.Color;

public class Circle extends Ellipse implements Cloneable {
	
	public Circle(Point position, Color color, int radius) {
		super(position, color, radius, radius);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
