package by.maribo.java_beans_handbook.structure.filter;

import by.maribo.java_beans_handbook.structure.bean.User;
import by.maribo.java_beans_handbook.structure.controller.command.AccessIsNotAllowedException;
import by.maribo.java_beans_handbook.structure.controller.command.CommandDirector;
import by.maribo.java_beans_handbook.structure.controller.command.UnknownCommandException;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.ControlConst.COMMAND;

/**
 * Filter checks user access rights.
 */
public class Access implements Filter {
	private static final Logger logger = Logger.getLogger(Access.class);
	private static final String USER = "user";

	@Override
	public void init(FilterConfig filterConfig) {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		String command = servletRequest.getParameter(COMMAND);
		if (command == null){
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		HttpSession session = ((HttpServletRequest)servletRequest).getSession();
		User user = (User) session.getAttribute(USER);
		CommandDirector director = CommandDirector.getInstance();
		try {
			director.checkAccess(command, user);
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (AccessIsNotAllowedException e) {
			logger.error(e.getMessage());
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
		} catch (UnknownCommandException e) {
			logger.error(e.getMessage());
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	public void destroy() {
	}
}
