package enemies.wolfs;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import items.armors.helmets.WoodHelmet;
import items.misc.WolfFur;
import player.Player;
import util.Interactive;
import util.Randomized;

/**
 * La clase AloneWolf es una subclase de la clase Enemy. Es un enemigo básico que el jugador puede encontrar en el juego.
 * Tiene un método de ataque único que le permite realizar una de tres acciones: simpleAttack, howl, or bite.
 * El método simpleAttack permite que AloneWolf ataque al jugador con una cantidad determinada de daño.
 * El método howl permite que AloneWolf aúlle.
 * El método bite permite que AloneWolf muerda al jugador provocando una cantidad determinada de daño.
 */
public class AloneWolf extends Enemy {

	/**
	 * Constructor de la clase Lobo solitario.
	 */
	public AloneWolf() {

		super("Lobo solitario", 30, 5, 10, 10);
	}

	/**
	 * Función que permite al lobo solitario atacar al jugador.
	 *
	 * @param player Jugador al que se le ataca.
	 */
	@Override
	public void attack(Player player) throws EnemyDeadException {

		if (!isDead()) {

			double simpleAttackProbability = 0.5;
			double howlProbability = 0.3;
			double biteProbability = 0.2;
			double totalProbability = simpleAttackProbability + howlProbability + biteProbability;
			double ratio = Randomized.randomizeDouble(totalProbability);
			// simpleAttackProbability = 50%, howlProbability = 30%, biteProbability = 20%
			// simpleAttackProbability + howlProbability + biteProbability = 100%
			// ratio = 0.0 - 0.5 -> simpleAttack, ratio = 0.51 - 0.7 -> bite, ratio = 0.71 - 1.0 -> howl
			if (ratio <= simpleAttackProbability) simpleAttack(player);
			else if (ratio <= simpleAttackProbability + biteProbability) bite(player);
			else howl(player);
		} else {
			throw new EnemyDeadException();
		}
	}

	/**
	 * Función que permite al lobo solitario soltar un objeto al morir.
	 *
	 * @param player Jugador al que se le suelta el objeto.
	 */
	@Override
	public void dropItem(Player player) {

		int ratio = Randomized.randomizeNumber(1, 100);
		player.getInventory().addItem(ratio > 65 ? new WoodHelmet() : new WolfFur());
	}

	/**
	 * Función que permite al lobo solitario atacar.
	 *
	 * @param player Jugador al que se le ataca.
	 */
	public void simpleAttack(Player player) {

		Interactive.printDialog(String.format("¡%s ataca con %d puntos de daño!", getName(), getDamage()));
		player.takeDamage(getDamage());
	}

	/**
	 * Función que permite al lobo solitario aullar.
	 *
	 * @param player Jugador al que se le aúlla.
	 */
	public void howl(Player player) {

		Interactive.printDialog("¡Lobo solitario aúlla!");
		//TODO: Implementar efecto de aullido.
	}

	/**
	 * Función que permite al lobo lanzar una mordida poderosa.
	 *
	 * @param player Jugador al que se le muerde.
	 */
	public void bite(Player player) {

		int totalDamage = getDamage() + 3;
		Interactive.printDialog(String.format("¡%s muerde con %d puntos de daño!", getName(), totalDamage));
		player.takeDamage(totalDamage);
	}
}
