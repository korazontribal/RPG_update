import enemies.goblins.RookieGoblin;
import exceptions.ZeroException;
import items.armors.helmets.WoodHelmet;
import player.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Main {

	public static void main(String[] args) {

		RookieGoblin rookieGoblin = new RookieGoblin();
		rookieGoblin.takeDamage(20);
//		try {
//			int x = Integer.parseInt(
//					JOptionPane.showInputDialog("Ingresa un número"));
//			if (x == 0)
//				throw new ZeroException();
//			JOptionPane.showMessageDialog(null, x);
//		} catch (NumberFormatException ex1) {
//			JOptionPane.showMessageDialog(null,
//					"Lo que ingresaste no fue un número ¬_¬");
//		} catch (ZeroException ex2) {
//			JOptionPane.showMessageDialog(null, ex2.getMessage());
//		}
	}
}
