package player;

import items.Item;
import items.armors.Armor;
import items.weapons.Weapon;
import util.FixedArrayList;
import util.Interactive;

import javax.swing.*;
import java.io.Serializable;
import java.util.List;

/**
 * Inventory es una clase que representa el inventario del jugador.
 *
 * @version 1.0
 */
public class Inventory implements Serializable {

	/**
	 * La lista de elementos en el inventario.
	 */
	private final FixedArrayList<Item> items;

	/**
	 * Construye un nuevo inventario con una capacidad fija de 15.
	 */
	public Inventory() {

		items = new FixedArrayList<>(15);
	}

	/**
	 * Muestra el menú para equipar una armadura.
	 *
	 * @param player el jugador que equipará la armadura
	 */
	public void equipArmorMenu(Player player) {

		StringBuilder message = new StringBuilder("Equipar Armadura:\n");
		String itemMessage;
		List<Armor> armors = items.filterArmors();
		for (int i = 0; i < armors.size(); i++) {

			itemMessage = String.format("%d. %s - %s\n", i + 1, armors.get(i).getName(), armors.get(i).getDescription());
			message.append(itemMessage);
		}
		message.append("0. Salir\n");
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(message.toString()));
			if (option > 0 && option <= armors.size()) {

				if (player.getArmor() != null)
					items.add(player.getArmor());
				player.equipArmor(armors.get(option - 1));
				player.printEquipArmor(armors.get(option - 1));
				items.remove(armors.get(option - 1));
			}
		} catch (Exception e) {
			Interactive.printDialog("Opción inválida.");
			equipArmorMenu(player);
		}
	}

	/**
	 * Muestra el menú para equipar un arma.
	 *
	 * @param player el jugador que equipará el arma
	 */
	public void equipWeaponMenu(Player player) {

		StringBuilder message = new StringBuilder("Equipar Arma:\n");
		String itemMessage;
		List<Weapon> weapons = items.filterWeapons();
		for (int i = 0; i < weapons.size(); i++) {

			itemMessage = String.format("%d. %s - %s\n", i + 1, weapons.get(i).getName(), weapons.get(i).getDescription());
			message.append(itemMessage);
		}
		message.append("0. Salir\n");
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(message.toString()));
			if (option > 0 && option <= weapons.size()) {

				if (player.getWeapon() != null)
					items.add(player.getWeapon());
				player.equipWeapon(weapons.get(option - 1));
				player.printEquipWeapon(weapons.get(option - 1));
				items.remove(weapons.get(option - 1));
			}
		} catch (Exception e) {
			Interactive.printDialog("Opción inválida.");
			equipWeaponMenu(player);
		}
	}

	/**
	 * Agrega un elemento al inventario.
	 *
	 * @param item el elemento a agregar
	 */
	public void addItem(Item item) {

		String addMessage = String.format("%s se ha agregado al Inventario!", item.getName());
		Interactive.printDialog(items.add(item) ? addMessage : "Inventario Lleno.");
	}

	/**
	 * Remueve un elemento del inventario.
	 *
	 * @param item el elemento a remover
	 */
	public void removeItem(Item item) {

		items.remove(item);
	}

	/**
	 * Obtiene la lista de elementos en el inventario.
	 *
	 * @return la lista de elementos en el inventario
	 */
	public FixedArrayList<Item> getItems() {

		return items;
	}

	/**
	 * Imprime en pantalla los elementos en el inventario.
	 */
	public void printItems() {

		if (items.isEmpty()) {

			Interactive.printDialog("Inventario Vació.");
		} else {
			StringBuilder message = new StringBuilder("Inventario:\n");
			String itemMessage;
			for (Item item : items) {
				itemMessage = String.format("%s - %s\n", item.getName(), item.getDescription());
				message.append(itemMessage);
			}
			Interactive.printDialog(message.toString());
		}
	}
}
