package enemies.bats;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import items.misc.BatEar;
import items.misc.BatWing;
import player.Player;
import util.Interactive;
import util.Randomized;

public class TinyBat extends Enemy {

	public TinyBat() {

		super("Murciélago diminuto", 8, 4, 4, 4);
	}


	@Override
	public void attack(Player player) throws EnemyDeadException {

		if (!isDead()) {

			double simpleAttackProbability = 0.5;
			double screechProbability = 0.5;
			double totalProbability = simpleAttackProbability + screechProbability;
			double ratio = Randomized.randomizeDouble(totalProbability);
			// simpleAttackProbability = 50%, screechProbability = 50%
			// simpleAttackProbability + screechProbability = 100%
			// ratio = 0.0 - 0.5 -> simpleAttack, ratio = 0.51 - 1.0 -> screech
			if (ratio <= simpleAttackProbability) simpleAttack(player);
			else screech(player);
		} else {
			throw new EnemyDeadException();
		}
	}

	@Override
	public void dropItem(Player player) {

		int ratio = Randomized.randomizeNumber(1, 100);
		player.getInventory().addItem(ratio > 50 ? new BatWing() : new BatEar());
	}

	public void simpleAttack(Player player) {

		Interactive.printDialog(String.format("¡%s ataca con %d puntos de daño!", getName(), getDamage()));
		player.takeDamage(getDamage());
	}

	public void screech(Player player) {

		Interactive.printDialog(String.format("¡El murciélago diminuto emite un chillido ensordecedor que causa %d de " +
		                                      "daño!", getDamage() * 2));
		player.takeDamage(getDamage() * 2);
	}
}
