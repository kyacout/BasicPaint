package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Rectangle extends Shape implements Cloneable {
	protected int width;
	protected int height;
	protected Point position;
	
	
	public Rectangle() { }
	
	public Rectangle(Point position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Rectangle(Point position, Color color, int width, int height) {
		this.color = color;
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Point getPos() {
		return position;
	}
	
	@Override
	public void Draw(Graphics2D graphics2D) {
		super.Draw(graphics2D);
		graphics2D.drawRect(position.getX(), position.getY(), width, height);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Rectangle t = new Rectangle(new Point(position.getX(), position.getY()), color, width, height);
		return t;
	}
}
