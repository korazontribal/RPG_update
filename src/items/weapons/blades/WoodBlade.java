package items.weapons.blades;

import items.weapons.Weapon;

import java.io.Serializable;

public class WoodBlade extends Weapon implements Serializable {

	public WoodBlade() {

		super("Espada de madera", "Una simple espada de madera", 5, 2);
	}

	@Override
	public String toString() {

		return "Espada de madera";
	}
}
