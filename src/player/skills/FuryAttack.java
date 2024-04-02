package player.skills;

import enemies.Enemy;
import player.Player;

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

		super(NAME, "Ataque al enemigo con 10 puntos de daño", 5, 10);
	}

	@Override
	public String effect(Player player) {

		return null;
	}

	@Override
	public String effect(Player player, Enemy enemy) {

		if (player.getDexterity() > enemy.getLuck()) {

			enemy.takeDamage(10);
			return String.format("%s ataca a %s con %d puntos de daño!", player.getName(), enemy.getName(), 20);
		} else {

			return String.format("%s esquiva el ataque!", enemy.getName());
		}
	}
}
