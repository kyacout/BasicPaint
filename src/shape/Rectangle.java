package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.InputMismatchException;

public class Rectangle extends Polygon implements Cloneable {
	protected int width;
	protected int height;
	protected Point position;
	
	
	public Rectangle(Point position, int width, int height) {
		super(new int[]{position.getX(), position.getX(), position.getX() + width, position.getX() + width},
				new int[]{position.getY(), position.getY(), position.getY() + height, position.getY() + height});
		
		position = new Point(Xs[0], Ys[0]);
		
		width = Xs[3] - Xs[0];
		height = Ys[3] - Ys[0];
	}
	
	public Rectangle(Color color, int[] Xs, int[] Ys) {
		super(color, Xs, Ys);
		if ((n != 4) || (Xs[0] != Xs[1]) || (Xs[2] != Xs[3]) || 
				(Ys[0] != Ys[1]) || (Ys[2] != Ys[3]))
			throw new InputMismatchException();
		
		position = new Point(Xs[0], Ys[0]);
		
		width = Xs[3] - Xs[0];
		height = Ys[3] - Ys[0];
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
		return super.clone();
	}
}
