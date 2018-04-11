package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.view.dialog.EntityChanging;
import by.maribo.web_service.view.dialog.EntityCreating;
import by.maribo.web_service.view.dialog.MethodChanging;
import by.maribo.web_service.view.dialog.MethodCreating;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import by.maribo.web_service.view.styled_component.DarkButton;
import by.maribo.web_service.view.styled_component.Text;

import javax.swing.*;
import java.awt.*;

class WorkPanel {

    private JTextArea textArea;
    private JPanel panel;
    private JButton addButton;
	private JButton changeButton;
	private JButton deleteButton;

	WorkPanel(JBHandlerController controller) {
        panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        textArea = new Text(700, 400).getTextArea();
        panel.add(textArea);

        addButton = new DarkButton("Добавить", false).getButton();
	    addButton.addActionListener(e -> {
            String table = controller.getTableName();
            if (!table.equalsIgnoreCase("method")) {
                new EntityCreating(controller, table);
            } else {
                new MethodCreating(controller);
            }
        });
        panel.add(addButton);

        changeButton = new DarkButton("Изменить", false).getButton();
        changeButton.addActionListener(e -> {
            String table = controller.getTableName();
            EntityLeaf entity = controller.getCurrentEntity();
            MethodLeaf method = controller.getCurrentMethod();
            if (!table.equalsIgnoreCase("method")) {
                new EntityChanging(controller, entity.getTable(), entity.getEntity());
            } else {
                new MethodChanging(controller, method.getMethod());
            }
        });
        panel.add(changeButton);

        deleteButton = new DarkButton("Удалить", false).getButton();
	    deleteButton.addActionListener(e -> {
            String table = controller.getTableName();
            EntityLeaf entity = controller.getCurrentEntity();
            MethodLeaf method = controller.getCurrentMethod();
            if (!table.equalsIgnoreCase("method")) {
                controller.deleteEntity(entity.getEntity(), entity.getTable());
            } else if (method != null) {
                controller.deleteMethod(method.getMethod());
            }
        });
        panel.add(deleteButton);
        panel.setPreferredSize(new Dimension(700, 600));
    }

    JPanel getPanel() {
        return panel;
    }

    void showDescription(String description) {
        textArea.setText(description);
    }

	void updateButtons(boolean add, boolean change, boolean delete) {
		addButton.setVisible(add);
		changeButton.setVisible(change);
		deleteButton.setVisible(delete);
	}
}
