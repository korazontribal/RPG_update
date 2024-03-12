package game;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import game.exceptions.InvalidOptionException;
import org.jetbrains.annotations.NotNull;
import player.Player;
import util.Interactive;
import util.Randomized;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Game {
	private Player player;
	private final List<Enemy> enemies;

	public Game() {

		player = null;
		enemies = new ArrayList<>(3);
		enemies.add(new RookieGoblin());
		enemies.add(new RookieGoblin());
		enemies.add(new RookieGoblin());
	}

	public static void main(String[] args) {

		Game game = new Game();
		game.printMainMenu();
	}

	public void printMainMenu() {

		String menu = "1. Jugar\n2. Salir";
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {
				case 1 -> {
					player = new Player(JOptionPane.showInputDialog("Ingresa el nombre del jugador:"));
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

	private void printPlayerMenu() {

		String menu = "1. Ver estadísticas\n2. Ver inventario\n3. Luchar\n4. Salir";
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
				case 4 -> {
					Interactive.printDialog("Gracias por jugar");
					enemies.clear();
				}
				default -> throw new InvalidOptionException();
			}
		} catch (Exception e) {
			Interactive.printDialog("La opción ingresada no es válida");
		}
	}

	private void battleMenu(Enemy enemy) {

		try {
			String battleMenu = "1. Atacar\n2. Huir";
			int battleOption = Integer.parseInt(JOptionPane.showInputDialog(battleMenu));
			switch (battleOption) {
				case 1 -> player.attack(enemy);
				case 2 -> {
					if (Randomized.randomizeBoolean()) {
						player.printRun();
						enemy.setHealth(0);
					} else Interactive.printDialog("No has podido huir!");
				}
				default -> throw new InvalidOptionException();
			}
			if (!enemy.isDead()) enemy.attack(player);
		} catch (Exception e) {
			Interactive.printDialog("La opción ingresada no es válida");
			battleMenu(enemy);
		}
	}

	@NotNull
	private static Enemy getEnemy(List<Enemy> enemies) {

		Enemy enemy = enemies.get(Randomized.randomizeNumber(0, enemies.size() - 1));
		Interactive.printDialog(String.format("Un %s aparece!", enemy.getName()));
		return enemy;
	}
}
