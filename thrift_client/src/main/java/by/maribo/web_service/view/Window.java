package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;
import by.maribo.web_service.view.styled_component.Image;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class Window {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private TreePanel treePanel;
	private WorkPanel workPanel;
	private JBHandlerController controller;

	public Window(JBHandlerController controller){
		this.controller = controller;
		mainFrame = new JFrame("JavaBeans");
		ImageIcon imageIcon = new Image("icon.png").getIcon();
		mainFrame.setIconImage(imageIcon.getImage());
		mainPanel = new JPanel();
		mainFrame.setPreferredSize(new Dimension(1000,600));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.add(mainPanel);

		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

		treePanel = new TreePanel(controller);
		mainPanel.add(treePanel.getPanel());
		workPanel = new WorkPanel(controller);
		mainPanel.add(workPanel.getPanel());

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
	}

	public void showDescription(String description) {
		workPanel.showDescription(description);
		mainFrame.repaint();
	}

	public void update() {
		mainPanel.remove(treePanel.getPanel());

		int[] treeSelectionModel = treePanel.returnSelection();
		Vector<Integer> expandedRows = treePanel.returnExpands();
		treePanel = new TreePanel(controller);
		treePanel.setSelection(treeSelectionModel, expandedRows);
		mainPanel.add(treePanel.getPanel());
		mainPanel.remove(workPanel.getPanel());
		workPanel = new WorkPanel(controller);
		mainPanel.add(workPanel.getPanel());
		mainFrame.revalidate();
		mainFrame.repaint();
	}

	public void updateButtons(boolean add, boolean change, boolean delete) {
		workPanel.updateButtons(add, change, delete);
	}
}
