package shape;

import java.awt.Color;

public class Circle extends Ellipse implements Cloneable {
	public Circle(Point position, Color color, int radius) {
		this.color = color;
		this.width = radius;
		this.height = radius;
		boundRect = new Rectangle(position, width, height);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
