package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import shape.Shape;

@SuppressWarnings("serial")
public class Panel extends JPanel {
	ImageIcon rectIcon = new ImageIcon("rect.jpg");
	ImageIcon sqIcon = new ImageIcon("square.jpg");
	public Panel(final DrawPad padDraw) {
		makeColorButton(Color.BLUE, padDraw);
		makeColorButton(Color.MAGENTA, padDraw);
		makeColorButton(Color.RED, padDraw);
		makeColorButton(Color.GREEN, padDraw);
		makeColorButton(Color.BLACK, padDraw);
		makeColorButton(Color.YELLOW, padDraw);
		makeColorButton(Color.GRAY, padDraw);
		makeColorButton(Color.ORANGE, padDraw);
		makeColorButton(Color.WHITE, padDraw);
		makeColorButton(Color.PINK, padDraw);
		makeColorButton(Color.CYAN, padDraw);
		makeColorButton(Color.DARK_GRAY, padDraw);
		makeShapeButton(rectIcon, padDraw, "Rectangle");
		makeShapeButton(sqIcon, padDraw, "Square");
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.clearAll();
			}
		});
		this.add(clearButton);
	}
	
	public void makeShapeButton(ImageIcon icon, final DrawPad padDraw, final String st){
		JButton shapeButton = new JButton(icon);
		this.add(shapeButton);
		shapeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				padDraw.currShape = st;
			}
		});
	}

	public void makeColorButton(final Color color, final DrawPad padDraw) {
		JButton tempButton = new JButton();
		tempButton.setBackground(color);
		tempButton.setPreferredSize(new Dimension(16, 16));
		this.add(tempButton);
		tempButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.changeColor(color);
			}
		});
	}
	
}
