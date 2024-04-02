package game;

import enemies.Enemy;
import game.exceptions.EnemyDeadException;
import game.exceptions.InvalidOptionException;
import game.exceptions.PlayerDeathException;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.managers.FileManager;
import util.interfaces.Interactive;
import util.interfaces.Randomized;

import javax.swing.*;
import java.io.File;
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
	private final int slot;
	/**
	 * La lista de enemigos del juego.
	 */
	private final List<Enemy> enemies;

	/**
	 * Constructor de la clase Game.
	 */
	public Game(int slot) {

		this.slot = slot;
		player = null;
		enemies = new ArrayList<>(5);
		/*enemies.add(new RookieGoblin());
		enemies.add(new AloneWolf());
		enemies.add(new RookieGoblin());
		enemies.add(new AloneWolf());
		enemies.add(new RookieGoblin());*/
		printMainMenu();
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
						player = FileManager.loadGame(new File("files\\game" + slot + ".dat"));
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
		try {

			int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {

				case 1 -> Interactive.printDialog(" ");
				case 2 -> player.getInventory().printItems();
				case 3 -> attackCycle();
				case 4 -> equipWeaponMenu();
				case 5 -> equipArmorMenu();
				case 6 -> endGame();
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
	 * Muestra el menú de fin de juego.
	 */
	private void endGame() {

		Interactive.printDialog("Gracias por jugar");
		FileManager.saveGame(player, slot);
		enemies.clear();
	}

	/**
	 * Realiza el ciclo de ataque.
	 */
	private void attackCycle() {

		Enemy currentEnemy;
		currentEnemy = getEnemy(enemies);
		while (!currentEnemy.isDead() && !player.isDead()) {

			battleMenu(currentEnemy);
		}
		enemies.remove(currentEnemy);
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

					case 1 -> battleOrder(enemy);
					case 2 -> fleeTry(enemy);
					default -> throw new InvalidOptionException();
				}
			} catch (InvalidOptionException e) {

				Interactive.printDialog("La opción ingresada no es válida");
				battleMenu(enemy);
			} catch (PlayerDeathException e) {

				gameOver();
			} catch (EnemyDeadException e) {

				Interactive.printDialog("El enemigo ha muerto!");
				enemy.setHp(0);
			}
		}
	}


	/**
	 * Muestra el mensaje de fin de juego al perder contra un enemigo.
	 */
	private void gameOver() {

		Interactive.printDialog("Has muerto!");
		Interactive.printDialog("Quizás deberías entrenar más antes de intentar pelear con los enemigos.");
		player.revive();
		enemies.clear();
	}

	/**
	 * Realiza el orden de ataque.
	 *
	 * @param enemy el enemigo con el que se está peleando
	 *
	 * @throws PlayerDeathException si el jugador muere
	 * @throws EnemyDeadException   si el enemigo muere
	 */
	private void battleOrder(Enemy enemy) throws PlayerDeathException, EnemyDeadException {

//		player.attack(enemy);
//		if (!enemy.isDead()) {
//			enemy.attack(player);
//		}
//		battleMenu(enemy);
	}

	/**
	 * Intenta huir de la batalla.
	 *
	 * @param enemy el enemigo con el que se está peleando
	 */
	private void fleeTry(Enemy enemy) {

		if (Randomized.randomizeBoolean()) {
			player.printRun();
			enemy.setHp(0);
		} else {
			Interactive.printDialog("No has podido huir!");
			battleMenu(enemy);
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