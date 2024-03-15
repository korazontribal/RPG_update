package game;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import enemies.wolfs.AloneWolf;
import game.exceptions.InvalidOptionException;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.FileManager;
import util.Interactive;
import util.Randomized;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La clase Game es la clase principal del juego. Es la clase que controla el flujo del juego y las interacciones entre
 * el jugador y los enemigos.
 */
public class Game {

	/**
	 * El jugador del juego.
	 */
	private Player player;
	/**
	 * La lista de enemigos del juego.
	 */
	private final List<Enemy> enemies;

	/**
	 * Constructor de la clase Game.
	 */
	public Game() {

		player = null;
		enemies = new ArrayList<>(5);
		enemies.add(new RookieGoblin());
		enemies.add(new AloneWolf());
		enemies.add(new RookieGoblin());
		enemies.add(new AloneWolf());
		enemies.add(new RookieGoblin());
	}

	/**
	 * Muestra el menú principal.
	 */
	public void printMainMenu() {

		String menu = "1. Jugar\n2. Salir";
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {
				case 1 -> {
					try {
						player = FileManager.loadGame();
						Interactive.printDialog("¡Bienvenido de nuevo!");
					} catch (Exception e) {
						player = new Player(JOptionPane.showInputDialog("Ingresa el nombre del jugador:"));
					}
					printPlayerMenu();
				}
				case 2 -> Interactive.printDialog("Gracias por jugar");
				default -> throw new InvalidOptionException();
			}
		} catch (Exception e) {
			Interactive.printDialog("La opción ingresada no es válida");
			printMainMenu();
		}
	}

	/**
	 * Muestra el menú del jugador.
	 */
	private void printPlayerMenu() {

		String menu = """
				1. Ver estadísticas
				2. Ver inventario
				""";
		if (!enemies.isEmpty()) menu += String.format("3. Atacar [%d/5 Enemigos]\n", enemies.size());
		menu += """
				4. Equipar arma
				5. Equipar armadura
				6. Salir""";
		Enemy currentEnemy;
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {
				case 1 -> player.displayData();
				case 2 -> player.getInventory().printItems();
				case 3 -> {
					currentEnemy = getEnemy(enemies);
					while (!currentEnemy.isDead() && !player.isDead()) {

						battleMenu(currentEnemy);
					}
					enemies.remove(currentEnemy);
				}
				case 4 -> equipWeaponMenu();
				case 5 -> equipArmorMenu();
				case 6 -> {
					Interactive.printDialog("Gracias por jugar");
					FileManager.saveGame(player);
					enemies.clear();
				}
				default -> throw new InvalidOptionException();
			}
			if (option < 6) {
				printPlayerMenu();
			}
		} catch (Exception e) {
			Interactive.printDialog("La opción ingresada no es válida");
			printPlayerMenu();
		}
	}

	/**
	 * Muestra el menú para equipar una armadura.
	 */
	private void equipArmorMenu() {

		player.getInventory().equipArmorMenu(player);
	}

	/**
	 * Muestra el menú para equipar un arma.
	 */
	private void equipWeaponMenu() {

		player.getInventory().equipWeaponMenu(player);
	}

	/**
	 * Muestra el menú de batalla.
	 *
	 * @param enemy el enemigo con el que se va a pelear
	 */
	private void battleMenu(Enemy enemy) {

		String menu = "1. Atacar\n2. Huir";
		if (!enemy.isDead()) {
			try {
				int battleOption = Integer.parseInt(JOptionPane.showInputDialog(menu));
				switch (battleOption) {
					case 1 -> {
						player.attack(enemy);
						if (!enemy.isDead()) {
							enemy.attack(player);
						}
						battleMenu(enemy);
					}
					case 2 -> {
						if (Randomized.randomizeBoolean()) {
							player.printRun();
							enemy.setHealth(0);
						} else {
							Interactive.printDialog("No has podido huir!");
							battleMenu(enemy);
						}
					}
					default -> throw new InvalidOptionException();
				}
			} catch (Exception e) {
				Interactive.printDialog("La opción ingresada no es válida");
				battleMenu(enemy);
			}
		}
	}


	/**
	 * Obtiene un enemigo aleatorio de la lista de enemigos.
	 *
	 * @param enemies la lista de enemigos
	 *
	 * @return el enemigo seleccionado
	 */
	@NotNull
	private static Enemy getEnemy(List<Enemy> enemies) {

		Enemy enemy = enemies.get(Randomized.randomizeNumber(0, enemies.size() - 1));
		Interactive.printDialog(String.format("Un %s aparece!", enemy.getName()));
		return enemy;
	}
}