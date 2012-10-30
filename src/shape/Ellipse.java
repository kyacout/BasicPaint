package shape;

import java.awt.Color;
import java.awt.Graphics2D;

public class Ellipse extends Shape {
	protected int width;
	protected int height;
	
	public Ellipse() { }
	
	public Ellipse(Point position, Color color, int width, int height) {
		this.color = color;
		this.width = width;
		this.height = height;
		boundRect = new Rectangle(position, width, height);
	}
	
	@Override
	public void Draw(Graphics2D graphics2D) {
		super.Draw(graphics2D);
		graphics2D.drawOval(boundRect.getPos().getX(), boundRect.getPos().getY(), width, height);
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Ellipse e = new Ellipse(new Point(boundRect.getPos().getX(), boundRect.getPos().getY()), color, width, height);
		return e;
	}
}
