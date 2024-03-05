package player;

import items.Item;
import util.FixedArrayList;

/**
 * Inventory es una clase que representa el inventario del jugador.
 *
 * @version 1.0
 * @see FixedArrayList
 * @see FixedArrayList#add(Object)
 * @see FixedArrayList#remove(Object)
 * @see FixedArrayList#isEmpty()
 * @see FixedArrayList#size()
 * @see FixedArrayList#ensureCapacity(int)
 * @see FixedArrayList#clear()
 * @see Item
 * @since 1.0
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

		System.out.println(items.add(item) ? item.getName() + " has been added to your inventory." : "Inventory is full.");
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

			System.out.println("Inventory is empty.");
		} else {
			System.out.println("Inventory:");
			for (Item item : items) {
				System.out.println(item.getName() + " - " + item.getDescription());
			}
		}
	}
}
