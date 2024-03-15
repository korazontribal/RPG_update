package enemies;

import game.exceptions.EnemyDeadException;
import player.Player;
import util.Interactive;

public abstract class Enemy {
	private final String name;
	private int health;
	private final int damage;
	private final int gold;
	private final int experience;

	public Enemy(String name, int health, int damage, int gold, int experience) {

		this.name = name;
		this.health = health;
		this.damage = damage;
		this.gold = gold;
		this.experience = experience;
	}

	public String getName() {

		return name;
	}

	public int getHealth() {

		return health;
	}

	public void setHealth(int health) {

		this.health = health;
	}

	public int getDamage() {

		return damage;
	}

	public int getGold() {

		return gold;
	}

	public int getExperience() {

		return experience;
	}

	public void takeDamage(int damage) {

		Interactive.printDialog(String.format("¡%s sufre %d daños!", name, damage));
		health -= damage;
		if (isDead())
			Interactive.printDialog(String.format("¡%s ha sido derrotado!", name));
	}

	public boolean isDead() {

		return health <= 0;
	}

	public abstract void attack(Player player) throws EnemyDeadException;

	public abstract void dropItem(Player player);
}
