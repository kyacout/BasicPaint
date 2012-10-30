package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Shape implements Cloneable {
	protected Rectangle boundRect;
	protected Color color;
	
	public void Draw(Graphics2D graphics2D) {
		graphics2D.setPaint(color);
	}
}
