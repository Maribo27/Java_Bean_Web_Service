package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import java.awt.*;

public class DarkButton {
	private JButton button;

	public DarkButton(String name, boolean visible) {
		button = new JButton(name);
		button.setForeground(Color.DARK_GRAY);
		button.setBackground(Color.LIGHT_GRAY);
		button.setFont(new Font("Arial", Font.PLAIN, 22));
		button.setVisible(visible);
	}

	public JButton getButton() {
		return button;
	}
}