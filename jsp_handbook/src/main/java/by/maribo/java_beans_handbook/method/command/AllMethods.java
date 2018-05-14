package by.maribo.java_beans_handbook.method.command;

import by.maribo.java_beans_handbook.method.Method;
import by.maribo.java_beans_handbook.method.MethodNotFoundException;
import by.maribo.java_beans_handbook.method.MethodService;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.service.ServiceException;
import by.maribo.java_beans_handbook.structure.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllMethods implements Command {
	private static final Logger logger = LoggerFactory.getLogger(AllMethods.class);
	private static final String METHODS = "methods";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    MethodService service = ServiceFactory.getInstance().getMethodService();
	    try {
	        List<Method> methods = service.getAllMethods();
	        request.setAttribute(METHODS, methods);
        } catch (MethodNotFoundException | ServiceException e) {
	        logger.error(e.getMessage(), e);
	        response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        }
    }
}