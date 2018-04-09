package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;

import javax.swing.*;
import java.awt.*;

public class Window {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private TreePanel treePanel;
	private WorkPanel workPanel;

	public Window(JBHandlerController controller){
		mainFrame = new JFrame("JavaBeans");
		mainPanel = new JPanel();
		mainFrame.setPreferredSize(new Dimension(1000,600));
		mainFrame.setBackground(Color.DARK_GRAY);
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
}
