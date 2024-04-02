package player.skills;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import gui.panels.DialogPanel;
import gui.panels.EnemyPanel;
import gui.panels.PlayerPanel;
import player.Player;
import util.enemies.EnemyFactory;

public class FuryAttack extends Skill {

	private final static FuryAttack instance = new FuryAttack();
	public final static String NAME = "Ataque Furia";

	public static FuryAttack getInstance() {

		if (instance == null) {

			return new FuryAttack();
		} else {
			return instance;
		}
	}

	public FuryAttack() {

		super(NAME, "Ataque al enemigo con 5 puntos de daño", 5, 3);
	}

	@Override
	public String effect(Player player) {

		return null;
	}

	@Override
	public String effect(Player player, Enemy enemy) {

		player.useMp(manaCost);
		String message = "";
		message += String.format("¡%s ataca a %s con %d puntos de daño!\n", player.getName(), enemy.getName(), 5);
		message += enemy.takeDamage(player, 5);
		((DialogPanel) getCharactersPanel().getDialogPanel()).getText().append(message);
		if (enemy.isDead()) {

			message += String.format("%s ha ganado %d de oro y %d de experiencia!\n", player.getName(), enemy.getGold(), enemy.getExperience());
			message += player.gainGold(enemy.getGold());
			message += player.gainExperience(enemy.getExperience());
			((DialogPanel) getCharactersPanel().getDialogPanel()).getText().append(message);
			enemy.dropItem(player, getCharactersPanel());
			Enemy newEnemy = EnemyFactory.generateRegularEnemy(player);
			getCharactersPanel().getWindow().setEnemy(enemy);
			charactersPanel.updateEnemy(newEnemy);
			EnemyPanel enemyPanel = (EnemyPanel) getCharactersPanel().getWindow().getEnemyPanel();
			PlayerPanel playerPanel = (PlayerPanel) getCharactersPanel().getWindow().getPlayerPanel();
			enemyPanel.updateEnemy(newEnemy);
			playerPanel.updatePlayer(player);
		} else {
			try {
				enemy.attack(player, getCharactersPanel());
			} catch (EnemyDeadException e) {
				throw new RuntimeException(e);
			}
			EnemyPanel enemyPanel = (EnemyPanel) getCharactersPanel().getWindow().getEnemyPanel();
			PlayerPanel playerPanel = (PlayerPanel) getCharactersPanel().getWindow().getPlayerPanel();
			enemyPanel.updateEnemy(enemy);
			playerPanel.updatePlayer(player);
		}
		return message;
	}
}
