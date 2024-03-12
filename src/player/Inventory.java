package player;

import items.Item;
import util.FixedArrayList;
import util.Interactive;

/**
 * Inventory es una clase que representa el inventario del jugador.
 *
 * @version 1.0
 */
public class Inventory {

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
