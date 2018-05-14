package by.maribo.java_beans_handbook.method.command;

import by.maribo.java_beans_handbook.method.MethodService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.ControlConst.ID;
import static by.maribo.java_beans_handbook.structure.Util.makeMainAddress;

public class DeletingMethod implements Command {
	private static final Logger logger = LoggerFactory.getLogger(DeletingMethod.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    MethodService service = ServiceFactory.getInstance().getMethodService();
	    String id = request.getParameter(ID);

	    try {
		    service.deleteMethod(id);
		    String address = makeMainAddress(request);
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}