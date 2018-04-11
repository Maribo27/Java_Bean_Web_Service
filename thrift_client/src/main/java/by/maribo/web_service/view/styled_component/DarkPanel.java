package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import java.awt.*;

public class DarkPanel {
	private JPanel panel;

	public DarkPanel() {
		panel = new JPanel();
		panel.setForeground(Color.DARK_GRAY);
	}

	public JPanel getPanel() {
		return panel;
	}
}