package by.maribo.web_service.control;

import by.maribo.web_service.Client;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.Window;

import java.util.List;

public class JBHandlerController {
	private Client client;
	private Window window;

	public JBHandlerController() {
	}
	public void startClient() {
		client = new Client();
		client.connect();
		window = new Window(this);
	}

	public List<Entity> getAllEntities(String tableName) {
		return client.getAllEntities(tableName);
	}

    public List<Method> getAllMethods() {
        return client.getAllMethods();
    }

    public void addMethod(Method method) {
    }

    public void deleteMethod(Method method){
    }

    public void modifyMethod(int id, Method method){
    }

    public void addEntity(Entity entity, String entityType){
    }

    public void deleteEntity(Entity entity, String entityType){
    }

    public void modifyEntity(int id, Entity entity, String entityType){
    }

    public void showDescription(String description) {
        window.showDescription(description);
    }
}
