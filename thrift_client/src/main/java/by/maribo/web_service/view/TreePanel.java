package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;

import javax.swing.*;
import java.awt.*;

public class TreePanel {

	private JScrollPane pane;
	private JTree tree;

	public TreePanel(JBHandlerController controller) {
		pane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}

	public JScrollPane getPanel() {
		return pane;
	}
}