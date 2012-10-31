package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JComponent;

import shape.Shape;


@SuppressWarnings("serial")
public class DrawPad extends JComponent {
	Image image;
	Graphics2D graphics2D;
	Color currColor;
	
	public void paintComponent(Graphics g) {
		if (image == null) {
			image = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) image.getGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			clear();
		}

		g.drawImage(image, 0, 0, null);
	}
	
	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}
	
	
	public void changeColor(Color color){
		currColor = color;
	}
	
	
	public void drawShape(Shape shape){
		
	}
	
}
