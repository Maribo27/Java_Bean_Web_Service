package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;

import javax.swing.*;
import java.awt.*;

public class WorkPanel {

	private JTextArea textArea;
	private JPanel panel;
	private JButton addButton;
	private JButton changeButton;
	private JButton deleteButton;

	public WorkPanel(JBHandlerController controller) {
		panel = new JPanel();
		textArea = new JTextArea();
		panel.add(textArea);
		addButton = new JButton("Добавить");
		panel.add(addButton);
		changeButton = new JButton("Изменить");
		panel.add(changeButton);
		deleteButton = new JButton("Удалить");
		panel.add(deleteButton);
	}

	public JPanel getPanel() {
		return panel;
	}
}
