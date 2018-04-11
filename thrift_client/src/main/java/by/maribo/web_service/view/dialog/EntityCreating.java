package by.maribo.web_service.view.dialog;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.view.styled_component.DarkButton;
import by.maribo.web_service.view.styled_component.DarkPanel;
import by.maribo.web_service.view.styled_component.Text;
import by.maribo.web_service.view.styled_component.TitledScrollPane;
import com.sun.java.swing.plaf.windows.WindowsScrollBarUI;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.plaf.multi.MultiScrollBarUI;
import java.awt.*;

import static by.maribo.web_service.view.dialog.Sizer.PANEL_WIDTH;

public class EntityCreating {

	private JDialog dialog;
	private JTextArea name;
	private JTextArea description;

	public EntityCreating(JBHandlerController controller, String type) {
		dialog = new JDialog(new JFrame(), "Создать", true);
		Sizer.setSize(dialog);
		JPanel panel = new DarkPanel().getPanel();
		dialog.add(panel);
		panel.setLayout(new FlowLayout());

		name = new Text("Имя", PANEL_WIDTH, 30).getTextArea();
		panel.add(name);

		description = new Text(PANEL_WIDTH, 500).getTextArea();
		panel.add(new TitledScrollPane(description, "Описание").getScroll());

		JButton decline = new DarkButton("Отклонить", true).getButton();
		decline.addActionListener(e -> dialog.dispose());

		JButton accept = new DarkButton("Принять", true).getButton();
		accept.addActionListener(e -> {
			Entity entity = new Entity();
			entity.setName(name.getText());
			entity.setDescription(description.getText());
			controller.addEntity(entity, type);
			dialog.dispose();
		});
		panel.add(decline);
		panel.add(accept);
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
		dialog.pack();
	}
}