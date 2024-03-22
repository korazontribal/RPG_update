package test;

import javax.swing.*;
import java.awt.*;

public class LabelTest {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Label Test");
		JLabel label = new JLabel("EJEMPLO", JLabel.CENTER);
		label.setIcon(new ImageIcon("img\\17.png"));
		JLabel label2 = new JLabel("Hello, Swing", JLabel.CENTER);
		label2.setForeground(java.awt.Color.RED);
		frame.setLayout(new GridBagLayout());
		frame.add(label, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		frame.add(label2, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(5, 5, 5, 5), 0, 0));
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
