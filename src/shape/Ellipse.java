package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape implements Cloneable {	
	public Ellipse(Point position, Color color, int width, int height) {
		super(color, new BoundRectangle(position, width, height));
	}
	
	public int getWidth() {
		return getBoundRect().getWidth();
	}
	
	public int getHeight() {
		return getBoundRect().getHeight();
	}
	
	@Override
	public void draw(Graphics2D graphics2D) {
		setDrawingColor(graphics2D);
		graphics2D.drawOval(getBoundRect().getPos().getX(), getBoundRect().getPos().getY(), getWidth(), getHeight());
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Ellipse e = new Ellipse(new Point(getBoundRect().getPos().getX(), getBoundRect().getPos().getY()), 
				getColor(), getWidth(), getHeight());
		return e;
	}
}
