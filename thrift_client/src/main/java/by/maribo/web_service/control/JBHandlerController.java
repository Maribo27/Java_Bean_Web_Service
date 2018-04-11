package by.maribo.web_service.control;

import by.maribo.web_service.Client;
import by.maribo.web_service.ClientConnectionException;
import by.maribo.web_service.entity.Entity;
import by.maribo.web_service.entity.Method;
import by.maribo.web_service.view.TreeNodeLevel;
import by.maribo.web_service.view.Window;
import by.maribo.web_service.view.leaf.EntityLeaf;
import by.maribo.web_service.view.leaf.MethodLeaf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JBHandlerController {
	private static final Logger logger = LoggerFactory.getLogger(JBHandlerController.class);
	private Client client;
	private Window window;

	private String tableName;
	private EntityLeaf currentEntity;
	private MethodLeaf currentMethod;


	public JBHandlerController() {
	}
	public void startClient() {
		try {
			client = new Client();
			client.connect();
			window = new Window(this);
		} catch (ClientConnectionException e) {
			logger.error(e.getMessage());
			System.exit(0);
		}
	}

	public List<Entity> getAllEntities(String tableName) {
		return client.getAllEntities(tableName);
	}

    public List<Method> getAllMethods() {
        return client.getAllMethods();
    }

    public void addMethod(Method method) {
	    try {
		    client.addMethod(method);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void deleteMethod(Method method){
	    try {
		    client.deleteMethod(method);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void modifyMethod(int id, Method method){
	    try {
		    client.modifyMethod(id, method);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void addEntity(Entity entity, String entityType){
	    try {
		    client.addEntity(entity, entityType);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void deleteEntity(Entity entity, String entityType){
	    try {
		    client.deleteEntity(entity, entityType);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void modifyEntity(int id, Entity entity, String entityType){
	    try {
		    client.modifyEntity(id, entity, entityType);
	    } catch (Exception e) {
		    logger.error(e.getMessage());
	    }
	    update();
    }

    public void showDescription(String description) {
        window.showDescription(description);
    }

	private void update() {
		window.update();
	}

	public String getTableName() {
		return tableName;
	}

	public EntityLeaf getCurrentEntity() {
		return currentEntity;
	}

	public MethodLeaf getCurrentMethod() {
		return currentMethod;
	}

	public void changeState(EntityLeaf currentEntity, MethodLeaf currentMethod, String tableName) {
		this.tableName = tableName;
		this.currentEntity = currentEntity;
		this.currentMethod = currentMethod;
	}

	public void updateButtons(TreeNodeLevel level) {
		boolean add = level.equals(TreeNodeLevel.TYPE);
		boolean change = !level.equals(TreeNodeLevel.TYPE);
		boolean delete = level.equals(TreeNodeLevel.ENTITY);
		window.updateButtons(add, change, delete);
	}
}
