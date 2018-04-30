package by.maribo.web_service.view.dialog;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.view.styled_component.DarkButton;
import by.maribo.web_service.view.styled_component.DarkPanel;
import by.maribo.web_service.view.styled_component.Text;
import by.maribo.web_service.view.styled_component.TitledScrollPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

import static by.maribo.web_service.view.dialog.Sizer.PANEL_WIDTH;
import static by.maribo.web_service.view.dialog.Sizer.setSize;

public class EntityChanging {

	private JDialog dialog;
	private JTextArea name;
	private JTextArea description;

	public EntityChanging(JBHandlerController controller, String type, Entity entity) {
		dialog = new JDialog(new JFrame(), "Изменить", true);
		dialog.setResizable(false);
		setSize(dialog);
		JPanel panel = new DarkPanel().getPanel();
		dialog.add(panel);
		panel.setLayout(new FlowLayout());

		name = new Text("Имя", PANEL_WIDTH, 30).getTextArea();
		name.setText(entity.getName());
		panel.add(name);

		description = new Text(PANEL_WIDTH, 500).getTextArea();
		description.setText(entity.getDescription());
		panel.add(new TitledScrollPane(description, "Описание").getScroll());

		JButton decline = new DarkButton("Отклонить", true).getButton();
		decline.addActionListener(e -> dialog.dispose());
		JButton accept = new DarkButton("Принять", true).getButton();
		accept.addActionListener(e -> {
			entity.setName(name.getText());
			entity.setDescription(description.getText());
			controller.modifyEntity(entity.getId(), entity, type);
			dialog.dispose();
		});
		panel.add(decline);
		panel.add(accept);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.pack();
	}
}