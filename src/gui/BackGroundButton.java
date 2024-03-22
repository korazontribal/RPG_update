package gui;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BackGroundButton extends JButton {

	private final String text;
	private final int slot;
	private final Font myfont;
	private final SelectFileWindow window;

	public BackGroundButton(SelectFileWindow window, String text, int slot) {

		this.window = window;
		this.text = text;
		this.slot = slot;
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
		setOpaque(false);
		Dimension size = new Dimension(250, 32);
		setSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		setMinimumSize(size);
		setVerticalAlignment(SwingConstants.CENTER);
		setHorizontalAlignment(SwingConstants.CENTER);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		try {
			this.myfont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts\\game_over.ttf"))
					.deriveFont(60f);
		} catch (FontFormatException | IOException e) {
			throw new RuntimeException(e);
		}
		addActionListener(e -> {

			window.dispose();
			new Game(slot);
		});
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageIcon imagen = new ImageIcon("img\\23.png");
		g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), null);
		g.setColor(Color.BLACK);
		g.setFont(myfont);
		g.drawString(text, ((getWidth() / 2) - (g.getFontMetrics(myfont).stringWidth(text) / 2)), (getHeight() / 2) + 7);
	}

	@Override
	public String getText() {

		return text;
	}

	public int getSlot() {

		return slot;
	}

	public Font getMyfont() {

		return myfont;
	}

	public SelectFileWindow getWindow() {

		return window;
	}
}
