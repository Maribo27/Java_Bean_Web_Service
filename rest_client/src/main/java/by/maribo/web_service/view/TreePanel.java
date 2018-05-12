package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import by.maribo.web_service.view.leaf.SimpleLeaf;
import by.maribo.web_service.view.styled_component.Pane;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Vector;

class TreePanel {

    private JPanel panel;
    private RootNode rootNode;
    private Tree treeHandler;
    private JTree tree;
    private TreeNodeLevel level;

    TreePanel(JBHandlerController controller) {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
	    rootNode = new RootNode(controller);
	    DefaultTreeModel treeModel = new DefaultTreeModel(rootNode.getNode(), true);
	    treeHandler = new Tree(treeModel);
	    tree = treeHandler.getTree();
        tree.addTreeSelectionListener(e -> {
            selectEntity(controller, e);
            controller.updateButtons(level);
        });
        panel.add(new Pane(tree).getPane());
    }


	private void selectEntity(JBHandlerController controller, TreeSelectionEvent e) {
	    Object node = e.getPath().getLastPathComponent();
	    String selectedNode = node.toString();
        for (EntityLeaf leaf : rootNode.getAllEntities()) {
            String nodeFromList = leaf.getName();
            boolean selected = nodeFromList.equals(selectedNode);
            if (selected) {
                controller.showDescription(leaf.getDescription());
                controller.changeState(leaf, null, leaf.getTable());
                level = tree.getModel().isLeaf(node) ? TreeNodeLevel.ENTITY : TreeNodeLevel.ROOT;
                return;
            }
        }
        for (MethodLeaf leaf : rootNode.getAllMethods()) {
            String nodeFromList = leaf.getName();
            boolean selected = nodeFromList.equals(selectedNode);
            if (selected) {
                controller.showDescription(leaf.getDescription());
	            controller.changeState(null, leaf, "method");
                level = TreeNodeLevel.ENTITY;
	            return;
            }
        }
        for (SimpleLeaf leaf : rootNode.getAllParents()) {
            String nodeFromList = leaf.getName();
            boolean selected = nodeFromList.equals(selectedNode);
            if (selected) {
	            controller.changeState(null, null, leaf.getTable());
	            level = TreeNodeLevel.TYPE;
                return;
            }
        }
    }

    JPanel getPanel() {
        return panel;
    }

    int[] returnSelection(){
        return treeHandler.returnSelection();
    }

    Vector<Integer> returnExpands(){
	    return treeHandler.returnExpands();
    }

    void setSelection(int[] treeSelectionModel, Vector<Integer> expandedRows) {
	    treeHandler.setSelection(treeSelectionModel, expandedRows);
    }
}