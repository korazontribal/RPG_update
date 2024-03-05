package items.armors.helmets;

import items.armors.Armor;

import javax.swing.*;

public class WoodHelmet extends Armor {

	public WoodHelmet() {

		super("Casco de Madera",
				"Un casco de madera que no protege mucho, pero es mejor que nada.", 5, 1);
		this.def = 1;
	}

	@Override
	public void effect() {

		JOptionPane.showMessageDialog(null, "Sin Efectos");
	}
}
