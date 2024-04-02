package gui.events;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import game.exceptions.PlayerDeathException;
import gui.game.GameWindow;
import gui.panels.*;
import player.Player;
import player.Stats;
import util.enemies.EnemyFactory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttackButtonAction implements ActionListener {

	private final Player player;
	private Enemy enemy;
	private GameWindow window;
	private PlayerPanel playerPanel;
	private EnemyPanel enemyPanel;
	private CharactersPanel charactersPanel;

	public AttackButtonAction(Player player, Enemy enemy, GameWindow window) {

		this.player = player;
		this.enemy = enemy;
		this.window = window;
		playerPanel = (PlayerPanel) window.getPlayerPanel();
		enemyPanel = (EnemyPanel) window.getEnemyPanel();
		charactersPanel = (CharactersPanel) window.getBattlePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		((DialogPanel) charactersPanel.getDialogPanel()).setText("");
		if (player.getSpeed() > enemy.getStats().get(Stats.SPEED)) {
			try {
				player.attack(enemy, charactersPanel);
				if (!enemy.isDead()) {
					enemy.attack(player, charactersPanel);
				}
			} catch (PlayerDeathException | EnemyDeadException ex) {
				throw new RuntimeException(ex);
			}
		} else {
			try {
				enemy.attack(player, charactersPanel);
				if (!player.isDead()) {
					player.attack(enemy, charactersPanel);
				}
			} catch (PlayerDeathException | EnemyDeadException ex) {
				throw new RuntimeException(ex);
			}
		}
		if (enemy.isDead()) {

			enemy = EnemyFactory.generateRegularEnemy(player);
			window.setEnemy(enemy);
			charactersPanel.updateEnemy(enemy);
			((DialogPanel) charactersPanel.getDialogPanel()).setText("");
		}
		enemyPanel.updateEnemy(enemy);
		playerPanel.updatePlayer(player);
	}
}
