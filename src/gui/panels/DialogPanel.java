package gui.panels;

import util.managers.FontManager;
import util.managers.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogPanel extends JPanel {

	private final Image img;
	private JPanel backgrounPanel;
	private JTextArea text;
	private JPanel textPanel;
	private JScrollPane scrollPanel;

	public DialogPanel() {

		img = ImageManager.getInstance().getImage("dialogPanel");
		add(backgrounPanel);
		Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
		setPreferredSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		text.setFont(FontManager.getInstance().getFont("Player"));
		text.setForeground(Color.WHITE);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		text.setOpaque(false);
		text.setAutoscrolls(true);
		scrollPanel.setOpaque(false);
		scrollPanel.getViewport().setOpaque(false);
		scrollPanel.setBorder(BorderFactory.createEmptyBorder());
		int delay = 100; // Delay en milisegundos entre cada letra

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		paintBackground(g2d);
	}

	public void paintBackground(Graphics2D g2d) {

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.drawImage(img, 0, 0, null);
	}

	public void setText(String text) {

		this.text.setText(text);
	}

	public JTextArea getText() {

		return text;
	}
}
