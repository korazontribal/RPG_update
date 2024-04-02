package gui.panels;

import gui.labels.HpLabel;
import gui.labels.MpLabel;
import gui.labels.PortraitLabel;
import gui.labels.TextLabel;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {

	private static PlayerPanel instance;
	private final Image background;
	private Player player;
	private JPanel backgroundPanel;
	private JLabel portraitLabel;
	private JLabel playerName;
	private JLabel jobLabel;
	private JLabel hpLabel;
	private JLabel levelLabel;
	private JLabel mpLabel;

	public static PlayerPanel getInstance(Player player) {

		if (instance == null) {

			instance = new PlayerPanel(player);
		}
		return instance;
	}

	private PlayerPanel(Player player) {

		this.player = player;
		background = ImageManager.getInstance().getImage("playerPanel");
		add(backgroundPanel);
		setPreferredSize(backgroundPanel.getPreferredSize());
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setOpaque(false);
	}

	public void updatePlayer(Player player) {

		this.player = player;
		((HpLabel) hpLabel).updateCharacter(player);
		((MpLabel) mpLabel).updateCharacter(player);
		mpLabel.repaint();
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.drawImage(background, 0, 0, null);
	}

	private void createUIComponents() {

		portraitLabel = PortraitLabel.getInstance();
		playerName = new TextLabel(player.getName(), "textHolder");
		levelLabel = new TextLabel(String.format("Nivel: %d", player.getLevel()), "textHolder");
		if (player.getJob() != null) {

			jobLabel = new TextLabel(player.getJob().getName(), "jobHolder");
		} else {
			jobLabel = new TextLabel("Aventurero", "jobHolder");
		}
		hpLabel = new HpLabel(player);
		mpLabel = MpLabel.getInstance(player);
	}
}
