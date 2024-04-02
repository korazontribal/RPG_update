package gui.panels;

import enemies.Enemy;
import gui.tabs.GameTab;
import player.Player;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

public class ActionsPanel extends JTabbedPane {

	public ActionsPanel() {

		super();
		Dimension size = new Dimension(1719, 480);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setOpaque(false);
		setBorder(null);
		setUI(new GameTab());
	}

	public void setTabIcon(int index, ImageIcon icon) {

		JLabel label = new JLabel(icon);
		Dimension size = new Dimension(icon.getIconWidth(), icon.getIconHeight());
		label.setPreferredSize(size);
		label.setMinimumSize(size);
		label.setMaximumSize(size);
		label.setSize(size);
		setTabComponentAt(index, label);
		addChangeListener(e -> {
			if (getSelectedIndex() == index) {
				label.setIcon(icon);
			}
		});
	}
}
