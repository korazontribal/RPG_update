package items.weapons;

import items.Item;

public abstract class Weapon extends Item {

	protected int atk;

	public Weapon(String name, String description, int price, int atk) {

		super(name, description, price);
		this.atk = atk;
	}

	public int getAtk() {

		return atk;
	}
}
