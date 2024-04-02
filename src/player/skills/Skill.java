package player.skills;

import enemies.Enemy;
import player.Player;

public abstract class Skill {

	protected String name;
	protected String description;
	protected int level;
	protected int manaCost;

	public Skill(String name, String description, int level, int manaCost) {

		this.name = name;
		this.description = description;
		this.level = level;
		this.manaCost = manaCost;
	}

	public String activate(Player player) {

		if (player.getMp() < manaCost) {
			return "¡No hay suficiente maná!";
		}
		player.useMp(manaCost);
		return effect(player);
	}

	public String activate(Player player, Enemy enemy) {

		if (player.getMp() < manaCost) {
			return "¡No hay suficiente maná!";
		}
		player.useMp(manaCost);
		return effect(player, enemy);
	}

	public abstract String effect(Player player);

	public abstract String effect(Player player, Enemy enemy);

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public int getLevel() {

		return level;
	}

	public void setLevel(int level) {

		this.level = level;
	}

	public int getManaCost() {

		return manaCost;
	}

	public void setManaCost(int manaCost) {

		this.manaCost = manaCost;
	}
}
