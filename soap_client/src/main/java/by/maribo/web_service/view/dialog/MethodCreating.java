package by.maribo.web_service.view.dialog;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.styled_component.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;

import static by.maribo.web_service.view.dialog.Sizer.PANEL_WIDTH;

public class MethodCreating {

	private JDialog dialog;
	private JTextArea name;
	private JTextArea description;
	private JComboBox<String> necessity;

	public MethodCreating(JBHandlerController controller) {
		dialog = new JDialog(new JFrame(), "Создать", true);
		Sizer.setSize(dialog);
		JPanel panel = new DarkPanel().getPanel();
		dialog.add(panel);
		panel.setLayout(new FlowLayout());

		name = new Text("Имя", PANEL_WIDTH, 30).getTextArea();
		panel.add(name);

		necessity = new DropdownList().getDropdownList();
		panel.add(necessity);

		description = new Text(PANEL_WIDTH, 440).getTextArea();
		panel.add(new TitledScrollPane(description, "Описание").getScroll());

		JButton decline = new DarkButton("Отклонить", true).getButton();
		decline.addActionListener(e -> dialog.dispose());

		JButton accept = new DarkButton("Принять", true).getButton();
		accept.addActionListener(e -> {
			Method method = new Method();
			method.setName(name.getText());
			method.setDescription(description.getText());
			Object selectedItem = Objects.requireNonNull(necessity.getSelectedItem());
			method.setNecessity(selectedItem.toString());
			controller.addMethod(method);
			dialog.dispose();
		});
		panel.add(decline);
		panel.add(accept);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.pack();
	}
}
