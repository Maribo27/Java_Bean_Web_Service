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

		DefaultMutableTreeNode javaBean = new DefaultMutableTreeNode(entities.get(0), true);
		root.add(javaBean);
        DefaultMutableTreeNode property = new DefaultMutableTreeNode(entities.get(0), true);
        root.add(javaBean);

        DefaultMutableTreeNode enterpriseJavaBean = new DefaultMutableTreeNode(entities.get(1), true);
        root.add(enterpriseJavaBean);

		treeModel = new DefaultTreeModel(root, true);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);

		pane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panel.add(pane);
		panel.setPreferredSize(new Dimension(700,700));
	}

	public JPanel getPanel() {
		return panel;
	}

	private void createLeaves(DefaultMutableTreeNode rootNode, String type) {
        java.util.List<Entity> entities = controller.getAllEntities(type);
    }
}