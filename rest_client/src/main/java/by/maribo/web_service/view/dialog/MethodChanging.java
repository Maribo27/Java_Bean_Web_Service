package by.maribo.web_service.view.dialog;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.styled_component.*;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

import static by.maribo.web_service.view.dialog.Sizer.PANEL_WIDTH;

public class MethodChanging {

	private JDialog dialog;
	private JTextArea name;
	private JTextArea description;
	private JComboBox<String> necessity;

	public MethodChanging(JBHandlerController controller, Method method) {
		dialog = new JDialog(new JFrame(), "Изменить", true);
		Sizer.setSize(dialog);
		JPanel panel = new DarkPanel().getPanel();
		dialog.add(panel);
		panel.setLayout(new FlowLayout());

		name = new Text("Имя", PANEL_WIDTH, 30).getTextArea();
		name.setText(method.getName());
		panel.add(name);

		necessity = new DropdownList().getDropdownList();
		panel.add(necessity);

		description = new Text(PANEL_WIDTH, 500).getTextArea();
		description.setText(method.getDescription());
		panel.add(new TitledScrollPane(description, "Описание").getScroll());
		JButton decline = new DarkButton("Отклонить", true).getButton();
		decline.addActionListener(e -> dialog.dispose());

		JButton accept = new DarkButton("Принять", true).getButton();
		accept.addActionListener(e -> {
			method.setName(name.getText());
			method.setDescription(description.getText());
			method.setNecessity(Objects.requireNonNull(necessity.getSelectedItem()).toString());
			controller.modifyMethod(method);
			dialog.dispose();
		});
		panel.add(decline);
		panel.add(accept);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.pack();
	}
}
