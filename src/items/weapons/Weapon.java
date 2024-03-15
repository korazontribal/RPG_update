package items.weapons;

import items.Item;

import java.io.Serializable;

public abstract class Weapon extends Item implements Serializable {

	protected int atk;

	public Weapon(String name, String description, int price, int atk) {

		super(name, description, price);
		this.atk = atk;
	}

	public int getAtk() {

		return atk;
	}
}
