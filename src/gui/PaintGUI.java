package gui;
import javax.swing.JFrame;




public class PaintGUI {
	public static void main(String[] args) {
		PaintWindow frame = new PaintWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
