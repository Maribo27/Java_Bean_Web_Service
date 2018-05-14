package by.maribo.java_beans_handbook.structure.controller.command;

import by.maribo.java_beans_handbook.structure.service.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.ControlConst.MODIFY;

public class Modifying implements Command {
	private static final Logger logger = LoggerFactory.getLogger(Modifying.class);
	private static final String ID_OLD = "idOld";
	private static final String NAME_OLD = "nameOld";
	private static final String DESCRIPTION_OLD = "descriptionOld";
	private static final String NECESSITY_OLD = "necessityOld";
	private static final String TYPE_OLD = "typeOld";
	private static final String ACTION = "action";

	@Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idOld = request.getParameter(ID_OLD);
		String nameOld = request.getParameter(NAME_OLD);
		String descriptionOld = request.getParameter(DESCRIPTION_OLD);
		String necessityOld = request.getParameter(NECESSITY_OLD);
		String typeOld = request.getParameter(TYPE_OLD);
		String action = request.getParameter(ACTION);
		try {
			request.setAttribute(NAME_OLD, nameOld);
			request.setAttribute(DESCRIPTION_OLD, descriptionOld);
			request.setAttribute(NECESSITY_OLD, necessityOld);
			request.setAttribute(TYPE_OLD, typeOld);
			request.setAttribute(ID_OLD, idOld);
			request.setAttribute(ACTION, action);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(MODIFY);
		    requestDispatcher.forward(request, response);
	    } catch (ServiceException e) {
		    logger.error(e.getMessage(), e);
		    response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
	    }
    }
}