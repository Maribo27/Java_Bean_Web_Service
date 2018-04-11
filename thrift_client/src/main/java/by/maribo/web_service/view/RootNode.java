package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import by.maribo.web_service.view.leaf.SimpleLeaf;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

class RootNode {

	private List<EntityLeaf> allEntities;
	private List<MethodLeaf> allMethods;
	private List<SimpleLeaf> allParents;
	private DefaultMutableTreeNode root;
	private JBHandlerController controller;

	RootNode(JBHandlerController controller) {
		this.controller = controller;
		allEntities = new ArrayList<>();
		allMethods = new ArrayList<>();
		allParents = new ArrayList<>();

		root = new DefaultMutableTreeNode("JavaBean Handbook", true);

		List<Entity> entities = controller.getAllEntities("type");

		EntityLeaf javaBean = new EntityLeaf(entities.get(0), true, "type");
		allEntities.add(javaBean);
		DefaultMutableTreeNode javaBeanNode = javaBean.getNode();
		root.add(javaBeanNode);

		SimpleLeaf leaf = new SimpleLeaf("Методы");
		leaf.setTable("method");
		allParents.add(leaf);
		DefaultMutableTreeNode node = leaf.getNode();
		javaBeanNode.add(node);
		createMethods(node);

		createParentLeaves(javaBeanNode, "Свойства", "property");
		createParentLeaves(javaBeanNode, "Общее", "rule");


		EntityLeaf enterpriseJavaBean = new EntityLeaf(entities.get(1), true, "type");
		allEntities.add(enterpriseJavaBean);
		DefaultMutableTreeNode enterpriseJavaBeanNode = enterpriseJavaBean.getNode();
		root.add(enterpriseJavaBeanNode);
		createParentLeaves(enterpriseJavaBeanNode, "Общее", "ejb_info");
		createParentLeaves(enterpriseJavaBeanNode, "Типы", "ejb_type");
		createParentLeaves(enterpriseJavaBeanNode, "Роли", "role");
	}

	private void createParentLeaves(DefaultMutableTreeNode rootNode, String name, String type) {
		SimpleLeaf leaf = new SimpleLeaf(name);
		leaf.setTable(type);
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

	List<EntityLeaf> getAllEntities() {
		return allEntities;
	}

	List<MethodLeaf> getAllMethods() {
		return allMethods;
	}

	List<SimpleLeaf> getAllParents() {
		return allParents;
	}

	DefaultMutableTreeNode getNode() {
		return root;
	}
}
