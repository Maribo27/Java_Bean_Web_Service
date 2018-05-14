package by.maribo.java_beans_handbook.entity.command;

import by.maribo.java_beans_handbook.entity.Entity;
import by.maribo.java_beans_handbook.entity.EntityService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.ControlConst.*;

public class EntityInformation implements Command {
	private static final Logger logger = LoggerFactory.getLogger(EntityInformation.class);
	private static final String ENTITY = "entity";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    EntityService service = ServiceFactory.getInstance().getEntityService();
	    String id = request.getParameter(ID);
	    String type = request.getParameter(TYPE);

	    try {
	        Entity entity = service.getEntity(id, type);
		    request.setAttribute(ENTITY, entity);
		    request.setAttribute("method", null);
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX);
		    requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}