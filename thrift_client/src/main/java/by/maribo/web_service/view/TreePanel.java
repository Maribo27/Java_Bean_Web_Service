package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import by.maribo.web_service.view.leaf.SimpleLeaf;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
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
    private List<SimpleLeaf> allParents;
    private EntityLeaf currentEntity;
    private MethodLeaf currentMethod;


    public TreePanel(JBHandlerController controller) {
        this.controller = controller;
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        allEntities = new ArrayList<>();
        allMethods = new ArrayList<>();
        allParents = new ArrayList<>();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("JavaBean Handbook", true);

        java.util.List<Entity> entities = controller.getAllEntities("type");

        EntityLeaf javaBean = new EntityLeaf(entities.get(0), true, "type");
        allEntities.add(javaBean);
        DefaultMutableTreeNode javaBeanNode = javaBean.getNode();
        root.add(javaBeanNode);
        DefaultMutableTreeNode method = new DefaultMutableTreeNode("Методы", true);
        javaBeanNode.add(method);
        createMethods(method);
        createParentLeaves(javaBeanNode, "Свойства", "property");
        createParentLeaves(javaBeanNode, "Общее", "rule");


        EntityLeaf enterpriseJavaBean = new EntityLeaf(entities.get(1), true, "type");
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
            selectEntity(controller, e);
            controller.update();
        });

        pane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setMaximumSize(new Dimension(200, 600));
        pane.setMinimumSize(new Dimension(200, 600));
        pane.setPreferredSize(new Dimension(200, 600));
        panel.add(pane);
    }

    private void selectEntity(JBHandlerController controller, TreeSelectionEvent e) {
        String selectedNode = e.getPath().getLastPathComponent().toString();
        for (EntityLeaf leaf : allEntities) {
            String nodeFromList = leaf.getName();
            boolean selected = nodeFromList.equals(selectedNode);
            if (selected) {
                controller.showDescription(leaf.getDescription());
                currentEntity = leaf;
                currentMethod = null;
                return;
            }
        }
        for (MethodLeaf leaf : allMethods) {
            String nodeFromList = leaf.getName();
            boolean selected = nodeFromList.equals(selectedNode);
            if (selected) {
                controller.showDescription(leaf.getDescription());
                currentEntity = null;
                currentMethod = leaf;
	            return;
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    private void createParentLeaves(DefaultMutableTreeNode rootNode, String name, String type) {
        SimpleLeaf leaf = new SimpleLeaf(name, type);
        allParents.add(leaf);
        DefaultMutableTreeNode node = leaf.getNode();
        rootNode.add(node);
        createLeaves(node, type);
    }

    private void createLeaves(DefaultMutableTreeNode rootNode, String type) {
        java.util.List<Entity> entities = controller.getAllEntities(type);
        for (Entity entity : entities) {
            EntityLeaf leaf = new EntityLeaf(entity, false, type);
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

    public EntityLeaf getCurrentEntity() {
        return currentEntity;
    }

    public MethodLeaf getCurrentMethod() {
        return currentMethod;
    }
}