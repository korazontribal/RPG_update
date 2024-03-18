package items.armors.helmets;

import items.armors.Armor;
import util.Interactive;

import javax.swing.*;
import java.io.Serializable;

public class WoodHelmet extends Armor implements Serializable {

	public WoodHelmet() {

		super("Casco de Madera",
				"Un casco de madera que no protege mucho, pero es mejor que nada.", 5, 2);
	}

	@Override
	public void effect() {

		Interactive.printDialog("Sin Efectos");
	}
}
