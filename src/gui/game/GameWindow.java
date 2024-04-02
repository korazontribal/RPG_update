package gui.game;

import enemies.Enemy;
import gui.panels.*;
import player.Player;
import util.enemies.EnemyFactory;

import javax.swing.*;

public class GameWindow extends JFrame {

	private Player player;
	private Enemy enemy;
	private JPanel rootPanel;
	private JPanel playerPanel;
	private JPanel enemyPanel;
	private JPanel charactersPanel;
	private JTabbedPane bottomPanel;

	public static void main(String[] args) {

		new GameWindow();
	}

	public GameWindow() {
		// Configuración de la ventana
		setContentPane(rootPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private void createUIComponents() {

//		try {
//			player = new Player("Miguel");//FileManager.loadGame(new File("files\\game1.dat"));
//		} catch (FileNotFoundException e) {
//			JOptionPane.showMessageDialog(this, "No se encontró el archivo");
//		}
		player = new Player("Miguel");
		enemy = EnemyFactory.generateRegularEnemy(player);
		playerPanel = new PlayerPanel(player);
		enemyPanel = new EnemyPanel(enemy);
		charactersPanel = new CharactersPanel(player, enemy);
		bottomPanel = new ActionsPanel();
		new StatusPanel((ActionsPanel) bottomPanel, 0, player);
		new BattlePanel((ActionsPanel) bottomPanel, this, 1, player, enemy);
	}

	public Player getPlayer() {

		return player;
	}

	public Enemy getEnemy() {

		return enemy;
	}

	public JPanel getPlayerPanel() {

		return playerPanel;
	}

	public JPanel getEnemyPanel() {

		return enemyPanel;
	}

	public JPanel getBattlePanel() {

		return charactersPanel;
	}

	public void setEnemy(Enemy enemy) {

		this.enemy = enemy;
	}

	public void setCharactersPanel(CharactersPanel charactersPanel) {

		this.charactersPanel = charactersPanel;
	}
}
