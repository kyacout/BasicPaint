package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JComponent;

import shape.Circle;
import shape.Ellipse;
import shape.Line;
import shape.Point;
import shape.Shape;
import shape.Square;
import shape.Triangle;
import undoRedo.History;
import undoRedo.State;

@SuppressWarnings("serial")
public class DrawPad extends JComponent {
	Image image;
	Graphics2D graphics2D;
	Color currColor = Color.BLACK;
	ArrayList<Shape> shapes = new ArrayList<>();
	String currShape = "Pointer";
	Shape selectedShape;
	int currentX, currentY, oldX, oldY, height, width, length, left, top,
			redius, pointCounter;
	int[] Xs = new int[3];
	int[] Ys = new int[3];
	int[] XsL = new int[2];
	int[] YsL = new int[2];

	public DrawPad() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			// if the mouse is pressed it sets the oldX & oldY
			// coordinates as the mouses x & y coordinates
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
				if (currShape == "Triangle") {
					if (pointCounter == 3) {
						pointCounter = 0;
						Xs = new int[3];
						Ys = new int[3];
					}
					Xs[pointCounter] = oldX;
					Ys[pointCounter++] = oldY;
					if (pointCounter == 3)
						drawNewTri();
				} else if (currShape == "Line") {
					if (pointCounter == 2) {
						pointCounter = 0;
						XsL = new int[2];
						YsL = new int[2];
					}
					XsL[pointCounter] = oldX;
					YsL[pointCounter++] = oldY;
					if (pointCounter == 2)
						drawNewLine();
				} else if (currShape == "Pointer") {
					selectShape(e.getX(), e.getY());
				}
			}

			public void mouseReleased(MouseEvent evt) {
				drawNewShape();
			}
		});
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

				if (currShape == "Square" || currShape == "Rectangle") {
					if (currShape == "Square") {
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
				} else if (currShape == "Ellipse" || currShape == "Circle") {
					left = oldX;
					top = oldY;
					if (currShape == "Circle") {
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
		case "Rectangle":
			drawNewRectangle();
			break;

		case "Square":
			drawNewSquare();
			break;

		case "Ellipse":
			drawNewEllipse();
			break;

		case "Circle":
			drawNewCircle();
			break;

		default:
			break;
		}
		if (currShape != "Triangle" && currShape != "Line") {
			History.addState(null, selectedShape);
			shapes.add(selectedShape);
			drawAll();
		}
	}

	public void drawNewRectangle() {
		shape.Rectangle rect = new shape.Rectangle(currColor, new int[] { oldX,
				oldX + width, oldX + width, oldX }, new int[] { oldY, oldY,
				oldY + height, oldY + height });
		selectedShape = rect;
	}

	public void drawNewSquare() {
		Square newSquare = new Square(currColor, new int[] { oldX,
				oldX + width, oldX + width, oldX }, new int[] { oldY, oldY,
				oldY + height, oldY + height });
		selectedShape = newSquare;
	}

	public void drawNewEllipse() {
		Ellipse newEllipse = new Ellipse(new Point(left, top), currColor,
				Math.abs(width), Math.abs(height));
		selectedShape = newEllipse;
	}

	public void drawNewCircle() {
		Circle newCircle = new Circle(new Point(left, top), currColor, redius);
		selectedShape = newCircle;
	}

	public void drawNewTri() {
		Triangle newTriangle = new Triangle(currColor, Xs, Ys);
		selectedShape = newTriangle;
		History.addState(null, selectedShape);
		shapes.add(selectedShape);
		drawAll();
	}

	public void drawNewLine() {
		Line newLine = new Line(currColor, XsL, YsL);
		selectedShape = newLine;
		History.addState(null, selectedShape);
		shapes.add(selectedShape);
		drawAll();
	}

	public void selectShape(int x, int y) {
		for (int i = shapes.size() - 1; i >= 0; i--) {
			if (shapes.get(i).contains(x, y)) {
				selectedShape = shapes.get(i);
			}
		}

	}

	public void drawAll() {
		clearCurr();

		for (Shape currShape : shapes) {
			if (currShape != null)
				currShape.draw(graphics2D);
		}

		repaint();
	}

	public void delete() {
		if (selectedShape != null) {
			State temp = History.Undo();

			int i = 0;
			while (shapes.get(i) != temp.getCurrState())
				i++;
			shapes.set(i, temp.getPrevState());
			drawAll();
		}
	}

	public void move() {
		State temp = History.Redo();

		int i = 0;
		while (shapes.get(i) != temp.getCurrState())
			i++;
		shapes.set(i, temp.getPrevState());
		drawAll();
	}
}
