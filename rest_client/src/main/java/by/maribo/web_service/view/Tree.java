package by.maribo.web_service.view;

import by.maribo.web_service.view.styled_component.TreeCellRenderer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.util.Vector;

class Tree {
	private JTree tree;

	Tree(DefaultTreeModel model) {
		tree = new JTree(model);
		tree.setBackground(Color.DARK_GRAY);
		tree.setCellRenderer(new TreeCellRenderer());
		tree.setRootVisible(false);
		tree.setRowHeight(24);
	}

	int[] returnSelection() {
		return tree.getSelectionRows();
	}

	Vector<Integer> returnExpands() {
		Vector<Integer> expandedRows = new Vector<>();
		for (int iterator = 0; iterator < tree.getRowCount(); iterator++)
			if (tree.isExpanded(iterator)) expandedRows.add(iterator);
		return expandedRows;
	}

	void setSelection(int[] treeSelectionModel, Vector<Integer> expandedRows) {
		tree.setSelectionRows(treeSelectionModel);
		for (int iterator = 0; iterator < expandedRows.size(); iterator++) {
			tree.expandRow(expandedRows.elementAt(iterator));
		}
	}

	JTree getTree() {
		return tree;
	}
}
