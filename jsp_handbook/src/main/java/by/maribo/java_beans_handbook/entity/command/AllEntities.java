package by.maribo.java_beans_handbook.entity.command;

import by.maribo.java_beans_handbook.entity.Entity;
import by.maribo.java_beans_handbook.entity.EntityNotFoundException;
import by.maribo.java_beans_handbook.entity.EntityService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.maribo.java_beans_handbook.entity.Entity.Type.*;

public class AllEntities implements Command {
	private static final Logger logger = LoggerFactory.getLogger(AllEntities.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    EntityService service = ServiceFactory.getInstance().getEntityService();
	    try {
	        List<Entity> entities = service.getAllEntities(EJB_INFO.name());
	        request.setAttribute(EJB_INFO.name(), entities);
		    entities = service.getAllEntities(EJB_TYPE.name());
		    request.setAttribute(EJB_TYPE.name(), entities);
		    entities = service.getAllEntities(PROPERTY.name());
		    request.setAttribute(PROPERTY.name(), entities);
		    entities = service.getAllEntities(ROLE.name());
		    request.setAttribute(ROLE.name(), entities);
		    entities = service.getAllEntities(RULE.name());
		    request.setAttribute(RULE.name(), entities);
		    entities = service.getAllEntities(TYPE.name());
		    request.setAttribute(TYPE.name(), entities);
        } catch (EntityNotFoundException | ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}