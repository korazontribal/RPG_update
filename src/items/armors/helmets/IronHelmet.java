package items.armors.helmets;

import items.armors.Armor;
import util.Interactive;

import javax.swing.*;
import java.io.Serializable;

public class IronHelmet extends Armor implements Serializable {
	private int str;
	private int counter;

	public IronHelmet(int str) {

		super("Casco de Hierro",
				"Un casco de hierro que protege más que el de madera.", 10, 5);
		this.def = 5;
		this.str = str;
		this.counter = 0;
	}

	public void riseDefense() {

		def += 5;
		counter = 3;
	}

	@Override
	public void effect() {

		Interactive.printDialog("Este casco permité aumentar la defensa del usuario por tres turnos.");
	}

	public int getStr() {

		return str;
	}

	public void setStr(int str) {

		this.str = str;
	}

	public int getCounter() {

		return counter;
	}

	public void setCounter(int counter) {

		this.counter = counter;
	}
}
