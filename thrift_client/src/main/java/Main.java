import by.maribo.web_service.control.JBHandlerController;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		try {
			String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(systemLookAndFeelClassName);
		} catch (UnsupportedLookAndFeelException e) {
			System.err.println("Данное оформление не поддерживается на данной платформе");
		} catch (Exception e) {
			System.err.println("Невозможно применить данную тему оформления");
		}
		JBHandlerController controller = new JBHandlerController();
		controller.startClient();
	}
}