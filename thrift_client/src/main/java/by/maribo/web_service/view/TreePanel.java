package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class TreePanel {

	private JPanel panel;
	private JScrollPane pane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private JBHandlerController controller;

	public TreePanel(JBHandlerController controller) {
	    this.controller = controller;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("JavaBean Handbook", true);

		java.util.List<Entity> entities = controller.getAllEntities("type");

		DefaultMutableTreeNode javaBean = new DefaultMutableTreeNode(entities.get(0).getName(), true);
		root.add(javaBean);

		DefaultMutableTreeNode method = new DefaultMutableTreeNode("Методы", true);
		javaBean.add(method);
		createLeaves(method, "method");
		DefaultMutableTreeNode property = new DefaultMutableTreeNode("Свойства", true);
		javaBean.add(property);
		createLeaves(property, "property");
		DefaultMutableTreeNode rule = new DefaultMutableTreeNode("Общее", true);
		javaBean.add(rule);
		createLeaves(rule, "rule");

        DefaultMutableTreeNode enterpriseJavaBean = new DefaultMutableTreeNode(entities.get(1).getName(), true);
        root.add(enterpriseJavaBean);

		DefaultMutableTreeNode info = new DefaultMutableTreeNode("Общее", true);
		enterpriseJavaBean.add(info);
		createLeaves(info, "ejb_info");
		DefaultMutableTreeNode type = new DefaultMutableTreeNode("Типы", true);
		enterpriseJavaBean.add(type);
		createLeaves(type, "ejb_type");
		DefaultMutableTreeNode role = new DefaultMutableTreeNode("Роли", true);
		enterpriseJavaBean.add(role);
		createLeaves(role, "role");

		treeModel = new DefaultTreeModel(root, true);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);

		tree.addTreeSelectionListener(e -> {
			DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
		});

		pane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setMaximumSize(new Dimension(200, 600));
		pane.setMinimumSize(new Dimension(200, 600));
		pane.setPreferredSize(new Dimension(200, 600));
		panel.add(pane);
	}

	public JPanel getPanel() {
		return panel;
	}

	private void createLeaves(DefaultMutableTreeNode rootNode, String type) {
        java.util.List<Entity> entities = controller.getAllEntities(type);
        for (Entity entity : entities) {
	        DefaultMutableTreeNode node = new DefaultMutableTreeNode(entity.getName(), false);
	        rootNode.add(node);
        }
    }
}