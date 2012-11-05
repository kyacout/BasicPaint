package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

import shape.Circle;
import shape.Ellipse;
import shape.Line;
import shape.Point;
import shape.Rectangle;
import shape.Shape;
import shape.Shapes;
import shape.Square;
import shape.Triangle;
import undoRedo.History;
import undoRedo.State;

@SuppressWarnings("serial")
public class DrawPad extends JComponent {
	private Image image;
	private Image sqIcon = new ImageIcon(this.getClass().getResource("selected.jpg")).getImage();
	private Graphics2D graphics2D;
	private Color currColor = Color.BLACK;
	private ArrayList<Shape> shapes = new ArrayList<>();
	private Shapes currShape = Shapes.Pointer;
	private Shape selectedShape;
	private int currentX, currentY, oldX, oldY, height, width, left, top,
			redius, pointCounter;
	private int[] Xs = new int[3];
	private int[] Ys = new int[3];
	private int[] XsL = new int[2];
	private int[] YsL = new int[2];

	public DrawPad() {
		setFocusable(true);
		setDoubleBuffered(false);
		addKeyListener(new TAdapter());
		addMouseListener(new MAdapter());

		addMouseMotionListener(new MouseMotionAdapter() {
			// while the mouse is dragged it sets currentX & currentY as the
			// mouses x and y
			// then it draws a line at the coordinates
			// it repaints it and sets oldX and oldY as currentX and currentY
			public void mouseDragged(MouseEvent e) {
				drawAll();
				currentX = e.getX();
				currentY = e.getY();
				height = currentY - oldY;
				width = currentX - oldX;
				graphics2D.setColor(currColor);

				if (currShape == Shapes.Square || currShape == Shapes.Rectangle) {
					if (currShape == Shapes.Square) {
						int temp = width;
						width = Math.min(Math.abs(width), Math.abs(height));
						if (temp < 0)
							width *= -1;
						temp = height;
						height = Math.min(Math.abs(width), Math.abs(height));
						if (temp < 0)
							height *= -1;
					}
					graphics2D.drawPolygon(new int[] { oldX, oldX + width,
							oldX + width, oldX }, new int[] { oldY, oldY,
							oldY + height, oldY + height }, 4);
				} else if (currShape == Shapes.Ellipse || currShape == Shapes.Circle) {
					left = oldX;
					top = oldY;
					if (currShape == Shapes.Circle) {
						redius = Math.min(Math.abs(width), Math.abs(height));
						if (width < 0)
							left -= Math.abs(redius);
						if (height < 0)
							top -= Math.abs(redius);
						graphics2D.drawOval(left, top, redius, redius);
					} else {
						if (width < 0)
							left -= Math.abs(width);
						if (height < 0)
							top -= Math.abs(height);
						graphics2D.drawOval(left, top, Math.abs(width),
								Math.abs(height));
					}
				}
				repaint();
			}
		});

	}
	
    public Shapes getCurrShape() {
    	return currShape;
    }
    
    public void setCurrShape(Shapes shape) {
    	currShape = shape;
    }

	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			clearAll();
		}
		g.drawImage(image, 0, 0, null);
	}

	public void clearAll() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		shapes = new ArrayList<>();
		repaint();
	}

	public void clearCurr() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(currColor);
		repaint();
	}

	public void changeColor(Color color) {
		currColor = color;
	}

	public void drawNewShape() {
		switch (currShape) {
		case Rectangle:
			drawNewRectangle();
			break;

		case Square:
			drawNewSquare();
			break;

		case Ellipse:
			drawNewEllipse();
			break;

		case Circle:
			drawNewCircle();
			break;

		default:
			break;
		}
		if (currShape != Shapes.Triangle && currShape != Shapes.Line && currShape != Shapes.Pointer) {
			History.addState(null, selectedShape);
			shapes.add(selectedShape);
		}
		drawAll();
	}

	public void drawNewRectangle() {
		Shape rect = new Rectangle(currColor, new int[] { oldX,
				oldX + width, oldX + width, oldX }, new int[] { oldY, oldY,
				oldY + height, oldY + height });
		selectedShape = rect;
	}

	public void drawNewSquare() {
		Shape newSquare = new Square(currColor, new int[] { oldX,
				oldX + width, oldX + width, oldX }, new int[] { oldY, oldY,
				oldY + height, oldY + height });
		selectedShape = newSquare;
	}

	public void drawNewEllipse() {
		Shape newEllipse = new Ellipse(new Point(left, top), currColor,
				Math.abs(width), Math.abs(height));
		selectedShape = newEllipse;
	}

	public void drawNewCircle() {
		Shape newCircle = new Circle(new Point(left, top), currColor, redius);
		selectedShape = newCircle;
	}

	public void drawNewTri() {
		Shape newTriangle = new Triangle(currColor, Xs, Ys);
		selectedShape = newTriangle;
		History.addState(null, selectedShape);
		shapes.add(selectedShape);
		drawAll();
	}

	public void drawNewLine() {
		Shape newLine = new Line(currColor, XsL, YsL);
		selectedShape = newLine;
		History.addState(null, selectedShape);
		shapes.add(selectedShape);
		drawAll();
	}

	public void selectShape(int x, int y) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i) != null && shapes.get(i).contains(x, y)) {
				selectedShape = shapes.get(i);
				break;
			}
		}

	}

	public void drawAll() {
		clearCurr();
		
		if (selectedShape != null) {
			int x = selectedShape.getBoundRect().getPos().getX();
			int y = selectedShape.getBoundRect().getPos().getY();
			int width = selectedShape.getBoundRect().getWidth();
			int height = selectedShape.getBoundRect().getHeight();
			
			graphics2D.drawImage(sqIcon, x, y, 5, 5, Color.black, null);
			graphics2D.drawImage(sqIcon, x + width, y, 5, 5, Color.black, null);
			graphics2D.drawImage(sqIcon, x, y + height, 5, 5, Color.black, null);
			graphics2D.drawImage(sqIcon, x + width, y + height, 5, 5, Color.black, null);
		}
		
		for (Shape currShape : shapes) {
			if (currShape != null)
				currShape.draw(graphics2D);
		}

		repaint();
	}

	public void delete() {
		if (selectedShape != null) {
			History.addState(selectedShape, null);
			int i = 0;
			while (shapes.get(i) != selectedShape)
				i++;
			shapes.set(i, null);
			selectedShape = null;
			drawAll();
		}
	}

	public void move() {
		
	}

	public void changeState(State temp) {
		if (temp != null) {
			int i = 0;
			while (shapes.get(i) != temp.getCurrState())
				i++;
			shapes.set(i, temp.getPrevState());
			selectedShape = temp.getPrevState();
			drawAll();
		}
	}

	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			if ((e.getKeyCode() == KeyEvent.VK_Z)
					&& ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				changeState(History.Undo());
			} else if ((e.getKeyCode() == KeyEvent.VK_Y)
					&& ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
				changeState(History.Redo());
			}
		}
	}

	private class MAdapter extends MouseAdapter {
		// if the mouse is pressed it sets the oldX & oldY
		// coordinates as the mouses x & y coordinates
		public void mousePressed(MouseEvent e) {
			oldX = e.getX();
			oldY = e.getY();
			if (currShape == Shapes.Triangle) {
				if (pointCounter == 3) {
					pointCounter = 0;
					Xs = new int[3];
					Ys = new int[3];
				}
				Xs[pointCounter] = oldX;
				Ys[pointCounter++] = oldY;
				if (pointCounter == 3)
					drawNewTri();
			} else if (currShape == Shapes.Line) {
				if (pointCounter == 2) {
					pointCounter = 0;
					XsL = new int[2];
					YsL = new int[2];
				}
				XsL[pointCounter] = oldX;
				YsL[pointCounter++] = oldY;
				if (pointCounter == 2)
					drawNewLine();
			} else if (currShape == Shapes.Pointer) {
				selectShape(e.getX(), e.getY());
			}
		}

		public void mouseReleased(MouseEvent evt) {
			drawNewShape();
		}
	}
}
