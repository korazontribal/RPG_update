package gui.game;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import gui.panels.*;
import player.Player;
import util.enemies.EnemyFactory;

import javax.swing.*;

public class GameWindow extends JFrame {

	private static GameWindow instance;
	private Player player;
	private Enemy enemy;
	private JPanel rootPanel;
	private JPanel playerPanel;
	private JPanel enemyPanel;
	private JPanel charactersPanel;
	private JTabbedPane bottomPanel;

	public static void main(String[] args) {

		GameWindow.getInstance().startGame();
	}

	public static GameWindow getInstance() {

		if (instance == null) {

			instance = new GameWindow();
		}
		return instance;
	}

	private GameWindow() {

		// Configuración de la ventana
		setContentPane(rootPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void startGame() {

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
		//enemy = EnemyFactory.generateRegularEnemy(player);
		enemy=new RookieGoblin(player);
		playerPanel = PlayerPanel.getInstance(player);
		enemyPanel = EnemyPanel.getInstance(enemy);
		charactersPanel = CharactersPanel.getInstance(player, enemy, this);
		String message = String.format("¡Bienvenido a la aventura, %s!\n", player.getName());
		message += String.format("¡Un %s salvaje apareció!\n", enemy.getName());
		DialogPanel.getInstance().getText().append(message);
		System.out.println(DialogPanel.getInstance().getText().isEditable());
		bottomPanel = ActionsPanel.getInstance();
		new StatusPanel(ActionsPanel.getInstance(), 0, player);
		new BattlePanel(ActionsPanel.getInstance(), this, 1, player, enemy);
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
}
