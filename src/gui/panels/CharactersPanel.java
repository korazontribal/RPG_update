package gui.panels;

import enemies.Enemy;
import enemies.bats.TinyBat;
import game.exceptions.EnemyDeadException;
import game.exceptions.PlayerDeathException;
import gui.labels.SpriteLabel;
import player.Player;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class CharactersPanel extends JPanel {

	private final Image img;
	private final Player player;
	private Enemy enemy;
	private JPanel backgroundPanel;
	private JPanel dialogPanel;
	private JPanel playerSpritePanel;
	private JPanel enemySpritePanel;
	private JLabel playerSprite;
	private JLabel enemySprite;

	public static void main(String[] args) {

		Player player = new Player("Test");
		Enemy enemy = new TinyBat(player);
		JFrame frame = new JFrame("CharactersPanel");
		CharactersPanel charactersPanel = new CharactersPanel(player, enemy);
		frame.setContentPane(charactersPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		try {
			player.attack(enemy, charactersPanel);
			enemy.attack(player, charactersPanel);
			player.attack(enemy, charactersPanel);
			enemy.attack(player, charactersPanel);
			player.attack(enemy, charactersPanel);
			enemy.attack(player, charactersPanel);
		} catch (PlayerDeathException | EnemyDeadException e) {
			throw new RuntimeException(e);
		}
	}

	public CharactersPanel(Player player, Enemy enemy) {

		this.enemy = enemy;
		this.player = player;
		this.img = ImageManager.getInstance().getImage("charactersPanel");
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		add(backgroundPanel);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.drawImage(img, 0, 0, null);
	}

	public void updateEnemy(Enemy enemy) {

		this.enemy = enemy;
		((SpriteLabel)enemySprite).updateImage(enemy.getImage());
		enemySpritePanel.repaint();
		dialogPanel.repaint();
		playerSpritePanel.repaint();
		repaint();
	}

	private void createUIComponents() {

		playerSprite = new SpriteLabel(player.getImage());
		enemySprite = new SpriteLabel(enemy.getImage());
		dialogPanel = new DialogPanel();
	}

	public Image getImg() {

		return img;
	}

	public Player getPlayer() {

		return player;
	}

	public Enemy getEnemy() {

		return enemy;
	}

	public void setEnemy(Enemy enemy) {

		this.enemy = enemy;
	}

	public JPanel getBackgroundPanel() {

		return backgroundPanel;
	}

	public void setBackgroundPanel(JPanel backgroundPanel) {

		this.backgroundPanel = backgroundPanel;
	}

	public JPanel getDialogPanel() {

		return dialogPanel;
	}

	public void setDialogPanel(JPanel dialogPanel) {

		this.dialogPanel = dialogPanel;
	}

	public JPanel getPlayerSpritePanel() {

		return playerSpritePanel;
	}

	public void setPlayerSpritePanel(JPanel playerSpritePanel) {

		this.playerSpritePanel = playerSpritePanel;
	}

	public JPanel getEnemySpritePanel() {

		return enemySpritePanel;
	}

	public void setEnemySpritePanel(JPanel enemySpritePanel) {

		this.enemySpritePanel = enemySpritePanel;
	}

	public JLabel getPlayerSprite() {

		return playerSprite;
	}

	public void setPlayerSprite(JLabel playerSprite) {

		this.playerSprite = playerSprite;
	}

	public JLabel getEnemySprite() {

		return enemySprite;
	}

	public void setEnemySprite(JLabel enemySprite) {

		this.enemySprite = enemySprite;
	}
}
