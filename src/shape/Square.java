package shape;

import java.awt.Color;

public class Square extends Rectangle implements Cloneable {
	public Square(Point position, Color color, int length) {
		this.position = position;
		this.color = color;
		this.width = length;
		this.height = length;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
