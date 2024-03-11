package player;

import characters.BasicCharacter;
import enemies.Enemy;
import game.exceptions.InvalidOptionException;
import items.armors.Armor;
import items.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import player.debuffs.Debuff;
import util.Randomized;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Un jugador es un personaje que puede luchar contra enemigos, ganar experiencia y oro, y equipar armas y armaduras.
 *
 * @version 1.0
 * @autor jesus
 */
public class Player extends BasicCharacter {
	/**
	 * La fuerza del jugador.
	 */
	private int strength;
	/**
	 * La defensa del jugador.
	 */
	private int defense;
	/**
	 * La inteligencia del jugador.
	 */
	private int intelligence;
	private int dexterity;
	private int luck;
	private int experience;
	private int level;
	private int gold;
	private ArrayList<Debuff> debuffs;
	private Weapon weapon;
	private Armor armor;
	private final Inventory inventory;

	public Player(String name) {

		super(name, 30, 10);
		debuffs = new ArrayList<>(5);
		experience = 0;
		level = 1;
		gold = 0;
		weapon = null;
		armor = null;
		randomizeStats(30);
		inventory = new Inventory();
	}

	public void play(List<Enemy> enemies) {

		JOptionPane.showMessageDialog(null, "Bienvenido a la aventura, " + getName() + "!");
		while (!enemies.isEmpty()) {

			String menu = "1. Ver estadísticas\n2. Ver inventario\n3. Luchar\n4. Salir";
			Enemy currentEnemy;
			try {
				int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
				switch (option) {
					case 1 -> displayData();
					case 2 -> inventory.printItems();
					case 3 -> {
						currentEnemy = getEnemy(enemies);
						while (!currentEnemy.isDead() && !isDead()) {

							attackMenu(currentEnemy);
						}
						enemies.remove(currentEnemy);
					}
					case 4 -> {
						JOptionPane.showMessageDialog(null, "Gracias por jugar");
						enemies.clear();
					}
					default -> throw new InvalidOptionException();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
			}
		}
	}

	@NotNull
	private static Enemy getEnemy(List<Enemy> enemies) {

		Enemy enemy = enemies.get(Randomized.randomizeNumber(0, enemies.size() - 1));
		JOptionPane.showMessageDialog(null, "Un " + enemy.getName() + " aparece!");
		return enemy;
	}

	private void attackMenu(Enemy enemy) {

		try {
			String battleMenu = "1. Atacar\n2. Huir";
			int battleOption = Integer.parseInt(JOptionPane.showInputDialog(battleMenu));
			switch (battleOption) {
				case 1 -> attack(enemy);
				case 2 -> {
					if (Randomized.randomizeBoolean()) {
						printRun();
						enemy.setHealth(0);
					} else JOptionPane.showMessageDialog(null, "No has podido huir!");
				}
				default -> throw new InvalidOptionException();
			}
			if (!enemy.isDead()) enemy.attack(this);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
			attackMenu(enemy);
		}
	}

	public void randomizeStats(int maxPoints) {

		int stat = Randomized.randomizeNumber(1, 5);
		while (maxPoints > 0) {
			switch (stat) {
				case 1 -> {
					if (strength < (level * 5)) strength++;
					else maxPoints++;
				}
				case 2 -> {
					if (defense < (level * 5)) defense++;
					else maxPoints++;
				}
				case 3 -> intelligence++;
				case 4 -> dexterity++;
				case 5 -> luck++;
			}
			maxPoints--;
			stat = Randomized.randomizeNumber(1, 5);
		}
	}

	public void equipWeapon(Weapon weapon) {

		this.weapon = weapon;
	}

	public void equipArmor(Armor armor) {

		this.armor = armor;
	}

	public void attack(@NotNull Enemy enemy) {

		String message = String.format("%s ataca con %d puntos de daño!", getName(), getDamage());
		JOptionPane.showMessageDialog(null, message);
		enemy.takeDamage(getDamage());
		if (enemy.isDead()) {

			gainExperience(enemy.getExperience());
			gainGold(enemy.getGold());
		}
	}

	@Override
	public void displayData() {

		System.out.println("Name: " + getName());
		System.out.println("Level: " + level);
		System.out.println("Experience: " + experience + "/" + level * 10);
		System.out.println("Health: " + getMaxHp());
		System.out.println("Mana: " + getMaxMp());
		System.out.println("Strength: " + strength);
		System.out.println("Defense: " + defense);
		System.out.println("Intelligence: " + intelligence);
		System.out.println("Dexterity: " + dexterity);
		System.out.println("Luck: " + luck);
		System.out.println("Gold: " + gold);
	}

	public void takeDamage(int damage) {

		damage -= defense;
		if (armor != null) {

			damage -= armor.getDef();
			if (damage < 0) damage = 0;
		}
		for (Debuff debuff : debuffs) {

			damage += debuff.getDamage();
			debuff.reduceDuration();
			if (debuff.getDuration() == 0) debuffs.remove(debuff);
		}
		super.takeDamage(damage);
		if (isDead()) printDeath();
	}

	public void gainExperience(int experience) {

		this.experience += experience;
		System.out.println("You have gained " + experience + " experience!");
		if (this.experience >= level * 20) {

			level++;
			strength++;
			defense++;
			intelligence++;
			dexterity++;
			luck++;
			maxHp += 5;
			maxMp += 3;
			hp = maxHp;
			mp = maxMp;
			randomizeStats(3);
			printLevelUp();
		}
	}

	public void gainGold(int gold) {

		this.gold += gold;
	}

	public void printLevelUp() {

		System.out.println("Congratulations! You have leveled up to level " + level + "!");
		displayData();
	}

	public void printDeath() {

		System.out.println("You have died!");
	}

	public void printRun() {

		System.out.println("You have successfully ran away!");
	}

	public void printGold(int gold) {

		System.out.println("You have gained " + gold + " gold!");
	}

	public void printExperience(int experience) {

		System.out.println("You have gained " + experience + " experience!");
	}

	public void printHeal(int heal) {

		System.out.println("You have healed for " + heal + " health!");
	}

	public void printEquipWeapon(@NotNull Weapon weapon) {

		System.out.println("You have equipped " + weapon.getName() + "!");
	}

	public void printEquipArmor(@NotNull Armor armor) {

		System.out.println("You have equipped " + armor.getName() + "!");
	}

	public void printUnequipWeapon(@NotNull Weapon weapon) {

		System.out.println("You have unequipped " + weapon.getName() + "!");
	}

	public void printUnequipArmor(@NotNull Armor armor) {

		System.out.println("You have unequipped " + armor.getName() + "!");
	}

	//Getters and Setters

	public ArrayList<Debuff> getDebuffs() {

		return debuffs;
	}

	public void setDebuffs(ArrayList<Debuff> debuffs) {

		this.debuffs = debuffs;
	}

	public int getLevel() {

		return level;
	}

	public int getGold() {

		return gold;
	}

	public void setGold(int gold) {

		this.gold = gold;
	}

	public int getExperience() {

		return experience;
	}

	public int getStrength() {

		return strength;
	}

	public int getDefense() {

		return defense;
	}

	public void setDefense(int defense) {

		this.defense = defense;
	}

	public int getIntelligence() {

		return intelligence;
	}

	public int getDexterity() {

		return dexterity;
	}

	public int getLuck() {

		return luck;
	}

	public Weapon getWeapon() {

		return weapon;
	}

	public Armor getArmor() {

		return armor;
	}

	public String getWeaponName() {

		return weapon != null ? weapon.getName() : "None";
	}

	public String getArmorName() {

		return armor != null ? armor.getName() : "None";
	}

	public int getDamage() {

		return weapon != null ? strength + weapon.getAtk() : strength;
	}

	public String getName() {

		return super.getName();
	}

	public Inventory getInventory() {

		return inventory;
	}
}
