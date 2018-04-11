import by.maribo.web_service.control.JBHandlerController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) {
		try {
			String systemLookAndFeelClassName = UIManager.getSystemLookAndFeelClassName();
			UIManager.setLookAndFeel(systemLookAndFeelClassName);
		} catch (Exception e) {
			logger.info("Cannot apply Look and Feel theme");
		}
		JBHandlerController controller = new JBHandlerController();
		controller.startClient();
	}
}