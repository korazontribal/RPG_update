package util;

import player.Player;

import java.io.*;

public class FileManager {

	public static Player loadGame() throws FileNotFoundException {

		Player player = null;
		try {
			player = (Player) new ObjectInputStream(new FileInputStream("files/game.dat")).readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new FileNotFoundException("No se encontró el archivo");
		}
		return player;
	}

	public static void saveGame(Player player) {

		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("files/game.dat"));
			oos.writeObject(player);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
