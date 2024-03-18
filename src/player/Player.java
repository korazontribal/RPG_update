package player;

import characters.BasicCharacter;
import enemies.Enemy;
import game.exceptions.PlayerDeathException;
import items.armors.Armor;
import items.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import player.debuffs.Debuff;
import util.Interactive;
import util.Randomized;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Un jugador es un personaje que puede luchar contra enemigos, ganar experiencia y oro, y equipar armas y armaduras.
 *
 * @version 1.0
 * @autor jesus
 */
public class Player extends BasicCharacter implements Serializable {
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
	private Weapon weapon;
	private Armor armor;
	private final Inventory inventory;

	/**
	 * Construye un nuevo jugador con un nombre.
	 *
	 * @param name el nombre del jugador
	 */
	public Player(String name) {

		super(name, 30, 10);
		experience = 0;
		level = 1;
		gold = 0;
		weapon = null;
		armor = null;
		randomizeStats(30);
		inventory = new Inventory();
	}

	/**
	 * Reparte aleatoriamente los puntos de fuerza, defensa, inteligencia, destreza y suerte.
	 *
	 * @param maxPoints los puntos a repartir
	 */
	public void randomizeStats(int maxPoints) {

		int stat = Randomized.randomizeNumber(1, 5);
		while (maxPoints > 0) {
			switch (stat) {
				case 1 -> {
					if (strength < (level * 3)) strength++;
					else maxPoints++;
				}
				case 2 -> {
					if (defense < (level * 3)) defense++;
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

	/**
	 * El jugador equipa un arma.
	 *
	 * @param weapon el arma a equipar
	 */
	public void equipWeapon(Weapon weapon) {

		this.weapon = weapon;
	}

	/**
	 * El jugador equipa una armadura.
	 *
	 * @param armor la armadura a equipar
	 */
	public void equipArmor(Armor armor) {

		this.armor = armor;
	}

	/**
	 * El jugador revive con toda su salud y mana.
	 */
	public void revive() {

		hp = maxHp;
		mp = maxMp;
	}

	/**
	 * El jugador ataca a un enemigo.
	 *
	 * @param enemy el enemigo a atacar
	 *
	 * @throws PlayerDeathException si el jugador está muerto
	 */
	public void attack(@NotNull Enemy enemy) throws PlayerDeathException {

		if (!isDead()) {

			Interactive.printDialog(String.format("%s ataca con %d puntos de daño!", getName(), getDamage()));
			enemy.takeDamage(getDamage());
			if (enemy.isDead()) getRewards(enemy);
		} else {
			throw new PlayerDeathException();
		}
	}

	/**
	 * El Jugador obtiene las recompensas por derrotar a un enemigo.
	 *
	 * @param enemy el enemigo derrotado
	 */
	private void getRewards(@NotNull Enemy enemy) {

		gainExperience(enemy.getExperience());
		gainGold(enemy.getGold());
		enemy.dropItem(this);
	}

	@Override
	public void displayData() {

		String message = String.format("""
						Nombre: %s
						Nivel: %d
						Experiencia: %s
						Salud: %s
						Mana: %s
						Fuerza: %s
						Defensa: %s
						Inteligencia: %d
						Destreza: %d
						Suerte: %d
						Oro: %d
						Arma: %s
						Armadura: %s""",
				getName(), level, getActualExperience(), getActualHp(), getActualMp(), getTotalAttack(),
				getTotalDefense(), intelligence, dexterity, luck, gold, getWeaponName(), getArmorName());
		Interactive.printDialog(message);
	}

	private String getActualHp() {

		return String.format("%d/%d", getHp(), getMaxHp());
	}

	private String getActualMp() {

		return String.format("%d/%d", getMp(), getMaxMp());
	}

	private String getActualExperience() {

		return String.format("%d/%d", experience, level * 20);
	}

	private String getTotalAttack() {

		return weapon != null ? String.format("%d (+ %d)", strength, weapon.getAtk()) : String.valueOf(strength);
	}

	private String getTotalDefense() {

		return armor != null ? String.format("%d (+ %d)", defense, armor.getDef()) : String.valueOf(defense);
	}

	public void takeDamage(int damage) {

		damage -= defense;
		if (armor != null) {

			damage -= armor.getDef();
			if (damage < 0) damage = 0;
		}
		super.takeDamage(damage);
		if (isDead()) printDeath();
	}

	public void gainExperience(int experience) {

		this.experience += experience;
		printExperience(experience);
		checkLevelUp();
	}

	/**
	 * Revisa si el jugador sube de nivel.
	 */
	private void checkLevelUp() {

		if (this.experience >= level * 20) {

			level++;
			maxHp += 5;
			maxMp += 3;
			hp = maxHp;
			mp = maxMp;
			randomizeStats(5);
			printLevelUp();
		}
	}

	public void gainGold(int gold) {

		this.gold += gold;
		printGold(gold);
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
