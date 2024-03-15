package enemies.goblins;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import game.exceptions.ZeroException;
import items.misc.GoblinEar;
import items.weapons.blades.WoodBlade;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.Interactive;
import util.Randomized;

/**
 * La clase RookieGoblin es una subclase de la clase Enemy. Es un enemigo básico que el jugador puede encontrar en el juego.
 * Tiene un método de ataque único que le permite realizar una de tres acciones: plainAttack, runAway, or stealGold.
 * El método plainAttack permite al RookieGoblin atacar al jugador e infligir una cantidad determinada de daño.
 * El método runAway permite al RookieGoblin huir de la batalla.
 * El método stealGold permite al RookieGoblin robar una cantidad determinada de oro del jugador.
 */
public class RookieGoblin extends Enemy {

	/**
	 * Constructor de la clase RookieGoblin.
	 */
	public RookieGoblin() {

		super("Rookie Goblin", 20, 7, 5, 5);
	}

	/**
	 * Función que permite al RookieGoblin atacar al jugador.
	 *
	 * @param player Jugador al que se le ataca.
	 *
	 * @throws EnemyDeadException Excepción que se lanza si el enemigo está muerto.
	 */
	@Override
	public void attack(Player player) throws EnemyDeadException {

		if (!isDead()) {

			double plainAttackProbability = 0.5;
			double runAwayProbability = 0.2;
			double stealGoldProbability = 0.3;
			double totalProbability = plainAttackProbability + runAwayProbability + stealGoldProbability;
			double ratio = Randomized.randomizeDouble(totalProbability);
			// plainAttackProbability = 50%, runAwayProbability = 20%, stealGoldProbability = 30%
			// plainAttackProbability + runAwayProbability + stealGoldProbability = 100%
			// ratio = 0.0 - 0.5 -> plainAttack, ratio = 0.51 - 0.8 -> stealGold, ratio = 0.81 - 1.0 -> runAway
			if (ratio <= plainAttackProbability) plainAttack(player);
			else if (ratio <= plainAttackProbability + stealGoldProbability) stealGold(player);
			else runAway();
		} else {
			throw new EnemyDeadException();
		}
	}

	/**
	 * Función que permite al RookieGoblin soltar un objeto al morir.
	 *
	 * @param player Jugador al que se le suelta el objeto.
	 */
	@Override
	public void dropItem(Player player) {

		int ratio = Randomized.randomizeNumber(1, 100);
		player.getInventory().addItem(ratio > 50 ? new WoodBlade() : new GoblinEar());
	}

	/**
	 * Función que permite al RookieGoblin atacar al jugador.
	 *
	 * @param player Jugador al que se le ataca.
	 */
	private void plainAttack(@NotNull Player player) {

		Interactive.printDialog(String.format("¡El duende novato ataca con %d daño!", getDamage()));
		player.takeDamage(getDamage());
	}

	/**
	 * Función que permite al RookieGoblin huir de la batalla.
	 */
	public void runAway() {

		Interactive.printDialog("¡El Duende novato huye!");
		this.setHealth(0);
	}

	/**
	 * Función que permite al RookieGoblin robar oro del jugador.
	 *
	 * @param player Jugador al que se le roba el oro.
	 */
	public void stealGold(@NotNull Player player) {

		try {
			int minus = player.getGold() - 5;
			if (minus < 0)
				throw new ZeroException();
			player.setGold(minus);
			Interactive.printDialog("¡El Duende novato roba 5 de oro!");
		} catch (ZeroException e) {
			player.setGold(0);
			Interactive.printDialog("¡La billetera del jugador está vacía!");
		}
	}
}
