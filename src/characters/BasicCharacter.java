package characters;

import java.io.Serializable;

public abstract class BasicCharacter implements Serializable {

	protected String name;
	protected int hp;
	protected int mp;
	protected int maxHp;
	protected int maxMp;

	public BasicCharacter(String name, int hp, int mp) {

		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.maxHp = hp;
		this.maxMp = mp;
	}

	public String takeDamage(int damage) {

		hp -= damage;
		String message = String.format("¡%s recibe %d puntos de daño!", name, damage);
		if (isDead()) message += String.format("\n¡%s ha sido derrotado!", name);
		return message;
	}

	public void heal(int amount) {

		hp += amount;
		if (hp > maxHp) hp = maxHp;
	}

	public void recoverMp(int amount) {

		mp += amount;
		if (mp > maxMp) mp = maxMp;
	}

	public void useMp(int amount) {

		mp -= amount;
	}

	public boolean isDead() {

		return hp <= 0;
	}

	public String getName() {

		return name;
	}

	public int getHp() {

		return hp;
	}

	public int getMp() {

		return mp;
	}

	public int getMaxHp() {

		return maxHp;
	}

	public int getMaxMp() {

		return maxMp;
	}

	public void setHp(int hp) {

		this.hp = hp;
	}

	public void setMp(int mp) {

		this.mp = mp;
	}
}
