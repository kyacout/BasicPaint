package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.InputMismatchException;

public abstract class Shape implements Cloneable {
	
	private BoundRectangle boundRect;
	private Color color;
	
	public Shape(Color color, BoundRectangle boundRect) {
		setColor(color);
		this.boundRect = boundRect;
	}
	
	public Shape(Color color, int[] Xs, int[] Ys) {
		if (Xs.length != Ys.length)
			throw new InputMismatchException();
		
		updateBoundRect(Xs, Ys);
		setColor(color);
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public BoundRectangle getBoundRect() {
		return boundRect;
	}
	
	protected void updateBoundRect(int[] Xs, int[] Ys) {
		int minX, maxX, minY, maxY;
		
		minX = Integer.MAX_VALUE;
		maxX = Integer.MIN_VALUE;
		minY = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		
		for (int i = 0; i < Xs.length; i++) {
			minX = Math.min(minX, Xs[i]);
			maxX = Math.max(maxX, Xs[i]);
			maxY = Math.max(maxY, Ys[i]);
			minY = Math.min(minY, Ys[i]);
		}
		
		boundRect = new BoundRectangle(new Point(minX, minY), maxX - minX, maxY - minY);
	}
	
	protected void updateBoundRect(Point pos, int width, int height) {
		boundRect = new BoundRectangle(pos, width, height);
	}
	
	public Point getPosition() {
		return new Point(boundRect.getPos());
	}
	
	public boolean contains(int x, int y) {
		if (getPosition().getX() > x || 
				getPosition().getX() + getBoundRect().getWidth() < x || 
				getPosition().getY() > y || 
				getPosition().getY() + getBoundRect().getHeight() < y)
			return false;
		return true;
	}
	
	public abstract void draw(Graphics2D g);
	
	public abstract void move(int x, int y);
	
	protected void setDrawingColor(Graphics2D g) {
		g.setColor(color);
	}
	
	public static Shape createShape(Shapes shape, Point position, Color color, int width, int height) {
		switch(shape) {
			case Circle:
				if (width != height)
					throw new InputMismatchException();
				return new Circle(position, color, width);
			case Ellipse:
				return new Ellipse(position, color, width, height);
			default:
				throw new InputMismatchException();
		}
	}
	
	public static Shape createShape(Shapes shape, Color color, int[] Xs, int[] Ys) {
		switch (shape) {
			case Line:
				return new Line(color, Xs, Ys);
			case Polygon:
				return new Polygon(color, Xs, Ys);
			case Rectangle:
				return new Rectangle(color, Xs ,Ys);
			case Square:
				return new Square(color, Xs, Ys);
			case Triangle:
				return new Triangle(color, Xs, Ys);
			default:
				throw new InputMismatchException();
		}
	}
	
	public static ShapeType shapeType(Shapes shape) {
		switch (shape) {
			case Circle:
				return ShapeType.Ellipse;
			case Ellipse:
				return ShapeType.Ellipse;
			case Line:
				return ShapeType.Polygon;
			case Polygon:
				return ShapeType.Polygon;
			case Rectangle:
				return ShapeType.Polygon;
			case Square:
				return ShapeType.Polygon;
			case Triangle:
				return ShapeType.Polygon;
			default:
				throw new InputMismatchException();
		}
	}
}
