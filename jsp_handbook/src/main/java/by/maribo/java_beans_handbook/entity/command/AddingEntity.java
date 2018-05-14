package by.maribo.java_beans_handbook.entity.command;

import by.maribo.java_beans_handbook.entity.Entity;
import by.maribo.java_beans_handbook.entity.EntityService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.Util.makeEntityAddress;
import static by.maribo.java_beans_handbook.structure.ControlConst.*;

public class AddingEntity implements Command {
	private static final Logger logger = LoggerFactory.getLogger(AddingEntity.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    EntityService service = ServiceFactory.getInstance().getEntityService();
	    String name = request.getParameter(NAME);
	    String description = request.getParameter(DESCRIPTION);
	    String type = request.getParameter(TYPE);

	    try {
	        Entity entity = service.addEntity(name, description, type);
	        String address = makeEntityAddress(request, String.valueOf(entity.getId()), type);
		    response.sendRedirect(address);
        } catch (ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}