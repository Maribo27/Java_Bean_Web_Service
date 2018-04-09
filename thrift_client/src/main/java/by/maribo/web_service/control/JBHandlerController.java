package by.maribo.web_service.control;

import by.maribo.web_service.Client;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.view.Window;

import java.util.List;

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

	public List<Entity> getAllEntities(String tableName) {
		return client.getAllEntities(tableName);
	}
}
