package enemies;

import exceptions.EnemyDeadException;
import player.Player;

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

		System.out.println(name + " takes " + damage + " damage!");
		health -= damage;
		if (isDead())
			System.out.println(name + " has been defeated!");
	}

	public boolean isDead() {

		return health <= 0;
	}

	public abstract void attack(Player player) throws EnemyDeadException;
}
