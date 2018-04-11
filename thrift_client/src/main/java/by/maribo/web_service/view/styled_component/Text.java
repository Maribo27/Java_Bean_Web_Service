package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Text {
	private JTextArea textArea;

	public Text(int width, int height) {
		textArea = new JTextArea(height / 30, width / 14);
		textArea.setPreferredSize(new Dimension(width, height));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	public Text(String title, int width, int height) {
		this(width, height);
		textArea.setBorder(new TitledBorder(title));
	}

	public JTextArea getTextArea() {
		return textArea;
	}
}
