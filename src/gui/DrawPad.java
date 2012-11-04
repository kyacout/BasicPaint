package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.LinkedList;

import javax.swing.JComponent;

import shape.Shape;
import shape.Square;


@SuppressWarnings("serial")
public class DrawPad extends JComponent {
	Image image;
	Graphics2D graphics2D;
	Color currColor;
	LinkedList<Shape> shapes = new LinkedList<>();
	String currShape;
	int currentX, currentY, oldX, oldY, height, width, length;
	
	public DrawPad() {
		setDoubleBuffered(false);
		addMouseListener(new MouseAdapter() {
			// if the mouse is pressed it sets the oldX & oldY
			// coordinates as the mouses x & y coordinates
			public void mousePressed(MouseEvent e) {
				oldX = e.getX();
				oldY = e.getY();
			}
			 public void mouseReleased(MouseEvent evt){
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
				
				if(currShape == "Square"){
					width = Math.min(width, height);
					height = Math.min(width, height);
				}
				
				int[] Xs = {oldX, oldX + width, oldX + width, oldX};
				int[] Ys = {oldY, oldY, oldY + height, oldY + height};
				
				graphics2D.drawPolygon(Xs, Ys, Xs.length);
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
		shapes = new LinkedList<>();
		repaint();
	}
	
	public void clearCurr(){
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(currColor);
		repaint();
	}
	
	
	public void changeColor(Color color){
		currColor = color;
	}
	
	
	public void drawNewShape(){
		switch (currShape) {
		case "Rectangle":
			drawNewRectangle();
			break;
			
		case "Square":
			drawNewSquare();
			break;

		default:
			break;
		}
	}
	
	public void drawNewRectangle(){
		shape.Rectangle rect = new shape.Rectangle(currColor,new int[]{oldX, oldX + width, oldX + width, oldX},
				new int[]{oldY, oldY, oldY + height, oldY + height});
		shapes.add(rect);
		drawAll();
	}
	
	public void drawNewSquare(){
		length = Math.min(width, height);
		Square newSquare = new Square(currColor,new int[]{oldX, oldX + length, oldX + length, oldX},
				new int[]{oldY, oldY, oldY + length, oldY + length});
		shapes.add(newSquare);
		drawAll();
	}
	
	public void drawAll(){
		clearCurr();
		
		for(Shape currShape : shapes){
			currShape.Draw(graphics2D);
		}
		
		repaint();
	}
}
