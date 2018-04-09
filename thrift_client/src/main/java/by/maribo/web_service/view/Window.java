package by.maribo.web_service.view;

import by.maribo.web_service.control.JBHandlerController;

import javax.swing.*;
import java.awt.*;

public class Window {
	private JFrame mainFrame = new JFrame("POP3 Client");
	private TreePanel treePanel;
	private WorkPanel workPanel;

	public Window(JBHandlerController controller){
		mainFrame.setLayout(new FlowLayout());
		mainFrame.setPreferredSize(new Dimension(1000,600));
		mainFrame.setBackground(Color.DARK_GRAY);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		treePanel = new TreePanel(controller);
		mainFrame.add(treePanel.getPanel());
		workPanel = new WorkPanel(controller);
		mainFrame.add(workPanel.getPanel());

		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
	}
}
