package util;

import java.util.ArrayList;

/**
 * Una FixedArrayList es una lista que tiene una capacidad fija y puede cambiar de tamaño de acuerdo a su función
 * expandCapacity.
 *
 * @param <T> the type of elements in the list
 *
 * @author jesus
 */
public class FixedArrayList<T> extends ArrayList<T> {
	private int capacity;

	/**
	 * Construye una nueva FixedArrayList con la capacidad especificada.
	 *
	 * @param capacity la capacidad de la lista
	 */
	public FixedArrayList(int capacity) {

		super(capacity);
		this.capacity = capacity;
	}

	/**
	 * Agrega el elemento especificado a la lista si la lista no está llena.
	 *
	 * @param e el elemento a agregar
	 *
	 * @return verdadero si se agregó el elemento, falso si la lista está llena.
	 */
	@Override
	public boolean add(T e) {

		return size() < capacity && super.add(e);
	}

	/**
	 * Expande la capacidad de la lista en la cantidad especificada.
	 *
	 * @param amount la cantidad en la que se expandirá la capacidad.
	 */
	public void expandCapacity(int amount) {

		capacity += amount;
		ensureCapacity(capacity);
	}
}
