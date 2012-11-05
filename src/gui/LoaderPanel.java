package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import loader.ShapeLoader;

@SuppressWarnings("serial")
public class LoaderPanel extends JPanel {
	public LoaderPanel() {
		final JTextField txt_Location = new JTextField("Add path here:");
		
		JButton but_Load = new JButton("Load");
		but_Load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ShapeLoader loader = new ShapeLoader(txt_Location.getText());
				loader.load();
			}
		});
		
		add(txt_Location);
		add(but_Load);
	}
}
