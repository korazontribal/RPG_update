package player.skills.knights;

import enemies.Enemy;
import player.Player;
import player.skills.Skill;
import util.annotations.JobRestriction;

@JobRestriction(jobs = {"Caballero novato", "Caballero"})
public class SwordBash extends Skill {

	public SwordBash() {

		super("Golpe de espada", "Golpe Básico de Espada", 1, 10);
	}

	@Override
	public String effect(Player player) {

		return null;
	}

	@Override
	public String effect(Player player, Enemy enemy) {

		enemy.takeDamage((int) (player.getStrength() * 1.5));
		return null;
	}
}
