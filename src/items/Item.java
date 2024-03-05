package items;

import javax.swing.*;

public abstract class Item {

	protected final String name;
	protected final String description;
	protected final int price;

	public Item(String name, String description, int price) {

		this.name = name;
		this.description = description;
		this.price = price;
	}

	public void displayData() {

		JOptionPane.showMessageDialog(null,
				String.format("%s", description));
	}

	public String getName() {

		return name;
	}

	public String getDescription() {

		return description;
	}
}
