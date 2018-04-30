package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import java.awt.*;

public class Pane {
	private static final int WIDTH = 200;
	private static final int HEIGHT = 600;
	private JScrollPane pane;

	public Pane(JTree tree) {
		pane = new JScrollPane(tree, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBackground(Color.DARK_GRAY);
		pane.getHorizontalScrollBar().setBackground(Color.DARK_GRAY);
		pane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
		pane.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		pane.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		pane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	public JScrollPane getPane() {
		return pane;
	}
}
