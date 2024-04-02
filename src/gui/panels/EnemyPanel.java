package gui.panels;

import enemies.Enemy;
import gui.labels.HpLabel;
import gui.labels.TextLabel;
import player.Stats;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class EnemyPanel extends JPanel {

	private final Image background;
	private Enemy enemy;
	private JPanel backgroundPanel;
	private JLabel enemyName;
	private JLabel enemyHp;
	private JLabel attackLabel;
	private JLabel defenseLabel;
	private JLabel expLabel;
	private JLabel golldLabel;

	public EnemyPanel(Enemy enemy) {

		this.enemy = enemy;
		background = ImageManager.getInstance().getImage("enemyPanel");
		add(backgroundPanel);
		setPreferredSize(backgroundPanel.getPreferredSize());
		Dimension size = new Dimension(background.getWidth(null), background.getHeight(null));
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setOpaque(false);
	}

	public void updateEnemy(Enemy enemy) {

		this.enemy = enemy;
		((HpLabel) enemyHp).updateCharacter(this.enemy);
		((TextLabel) enemyName).updateName(this.enemy.getName());
		((TextLabel) attackLabel).setDisplayText(String.format("FUE: %d", this.enemy.getStats().get(Stats.ATTACK)));
		((TextLabel) defenseLabel).setDisplayText(String.format("DEF: %d", this.enemy.getStats().get(Stats.DEFENSE)));
		((TextLabel) expLabel).setDisplayText(String.format("EXP: %d", this.enemy.getExperience()));
		((TextLabel) golldLabel).setDisplayText(String.format("ORO: %d", this.enemy.getGold()));
		enemyName.repaint();
		attackLabel.repaint();
		defenseLabel.repaint();
		expLabel.repaint();
		golldLabel.repaint();
		paintComponents(getGraphics());
	}

	private void createUIComponents() {

		enemyName = new TextLabel(enemy.getName());
		enemyHp = new HpLabel(enemy);
		attackLabel = new TextLabel(String.format("FUE: %d", enemy.getStats().get(Stats.ATTACK)), "textHolder");
		defenseLabel = new TextLabel(String.format("DEF: %d", enemy.getStats().get(Stats.DEFENSE)), "textHolder");
		expLabel = new TextLabel(String.format("EXP: %d", enemy.getExperience()), "textHolder");
		golldLabel = new TextLabel(String.format("ORO: %d", enemy.getGold()), "textHolder");
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.drawImage(background, 0, 0, null);
	}
}
