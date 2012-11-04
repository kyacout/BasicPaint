package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape implements Cloneable {	
	public Ellipse(Point position, Color color, int width, int height) {
		super(color, new Rectangle(position, width, height));
	}
	
	public int getWidth() {
		return getBoundRect().getWidth();
	}
	
	public int getHeight() {
		return getBoundRect().getHeight();
	}
	
	@Override
	public void Draw(Graphics2D graphics2D) {
		setDrawingColor(graphics2D);
		graphics2D.drawOval(getBoundRect().getPosition().getX(), getBoundRect().getPosition().getY(), getWidth(), getHeight());
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Ellipse e = new Ellipse(new Point(getBoundRect().getPosition().getX(), getBoundRect().getPosition().getY()), 
				getColor(), getWidth(), getHeight());
		return e;
	}
}
