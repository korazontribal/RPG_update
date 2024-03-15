package items;

import util.Interactive;

import java.io.Serializable;

public abstract class Item implements Serializable {

	protected final String name;
	protected final String description;
	protected final int price;

	public Item(String name, String description, int price) {

		this.name = name;
		this.description = description;
		this.price = price;
	}

	public void displayData() {

		Interactive.printDialog(String.format("Nombre: %s\nDescripción: %s\nPrecio: %d", name, description, price));
	}

	public String getName() {

		return name;
	}

	public String getDescription() {

		return description;
	}
}
