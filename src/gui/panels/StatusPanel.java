package gui.panels;

import player.Player;
import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class StatusPanel extends JPanel {

	private final Image img;
	private final ActionsPanel actionsPanel;
	private final int tabIndex;
	private final ImageIcon activeIcon;
	private final ImageIcon inactiveIcon;
	private final Player player;

	public StatusPanel(ActionsPanel actionsPanel, int tabIndex, Player player) {

		this.player = player;
		ImageManager imageManager = ImageManager.getInstance();
		this.img = imageManager.getImage("statusPanel");
		this.tabIndex = tabIndex;
		this.activeIcon = new ImageIcon(imageManager.getImage("statusTabActive"));
		this.inactiveIcon = new ImageIcon(imageManager.getImage("statusTabInactive"));
		this.actionsPanel = actionsPanel;
		actionsPanel.addTab("Status", this);
		actionsPanel.setTabIcon(tabIndex, isActive() ? activeIcon : inactiveIcon);
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {

				actionsPanel.setTabIcon(tabIndex, activeIcon);
			}

			@Override
			public void componentHidden(ComponentEvent e) {

				actionsPanel.setTabIcon(tabIndex, inactiveIcon);
			}
		});
	}

	private boolean isActive() {

		return actionsPanel.getSelectedIndex() == tabIndex;
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageManager imageManager = ImageManager.getInstance();
		Image image;
		String message;
		int textPositionX = 70;
		int textPositionY;
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);
		g2d.setColor(Color.BLACK);
		g2d.setFont(FontManager.getInstance().getFont("Player Status"));
		//Primer Columna
		//Exp
		image = imageManager.getImage("expHolder");
		g2d.translate(50, 10);
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = String.format("EXP: %d", player.getExperience());
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Ataque
		image = imageManager.getImage("attackHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalAttack();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Defensa
		image = imageManager.getImage("defenseHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalDefense();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		//Segunda Columna
		g2d.translate(image.getWidth(null) + 15 - textPositionX,
				-textPositionY - 2 * (image.getHeight(null) + 5));
		//Oro
		image = imageManager.getImage("goldHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = String.format("ORO: %d", player.getGold());
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Inteligencia
		image = imageManager.getImage("intelligenceHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalIntelligence();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Resistencia
		image = imageManager.getImage("resHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalResistance();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		//Tercera Columna
		g2d.translate(image.getWidth(null) + 15 - textPositionX,
				-textPositionY - 2 * (image.getHeight(null) + 5));
		//Suerte
		image = imageManager.getImage("luckHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalLuck();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Habilidad
		image = imageManager.getImage("dexterityHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalDexterity();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null) + 5);
		//Velocidad
		image = imageManager.getImage("velHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getTotalSpeed();
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		//Cuarta Columna
		g2d.translate(image.getWidth(null) + 15 - textPositionX,
				-textPositionY - 2 * (image.getHeight(null) + 5));
		//Cabeza
		image = imageManager.getImage("headArmorHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getHeadArmor() != null ? player.getHeadArmor().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null));
		//Pecho
		image = imageManager.getImage("chestArmorHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getChestArmor() != null ? player.getChestArmor().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null));
		//Piernas
		image = imageManager.getImage("legArmorHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getLegArmor() != null ? player.getLegArmor().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		//Quinta Columna
		g2d.translate(image.getWidth(null) + 15 - textPositionX,
				-textPositionY - (2 * (image.getHeight(null)))-1.5);
		//Pies
		image = imageManager.getImage("feetArmorHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getFootArmor() != null ? player.getFootArmor().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null));
		//Manos
		image = imageManager.getImage("handArmorHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getHandArmor() != null ? player.getHandArmor().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
		g2d.translate(-textPositionX, -textPositionY + image.getHeight(null));
		//Arma
		image = imageManager.getImage("weaponHolder");
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null), null);
		message = player.getWeapon() != null ? player.getWeapon().getName() : "Ninguno";
		textPositionY = image.getHeight(null) / 2 + g2d.getFontMetrics(g2d.getFont()).getHeight() / 2 - 10;
		g2d.translate(textPositionX, textPositionY);
		g2d.drawString(message, 0, 0);
	}
}
