package items.armors;

import items.Item;

public abstract class Armor extends Item {

	protected int def;

	public Armor(String name, String description, int price, int def) {

		super(name, description, price);
		this.def = def;
	}

	public abstract void effect();

	public int getDef() {

		return def;
	}

	public void setDef(int def) {

		this.def = def;
	}
}
