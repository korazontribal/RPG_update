package gui.panels;

import enemies.Enemy;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class SkillPanel extends JScrollPane {

	private final Image img;
	private final Player player;
	private final Enemy enemy;
	private JPanel mainPanel;

	public SkillPanel(Player player, Enemy enemy) {

		this.player = player;
		this.enemy = enemy;
		this.img = ImageManager.getInstance().getImage("skillPanel");
		setViewportView(mainPanel);
		setViewportBorder(null);
		setBorder(null);
		setOpaque(false);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}

	private void createUIComponents() {

		mainPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);
				g.drawImage(img, 0, 0, null);
			}
		};
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setOpaque(false);
	}

	private void addSkill(String skillName) {

		mainPanel.add(new JLabel(skillName));
	}
}
