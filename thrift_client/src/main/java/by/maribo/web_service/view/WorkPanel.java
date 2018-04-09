package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import by.maribo.web_service.view.leaf.SimpleLeaf;

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
        textArea.setPreferredSize(new Dimension(700, 500));
        panel.add(textArea);
        addButton = new JButton("Добавить");
        addButton.addActionListener(e -> {
            /*controller.*/
        });
        panel.add(addButton);
        changeButton = new JButton("Изменить");
        changeButton.addActionListener(e -> {
            /*EntityLeaf entity = controller.getCurrentEntity();
            MethodLeaf method = controller.getCurrentMethod();
            SimpleLeaf parent = controller.getCurrentParent();
            if (entity != null) {
                controller.modifyEntity(entity., entity,);
            } else if (method != null) {

            } else {

            }*/
        });
        panel.add(changeButton);
        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(e -> {
            /*controller.*/
        });
        panel.add(deleteButton);
        panel.setPreferredSize(new Dimension(700, 600));
    }

    public JPanel getPanel() {
        return panel;
    }

    public void showDescription(String description) {
        textArea.setText(description);
    }
}
