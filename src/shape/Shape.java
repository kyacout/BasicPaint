package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape implements Cloneable {
	
	public static enum shapes {
		Circle,
		Ellipse,
		Line,
		Point,
		Polygon,
		Rectangle,
		Square,
		Triangle;
	}
	
	protected Rectangle boundRect;
	protected Color color;
	
	public void Draw(Graphics2D graphics2D) {
		graphics2D.setPaint(color);
	}
}
