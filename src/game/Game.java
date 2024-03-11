package game;

import enemies.Enemy;
import enemies.goblins.RookieGoblin;
import game.exceptions.InvalidOptionException;
import player.Player;

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
		game.printMenu();
	}

	public void printMenu() {

		String menu = "1. Jugar\n2. Salir";
		try {
			int option = Integer.parseInt(JOptionPane.showInputDialog(menu));
			switch (option) {
				case 1 -> {
					player = new Player(JOptionPane.showInputDialog("Ingresa el nombre del jugador:"));
					player.play(enemies);
				}
				case 2 -> JOptionPane.showMessageDialog(null, "Gracias por jugar");
				default -> throw new InvalidOptionException();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "La opción ingresada no es válida");
			printMenu();
		}
	}

}
