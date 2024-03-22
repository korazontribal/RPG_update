package gui;

import player.Player;
import util.FileManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SelectFileWindow extends JFrame {
	private JPanel rootPane;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JLabel gameName;
	private JLabel icono;

	public static void main(String[] args) {

		SelectFileWindow selectFileWindow = new SelectFileWindow();
	}

	public SelectFileWindow() {
		//Título de la ventana
		setTitle("Ventana de selección de archivo");
		//Instancia de la clase JPanel
		changeFont();
		setContentPane(rootPane);
		//Operación por defecto al cerrar la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Tamaño de la ventana
		pack();
		//Hacer visible la ventana
		setVisible(true);
		//Ventana no redimensionable
		setResizable(false);
		//Centrar la ventana
		setLocationRelativeTo(null);
	}

	private void changeFont() {

		try {
			Font myfont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\FortunerHeavyPersonalUse.otf"))
					.deriveFont(24f);
			gameName.setOpaque(false);
			gameName.setBackground(null);
			gameName.setIcon(new ImageIcon("img\\23.png"));
			gameName.setFont(myfont);
			icono.setIcon(new ImageIcon("img\\17.png"));
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
	}

	private void createUIComponents() {

		rootPane = new BackgrounPanel(new ImageIcon("img\\back_1.png").getImage());
		File load;
		for (int i = 1; i <= 3; i++) {

			load = new File("files\\game" + i + ".dat");
			if (load.exists()) {
				try {
					Player player = FileManager.loadGame(load);
					switch (i) {
						case 1 -> button1 = new BackGroundButton(this, player.toString(), i);
						case 2 -> button2 = new BackGroundButton(this, player.toString(), i);
						case 3 -> button3 = new BackGroundButton(this, player.toString(), i);
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			} else {
				switch (i) {
					case 1 -> button1 = new BackGroundButton(this, "--LIBRE--", i);
					case 2 -> button2 = new BackGroundButton(this, "--LIBRE--", i);
					case 3 -> button3 = new BackGroundButton(this, "--LIBRE--", i);
				}
			}
		}
	}

	public JButton getButton1() {

		return button1;
	}

	public JButton getButton2() {

		return button2;
	}

	public JButton getButton3() {

		return button3;
	}
}
