package player;

import characters.BasicCharacter;
import enemies.Enemy;
import exceptions.PlayerDeathException;
import items.armors.Armor;
import items.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import player.debuffs.Debuff;
import util.Interactive;
import util.Randomized;

import java.util.ArrayList;

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

	public void attack(@NotNull Enemy enemy) throws PlayerDeathException {

		if (!isDead()) {
			Interactive.printDialog(String.format("%s ataca con %d puntos de daño!", getName(), getDamage()));
			enemy.takeDamage(getDamage());
			if (enemy.isDead()) {

				gainExperience(enemy.getExperience());
				gainGold(enemy.getGold());
			}
		} else {
			throw new PlayerDeathException();
		}
	}

	@Override
	public void displayData() {

		String message = String.format("""
						Nombre: %s
						Nivel: %d
						Experiencia: %d/%d
						Salud: %d/%d
						Mana: %d
						Fuerza: %d
						Defensa: %d
						Inteligencia: %d
						Destreza: %d
						Suerte: %d
						Oro: %d
						Arma: %s
						Armadura: %s""",
				getName(), level, experience, level * 20, getHp(), getMaxHp(), getMaxMp(), strength, defense, intelligence,
				dexterity, luck, gold, getWeaponName(), getArmorName());
		Interactive.printDialog(message);
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
		Interactive.printDialog(String.format("Has ganado %d puntos de experiencia!", experience));
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

		Interactive.printDialog(String.format("¡Felicidades! Has subido al nivel %d!", level));
		displayData();
	}

	public void printDeath() {

		Interactive.printDialog("¡Has muerto!");
	}

	public void printRun() {

		Interactive.printDialog("¡Has huido!");
	}

	public void printGold(int gold) {

		Interactive.printDialog(String.format("Has ganado %d monedas de oro!", gold));
	}

	public void printExperience(int experience) {

		Interactive.printDialog(String.format("Has ganado %d puntos de experiencia!", experience));
	}

	public void printHeal(int heal) {

		Interactive.printDialog(String.format("Has recuperado %d puntos de salud!", heal));
	}

	public void printEquipWeapon(@NotNull Weapon weapon) {

		Interactive.printDialog(String.format("Has equipado %s!", weapon.getName()));
	}

	public void printEquipArmor(@NotNull Armor armor) {

		Interactive.printDialog(String.format("Has equipado %s!", armor.getName()));
	}

	public void printUnequipWeapon(@NotNull Weapon weapon) {

		Interactive.printDialog(String.format("Has desequipado %s!", weapon.getName()));
	}

	public void printUnequipArmor(@NotNull Armor armor) {

		Interactive.printDialog(String.format("Has desequipado %s!", armor.getName()));
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
