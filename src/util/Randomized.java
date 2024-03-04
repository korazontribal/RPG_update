package util;

import java.util.Random;

/**
 * Randomized es una interfaz que proporciona métodos para generar números aleatorios, así como valores booleanos como
 * si de una moneda se tratara.
 *
 * @version 1.0
 * @see Random
 * @see Random#nextInt(int)
 * @see Random#nextBoolean()
 * @see Random#nextDouble()
 * @see Random#nextDouble()
 * @since 1.0
 */
public interface Randomized {

	/**
	 * Genera un número aleatorio entre los valores especificados.
	 *
	 * @param min el valor mínimo del número generado
	 * @param max el valor máximo del número generado
	 *
	 * @return un número aleatorio entre los valores especificados
	 */
	static int randomizeNumber(int min, int max) {

		return new Random().nextInt(max - min + 1) + min;
	}

	/**
	 * Genera un valor booleano aleatorio.
	 *
	 * @return un valor booleano aleatorio
	 */
	static boolean randomizeBoolean() {

		return new Random().nextBoolean();
	}

	/**
	 * Genera un número decimal aleatorio entre los valores especificados.
	 *
	 * @param min el valor mínimo del número generado
	 * @param max el valor máximo del número generado
	 *
	 * @return un número decimal aleatorio entre los valores especificados
	 */
	static double randomizeDouble(double min, double max) {

		return new Random().nextDouble() * (max - min) + min;
	}
}
