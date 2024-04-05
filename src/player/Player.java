package player;

import characters.BasicCharacter;
import enemies.Enemy;
import game.exceptions.PlayerDeathException;
import gui.game.GameWindow;
import gui.panels.ActionsPanel;
import gui.panels.CharactersPanel;
import gui.panels.DialogPanel;
import gui.panels.StatusPanel;
import items.armors.Armor;
import items.weapons.Weapon;
import org.jetbrains.annotations.NotNull;
import player.jobs.Job;
import player.skills.BasicHeal;
import player.skills.FuryAttack;
import player.skills.Skill;
import util.interfaces.Interactive;
import util.interfaces.Randomized;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	private int resistance;
	private int speed;
	private int experience;
	private int level;
	private int gold;
	private Job job;
	private Weapon weapon;
	private Armor headArmor;
	private Armor chestArmor;
	private Armor legArmor;
	private Armor footArmor;
	private Armor handArmor;
	private final Inventory inventory;
	private Map<String, Skill> skillMap;

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
		job = null;
		weapon = null;
		headArmor = null;
		chestArmor = null;
		legArmor = null;
		footArmor = null;
		handArmor = null;
		strength = 5;
		defense = 5;
		randomizeStats(20);
		inventory = new Inventory();
		skillMap = new HashMap<>();
		skillMap.put(BasicHeal.NAME, BasicHeal.getInstance());
		skillMap.put(FuryAttack.NAME, FuryAttack.getInstance());
	}

	@Override
	public String toString() {

		return String.format("%s - Nivel %d", getName(), level);
	}

	/**
	 * Reparte aleatoriamente los puntos de fuerza, defensa, inteligencia, destreza y suerte.
	 *
	 * @param maxPoints los puntos a repartir
	 */
	public void randomizeStats(int maxPoints) {

		int stat = Randomized.randomizeNumber(1, 7);
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
				case 6 -> resistance++;
				case 7 -> speed++;
			}
			maxPoints--;
			stat = Randomized.randomizeNumber(1, 7);
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

		switch (armor.getType()) {
			case HEAD -> headArmor = armor;
			case CHEST -> chestArmor = armor;
			case LEGS -> legArmor = armor;
			case FEET -> footArmor = armor;
			case HANDS -> handArmor = armor;
		}
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
	public void attack(@NotNull Enemy enemy, CharactersPanel panel) throws PlayerDeathException {

		if (!isDead()) {

			DialogPanel.getInstance().getText().append(String.format("%s", enemy.takeDamage(this)));
			if (enemy.isDead()) getRewards(enemy, panel);
		} else {
			throw new PlayerDeathException();
		}
	}

	/**
	 * El Jugador obtiene las recompensas por derrotar a un enemigo.
	 *
	 * @param enemy el enemigo derrotado
	 */
	private void getRewards(@NotNull Enemy enemy, CharactersPanel panel) {

		String message = gainExperience(enemy.getExperience());
		message += gainGold(enemy.getGold());
		((DialogPanel) panel.getDialogPanel()).getText().append(message);
		enemy.dropItem(this, panel);
		StatusPanel.getInstance(ActionsPanel.getInstance(), 0, panel.getPlayer()).update();
	}

	public String getActualHp() {

		return String.format("%d/%d", getHp(), getMaxHp());
	}

	public String getActualMp() {

		return String.format("%d/%d", getMp(), getMaxMp());
	}

	private int getWeaponStat(Weapon weapon, Stats stat) {

		return weapon != null && weapon.getStats().containsKey(stat) ? weapon.getStats().get(stat) : 0;
	}

	private int getArmorStat(Armor armor, Stats stat) {

		return armor != null && armor.getStats().containsKey(stat) ? armor.getStats().get(stat) : 0;
	}

	public String getTotalAttack() {

		int plusAttack = 0;
		String message = String.format("FUE: %d", getStrength());
		plusAttack += getWeaponStat(weapon, Stats.ATTACK);
		plusAttack += getArmorStat(headArmor, Stats.ATTACK);
		plusAttack += getArmorStat(chestArmor, Stats.ATTACK);
		plusAttack += getArmorStat(legArmor, Stats.ATTACK);
		plusAttack += getArmorStat(footArmor, Stats.ATTACK);
		plusAttack += getArmorStat(handArmor, Stats.ATTACK);
		if (plusAttack > 0) {
			message += String.format(" (+%d)", plusAttack);
		}
		return message;
	}

	private int getAtk() {

		int plusAttack = 0;
		plusAttack += getWeaponStat(weapon, Stats.ATTACK);
		plusAttack += getArmorStat(headArmor, Stats.ATTACK);
		plusAttack += getArmorStat(chestArmor, Stats.ATTACK);
		plusAttack += getArmorStat(legArmor, Stats.ATTACK);
		plusAttack += getArmorStat(footArmor, Stats.ATTACK);
		plusAttack += getArmorStat(handArmor, Stats.ATTACK);
		return plusAttack > 0 ? strength + plusAttack : strength;
	}

	public String getTotalDefense() {

		int plusDefense = 0;
		String message = String.format("DEF: %d", getDefense());
		plusDefense += getArmorStat(headArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(chestArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(legArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(footArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(handArmor, Stats.DEFENSE);
		if (plusDefense > 0) {

			message += String.format(" (+%d)", plusDefense);
		}
		return message;
	}

	private int getDef() {

		int plusDefense = 0;
		plusDefense += getArmorStat(headArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(chestArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(legArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(footArmor, Stats.DEFENSE);
		plusDefense += getArmorStat(handArmor, Stats.DEFENSE);
		return plusDefense > 0 ? defense + plusDefense : defense;
	}

	public String getTotalIntelligence() {

		int plusIntelligence = 0;
		String message = String.format("INT: %d", getInt());
		plusIntelligence += getArmorStat(headArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(chestArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(legArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(footArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(handArmor, Stats.INTELLIGENCE);
		if (plusIntelligence > 0) {

			message += String.format(" (+%d)", plusIntelligence);
		}
		return message;
	}

	private int getInt() {

		int plusIntelligence = 0;
		plusIntelligence += getArmorStat(headArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(chestArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(legArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(footArmor, Stats.INTELLIGENCE);
		plusIntelligence += getArmorStat(handArmor, Stats.INTELLIGENCE);
		return plusIntelligence > 0 ? intelligence + plusIntelligence : intelligence;
	}

	public String getTotalDexterity() {

		int plusDexterity = 0;
		String message = String.format("DES: %d", getDex());
		plusDexterity += getArmorStat(headArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(chestArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(legArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(footArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(handArmor, Stats.DEXTERITY);
		if (plusDexterity > 0) {

			message += String.format(" (+%d)", plusDexterity);
		}
		return message;

	}

	private int getDex() {

		int plusDexterity = 0;
		plusDexterity += getArmorStat(headArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(chestArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(legArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(footArmor, Stats.DEXTERITY);
		plusDexterity += getArmorStat(handArmor, Stats.DEXTERITY);
		return plusDexterity > 0 ? dexterity + plusDexterity : dexterity;
	}

	public String getTotalLuck() {

		int plusLuck = 0;
		String message = String.format("SUER: %d", getLck());
		plusLuck += getArmorStat(headArmor, Stats.LUCK);
		plusLuck += getArmorStat(chestArmor, Stats.LUCK);
		plusLuck += getArmorStat(legArmor, Stats.LUCK);
		plusLuck += getArmorStat(footArmor, Stats.LUCK);
		plusLuck += getArmorStat(handArmor, Stats.LUCK);
		if (plusLuck > 0) {

			message += String.format(" (+%d)", plusLuck);
		}
		return message;
	}

	private int getLck() {

		int plusLuck = 0;
		plusLuck += getArmorStat(headArmor, Stats.LUCK);
		plusLuck += getArmorStat(chestArmor, Stats.LUCK);
		plusLuck += getArmorStat(legArmor, Stats.LUCK);
		plusLuck += getArmorStat(footArmor, Stats.LUCK);
		plusLuck += getArmorStat(handArmor, Stats.LUCK);
		return plusLuck > 0 ? luck + plusLuck : luck;
	}

	public String getTotalResistance() {

		int plusResistance = 0;
		String message = String.format("RES: %d", getRes());
		plusResistance += getArmorStat(headArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(chestArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(legArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(footArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(handArmor, Stats.RESISTANCE);
		if (plusResistance > 0) {

			message += String.format(" (+%d)", plusResistance);
		}
		return message;
	}

	private int getRes() {

		int plusResistance = 0;
		plusResistance += getArmorStat(headArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(chestArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(legArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(footArmor, Stats.RESISTANCE);
		plusResistance += getArmorStat(handArmor, Stats.RESISTANCE);
		return plusResistance > 0 ? resistance + plusResistance : resistance;
	}

	public String getTotalSpeed() {

		int plusSpeed = 0;
		String message = String.format("VEL: %d", getSpd());
		plusSpeed += getArmorStat(headArmor, Stats.SPEED);
		plusSpeed += getArmorStat(chestArmor, Stats.SPEED);
		plusSpeed += getArmorStat(legArmor, Stats.SPEED);
		plusSpeed += getArmorStat(footArmor, Stats.SPEED);
		plusSpeed += getArmorStat(handArmor, Stats.SPEED);
		if (plusSpeed > 0) {

			message += String.format(" (+%d)", plusSpeed);
		}
		return message;
	}

	private int getSpd() {

		int plusSpeed = 0;
		plusSpeed += getArmorStat(headArmor, Stats.SPEED);
		plusSpeed += getArmorStat(chestArmor, Stats.SPEED);
		plusSpeed += getArmorStat(legArmor, Stats.SPEED);
		plusSpeed += getArmorStat(footArmor, Stats.SPEED);
		plusSpeed += getArmorStat(handArmor, Stats.SPEED);
		return plusSpeed > 0 ? speed + plusSpeed : speed;
	}

	public String takeDamage(int damage) {

		damage -= getDef();
		String message;
		if (damage < 0) damage = 0;
		message = super.takeDamage(damage);
		if (isDead())
			message += String.format("%s\n", printDeath());
		return message;
	}

	public String gainExperience(int experience) {

		this.experience += experience;
		String message = printExperience(experience);
		message += checkLevelUp();
		return message;
	}

	/**
	 * Revisa si el jugador sube de nivel.
	 */
	private String checkLevelUp() {

		if (this.experience >= level * 20) {

			level++;
			maxHp += 5;
			maxMp += 3;
			hp = maxHp;
			mp = maxMp;
			strength += Randomized.randomizeNumber(1, 3);
			defense += Randomized.randomizeNumber(1, 3);
			randomizeStats(5);
			GameWindow.getInstance().getStatusPanel().update(this);
			return String.format("¡%s ha subido al nivel %d!\n", getName(), level);
		} else {
			return "";
		}
	}

	public String gainGold(int gold) {

		this.gold += gold;
		return printGold(gold);
	}

	public String printDeath() {

		return "¡Has muerto!";
	}

	public void printRun() {

		Interactive.printDialog("¡Has huido!");
	}

	public String printGold(int gold) {

		return String.format("Has ganado %d monedas de oro!\n", gold);
	}

	public String printExperience(int experience) {

		return String.format("Has ganado %d puntos de experiencia!\n", experience);
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

	public String getWeaponName() {

		return weapon != null ? weapon.getName() : "None";
	}

	public int getDamage() {

		return getAtk();
	}

	public String getName() {

		return super.getName();
	}

	public Inventory getInventory() {

		return inventory;
	}

	public int getResistance() {

		return resistance;
	}

	public int getSpeed() {

		return speed;
	}

	public Job getJob() {

		return job;
	}

	public Armor getHeadArmor() {

		return headArmor;
	}

	public Armor getChestArmor() {

		return chestArmor;
	}

	public Armor getLegArmor() {

		return legArmor;
	}

	public Armor getFootArmor() {

		return footArmor;
	}

	public Armor getHandArmor() {

		return handArmor;
	}

	public Image getImage() {

		return ImageManager.getInstance().getImage("player",
				new ImageIcon("img\\player\\player.png").getImage());
	}

	public Map<String, Skill> getSkillMap() {

		return skillMap;
	}
}
