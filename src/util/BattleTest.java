package util;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import game.exceptions.EnemyDeadException;
import game.exceptions.PlayerDeathException;
import player.Player;

import javax.swing.*;

public class BattleTest {

	public static void battle() {

		Player player = new Player("Juan");
		player.displayData();
		Enemy enemy = new RookieGoblin();
		try {
			while (!enemy.isDead()) {
				player.attack(enemy);
				try {
					enemy.attack(player);
				} catch (EnemyDeadException e) {
					JOptionPane.showMessageDialog(null,
							"El enemigo ya esta muerto!");
					break;
				}
			}
		} catch (PlayerDeathException pdex) {
			System.out.println("Quizás deberías entrenar más");
			player.heal(player.getMaxHp());
		}
		player.displayData();
	}

	public static void main(String[] args) {

		BattleTest.battle();
	}
}
