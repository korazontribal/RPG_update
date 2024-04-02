package enemies;

import characters.BasicCharacter;
import game.exceptions.EnemyDeadException;
import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import player.Player;
import player.Stats;
import util.interfaces.Randomized;
import util.managers.ImageManager;
import util.interfaces.Interactive;

import java.awt.*;
import java.util.HashMap;

public abstract class Enemy extends BasicCharacter {
	protected final String name;
	protected final HashMap<Stats, Integer> stats = new HashMap<>();
	protected final int gold;
	protected final int experience;
	protected final int level;
	protected final int maxLevel;
	protected Image image;
	protected final ImageManager imageManager = ImageManager.getInstance();

	public Enemy(Player player, String name, int maxLevel, int health, int gold, int experience) {

		super(name, health, 0);
		this.maxLevel = maxLevel;
		this.level = getLevel(player, maxLevel);
		this.name = name;
		this.maxHp = getHealth(health);
		this.hp = maxHp;
		this.gold = getGold(gold);
		this.experience = getExperience(experience);
	}

	private int getExperience(int experience) {

		return Randomized.randomizeNumber(experience, experience + 2) * level;
	}

	private int getGold(int gold) {

		return Randomized.randomizeNumber(gold, gold + 2) * level;
	}

	private int getHealth(int health) {

		return (Randomized.randomizeNumber(health, health + 2)) * level;
	}

	protected int getDefense(Player player) {

		int defense = stats.get(Stats.DEFENSE) * level;
		if (defense > player.getDamage()) {

			defense = player.getDamage() - 1;
		}
		if (stats.get(Stats.LUCK) > player.getDexterity()) {
			//TODO: Implementar críticos
		}
		return defense;
	}

	protected int getDamage(Player player) {

		int damage = stats.get(Stats.ATTACK) * level;
		if (damage < player.getDefense()) {

			damage = player.getDefense() + 1;
		}
		if (stats.get(Stats.DEXTERITY) > player.getLuck()) {

			//TODO: Implementar críticos
		}
		return damage;
	}

	private int getLevel(Player player, int maxLevel) {

		return maxLevel < player.getLevel() ? Randomized.randomizeNumber(maxLevel, player.getLevel()) :
				player.getLevel();
	}

	public int getLuck() {

		return stats.get(Stats.LUCK);
	}

	@Override
	public String toString() {

		return String.format("Nombre: %s\nNivel: %d\nHP: %d\nFUE: %d\nDEF: %d\nOro: %d\nEXP: %d\nSPD: %d\n",
				name, level, maxHp, stats.get(Stats.ATTACK), stats.get(Stats.DEFENSE), gold, experience,
				stats.get(Stats.SPEED));
	}

	public Image getImage() {

		return image;
	}

	public ImageManager getImageManager() {

		return imageManager;
	}

	public int getLevel() {

		return level;
	}

	public int getMaxLevel() {

		return maxLevel;
	}

	public String getName() {

		return name;
	}

	public int getGold() {

		return gold;
	}

	public int getExperience() {

		return experience;
	}

	public int calculateDamage(Player player) {

		return Math.max(0, (player.getDamage() - getDefense(player)));
	}

	public int calculateDamage(Player player, int damage) {

		return Math.max(0, (damage - getDefense(player)));
	}

	public String takeDamage(Player player) {

		int damage = calculateDamage(player);
		String message = String.format("¡%s sufre %d punto(s) de daño!\n", name, damage);
		hp -= damage;
		if (isDead())
			message += String.format("¡%s ha sido derrotado!\n", name);
		return message;
	}

	public String takeDamage(Player player, int damage) {

		int finalDamage = calculateDamage(player, damage);
		hp -= finalDamage;
		String message = String.format("¡%s sufre %d punto(s) de daño!\n", name, finalDamage);
		if (isDead())
			message += String.format("¡%s ha sido derrotado!\n", name);
		return message;
	}

	public boolean isDead() {

		return hp <= 0;
	}

	public abstract void attack(Player player, CharactersPanel panel) throws EnemyDeadException;

	public abstract void dropItem(Player player, CharactersPanel panel);

	public HashMap<Stats, Integer> getStats() {

		return stats;
	}
}
