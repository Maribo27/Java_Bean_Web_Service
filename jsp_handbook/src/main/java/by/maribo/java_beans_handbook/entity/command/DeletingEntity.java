package by.maribo.java_beans_handbook.entity.command;

import by.maribo.java_beans_handbook.entity.EntityService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.Util.makeMainAddress;
import static by.maribo.java_beans_handbook.structure.ControlConst.ID;
import static by.maribo.java_beans_handbook.structure.ControlConst.TYPE;

public class DeletingEntity implements Command {
	private static final Logger logger = LoggerFactory.getLogger(DeletingEntity.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    EntityService service = ServiceFactory.getInstance().getEntityService();
	    String id = request.getParameter(ID);
	    String type = request.getParameter(TYPE);

	    try {
		    service.deleteEntity(id, type);
		    String address = makeMainAddress(request);
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}