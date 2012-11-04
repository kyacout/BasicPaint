package gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class PaintWindow extends JFrame {

	public PaintWindow() {
		DrawPad padDraw = new DrawPad();
		Panel panel = new Panel(padDraw);
		setTitle("Basic Paint");
		setSize(1300, 720);
		panel.setPreferredSize(new Dimension(100, 150));
		Container content = this.getContentPane();
		content.setLayout(new BorderLayout());
		content.add(panel, BorderLayout.WEST);
		content.add(padDraw, BorderLayout.CENTER);
	}
}
