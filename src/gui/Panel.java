package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import shape.Shapes;
import undoRedo.History;


@SuppressWarnings("serial")
public class Panel extends JPanel {	
	public Panel(final DrawPad padDraw) {
		ImageIcon rectIcon = new ImageIcon("rect.jpg");
		ImageIcon sqIcon = new ImageIcon("square.jpg");
		ImageIcon ellIcon = new ImageIcon("ellipse.jpg");
		ImageIcon circleIcon = new ImageIcon("circle.jpg");
		ImageIcon triangleIcon = new ImageIcon("triangle.jpg");
		ImageIcon lineIcon = new ImageIcon("line.jpg");
		ImageIcon pointerIcon = new ImageIcon("pointer.jpg");
		
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
		makeShapeButton(pointerIcon, padDraw, Shapes.Pointer);
		makeShapeButton(rectIcon, padDraw, Shapes.Rectangle);
		makeShapeButton(sqIcon, padDraw, Shapes.Square);
		makeShapeButton(ellIcon, padDraw, Shapes.Ellipse);
		makeShapeButton(circleIcon, padDraw, Shapes.Circle);
		makeShapeButton(triangleIcon, padDraw, Shapes.Triangle);
		makeShapeButton(lineIcon, padDraw, Shapes.Line);
		
		JTextField classLocationTxt = new JTextField();
		JButton loadButton = new JButton("load");
		
		JButton clearButton = new JButton("Clear");
		
		JButton undoButton = new JButton("Undo");
		undoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				padDraw.changeState(History.Undo());
			}
		});
		
		JButton redoButton = new JButton("Redo");
		redoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				padDraw.changeState(History.Redo());
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.clearAll();
			}
		});
		
		JButton copyButton = new JButton("Copy");
		copyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.clearAll();
			}
		});
		
		JButton moveButton = new JButton("Move");
		moveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.move();
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				padDraw.delete();
			}
		});
		
		this.add(copyButton);
		this.add(moveButton);
		this.add(deleteButton);
		this.add(clearButton);
		this.add(undoButton);
		this.add(redoButton);
		this.add(classLocationTxt);
		this.add(loadButton);
	}
	
	public void makeShapeButton(ImageIcon icon, final DrawPad padDraw, final Shapes st) {
		JButton shapeButton = new JButton(icon);
		this.add(shapeButton);
		shapeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				padDraw.setCurrShape(st);
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
