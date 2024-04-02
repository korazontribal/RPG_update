package gui.labels;

import player.Player;
import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;

public class MpLabel extends JLabel {

	private final Image image;
	private final String text;

	public MpLabel(Player player) {

		ImageManager imageManager = ImageManager.getInstance();
		text = String.format("%d/%d", player.getMp(), player.getMaxMp());
		double mpPercentage = (double) player.getMp() / player.getMaxMp();
		Color color;
		if (mpPercentage >= .80) {
			image = imageManager.getImage("mp100");
			color = new Color(0, 0, 0, 255);
		} else if (mpPercentage > 0.6) {
			image = imageManager.getImage("mp80");
			color = new Color(0, 0, 0, 255);
		} else if (mpPercentage > 0.4) {
			image = imageManager.getImage("mp60");
			color = new Color(109, 109, 109, 255);
		} else if (mpPercentage > 0.2) {
			image = imageManager.getImage("mp40");
			color = new Color(109, 109, 109, 255);
		} else if (mpPercentage > 0) {
			image = imageManager.getImage("mp20");
			color = new Color(109, 109, 109, 255);
		} else {
			image = imageManager.getImage("mp0");
			color = new Color(255, 255, 255, 255);
		}
		Font font = FontManager.getInstance().getFont("Player");
		Dimension size = new Dimension(image.getWidth(null), image.getHeight(null) + 10);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setSize(size);
		setForeground(color);
		setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		setFont(font);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(image, 0, 10, null);
		int textPositionY = image.getHeight(null) / 2 + 8 + g2d.getFontMetrics().getHeight() / 4;
		int textPositionX = ((image.getWidth(null)-37) / 2) - (g2d.getFontMetrics().stringWidth(text) / 2);
		g2d.translate(textPositionX, textPositionY);
		BasicStroke contorno = new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
		g2d.setStroke(contorno);
		g2d.drawString(text, 0, 0);
	}
}