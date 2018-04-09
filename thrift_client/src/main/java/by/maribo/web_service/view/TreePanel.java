package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TreePanel {

	private JPanel panel;
	private JScrollPane pane;
	private JTree tree;
	private DefaultTreeModel treeModel;
	private JBHandlerController controller;
    private List<EntityLeaf> allEntities;
    private List<MethodLeaf> allMethods;


    public TreePanel(JBHandlerController controller) {
	    this.controller = controller;
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		allEntities = new ArrayList<>();
		allMethods = new ArrayList<>();

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("JavaBean Handbook", true);

		java.util.List<Entity> entities = controller.getAllEntities("type");

		EntityLeaf javaBean = new EntityLeaf(entities.get(0), true);
        allEntities.add(javaBean);
		DefaultMutableTreeNode javaBeanNode = javaBean.getNode();
        root.add(javaBeanNode);
		DefaultMutableTreeNode method = new DefaultMutableTreeNode("Методы", true);
        javaBeanNode.add(method);
		createMethods(method);
        createParentLeaves(javaBeanNode, "Свойства", "property");
        createParentLeaves(javaBeanNode, "Общее", "rule");


        EntityLeaf enterpriseJavaBean = new EntityLeaf(entities.get(1), true);
        allEntities.add(enterpriseJavaBean);
        DefaultMutableTreeNode enterpriseJavaBeanNode = enterpriseJavaBean.getNode();
        root.add(enterpriseJavaBeanNode);
        createParentLeaves(enterpriseJavaBeanNode, "Общее", "ejb_info");
        createParentLeaves(enterpriseJavaBeanNode, "Типы", "ejb_type");
        createParentLeaves(enterpriseJavaBeanNode, "Роли", "role");

		treeModel = new DefaultTreeModel(root, true);
		tree = new JTree(treeModel);
		tree.setRootVisible(false);

		tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) e.getPath().getLastPathComponent();
            for (EntityLeaf leaf : allEntities) {
                DefaultMutableTreeNode nodeFromList = leaf.getNode();
                boolean selected = nodeFromList.equals(selectedNode);
                if (selected) {
                    controller.showDescription(leaf.getDescription());
                    break;
                }
            }
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

    private void createParentLeaves(DefaultMutableTreeNode rootNode, String name, String type) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(name, true);
        rootNode.add(node);
        createLeaves(node, type);
    }

	private void createLeaves(DefaultMutableTreeNode rootNode, String type) {
        java.util.List<Entity> entities = controller.getAllEntities(type);
        for (Entity entity : entities) {
	        EntityLeaf leaf = new EntityLeaf(entity, false);
            allEntities.add(leaf);
            rootNode.add(leaf.getNode());
        }
    }

    private void createMethods(DefaultMutableTreeNode rootNode) {
        java.util.List<Method> methods = controller.getAllMethods();
        for (Method method : methods) {
            MethodLeaf leaf = new MethodLeaf(method, false);
            allMethods.add(leaf);
            rootNode.add(leaf.getNode());
        }
    }
}