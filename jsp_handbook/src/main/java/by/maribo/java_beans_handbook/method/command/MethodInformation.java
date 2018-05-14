package by.maribo.java_beans_handbook.method.command;

import by.maribo.java_beans_handbook.method.Method;
import by.maribo.java_beans_handbook.method.MethodService;
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

public class MethodInformation implements Command {
	private static final Logger logger = LoggerFactory.getLogger(MethodInformation.class);
	private static final String METHOD = "method";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    MethodService service = ServiceFactory.getInstance().getMethodService();
	    String id = request.getParameter(ID);

	    try {
		    Method method = service.getMethod(id);
		    request.setAttribute(METHOD, method);
		    RequestDispatcher requestDispatcher = request.getRequestDispatcher(INDEX);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}