package player.skills.knights;

import enemies.Enemy;
import player.Player;
import player.skills.Skill;
import util.annotations.JobRestriction;


@JobRestriction(jobs = {"Caballero novato", "Caballero"})
public class KnigthHeal extends Skill {

	public KnigthHeal() {

		super("Curación de Caballero", "Recupera el 5% de vida del Personaje", 1, 10);
	}

	@Override
	public String effect(Player player) {

		player.heal((int) (player.getMaxHp() * .05));
		return null;
	}

	@Override
	public String effect(Player player, Enemy enemy) {

		return null;
	}
}
