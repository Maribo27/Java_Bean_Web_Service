package by.maribo.java_beans_handbook.method.command;

import by.maribo.java_beans_handbook.method.Method;
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

public class AddingMethod implements Command {
	private static final Logger logger = LoggerFactory.getLogger(AddingMethod.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    MethodService service = ServiceFactory.getInstance().getMethodService();
	    String name = request.getParameter(NAME);
	    String description = request.getParameter(DESCRIPTION);
	    String necessity = request.getParameter(NECESSITY);

	    try {
		    Method method = service.addMethod(name, description, necessity);
		    String address = makeMethodAddress(request, String.valueOf(method.getId()));
		    response.sendRedirect(address);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}