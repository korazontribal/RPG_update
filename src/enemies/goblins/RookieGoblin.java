package enemies.goblins;

import enemies.Enemy;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.Randomized;

import static util.Randomized.randomizeNumber;

/**
 * La clase RookieGoblin es una subclase de la clase Enemy. Es un enemigo básico que el jugador puede encontrar en el juego.
 * It has a unique attack method that allows it to perform one of three actions: plainAttack, runAway, or stealGold.
 * The plainAttack method allows the RookieGoblin to attack the player for a set amount of damage.
 * The runAway method allows the RookieGoblin to flee from battle.
 * The stealGold method allows the RookieGoblin to steal a set amount of gold from the player.
 */
public class RookieGoblin extends Enemy {
	public RookieGoblin() {

		super("Rookie Goblin", 20, 2, 5, 5);
	}

	@Override
	public void attack(Player player) {

		switch (Randomized.randomizeNumber(0, 2)) {

			case 0 -> plainAttack(player);
			case 1 -> runAway();
			case 2 -> stealGold(player);
		}
	}

	private void plainAttack(@NotNull Player player) {

		System.out.println("Rookie Goblin attacks for " + getDamage() + " damage!");
		player.takeDamage(getDamage());
	}

	public void runAway() {

		System.out.println("Rookie Goblin runs away!");
		this.setHealth(0);
	}

	public void stealGold(@NotNull Player player) {

		System.out.println("Rookie Goblin steals 5 gold!");
		player.setGold(player.getGold() - 5);
	}
}
