package player.skills;

import enemies.Enemy;
import player.Player;

public class BasicHeal extends Skill {

	private static final BasicHeal instance = new BasicHeal();
	public static final String NAME = "Curación Básica";

	public static BasicHeal getInstance() {

		if (instance == null) {

			return new BasicHeal();
		} else {
			return instance;
		}
	}

	private BasicHeal() {

		super(NAME, "Cura al jugador 5 puntos de vida", 3, 5);
	}

	@Override
	public String effect(Player player) {

		player.heal(5);
		return String.format("¡%s ha sido curado por 5 puntos de vida!", player.getName());
	}

	@Override
	public String effect(Player player, Enemy enemy) {

		return null;
	}
}
