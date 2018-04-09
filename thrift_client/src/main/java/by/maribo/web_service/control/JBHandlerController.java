package by.maribo.web_service.control;

import by.maribo.web_service.Client;
import by.maribo.web_service.view.Window;

public class JBHandlerController {
	private Client client;
	private Window window;

	public JBHandlerController() {
		client = new Client();
		window = new Window(this);
	}
	public void startClient() {
		client.connect();
	}
}
