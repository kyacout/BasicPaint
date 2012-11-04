package shape;

public class BoundRectangle {
	private Point position;
	private int width;
	private int height;
	
	/**
	 * This Constructor is used to make a bound rectangle, the rectangle created
	 * here is not drawable.
	 * @param position: The top left corner of the rectangle
	 * @param width
	 * @param height
	 */
	public BoundRectangle(Point position, int width, int height) {
		this.position = position;
		this.width = width;
		this.height = height;
	}
	
	public Point getPos() {
		return position;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
