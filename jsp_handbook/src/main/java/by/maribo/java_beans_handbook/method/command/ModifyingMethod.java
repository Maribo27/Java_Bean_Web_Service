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

import static by.maribo.java_beans_handbook.structure.ControlConst.*;
import static by.maribo.java_beans_handbook.structure.Util.makeMethodAddress;

public class ModifyingMethod implements Command {
	private static final Logger logger = LoggerFactory.getLogger(ModifyingMethod.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    MethodService service = ServiceFactory.getInstance().getMethodService();
	    String id = request.getParameter(ID);
	    String name = request.getParameter(NAME);
	    String description = request.getParameter(DESCRIPTION);
	    String necessity = request.getParameter(NECESSITY);

	    try {
		    service.modifyMethod(id, name, description, necessity);
		    String address = makeMethodAddress(request, id);
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}