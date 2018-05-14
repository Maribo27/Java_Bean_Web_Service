package by.maribo.java_beans_handbook.structure.controller;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;

public class InitializerListener implements ServletContextListener{
	public final static Logger logger = LoggerFactory.getLogger(ServletContextListener.class);

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		createLogger(servletContextEvent);
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}

	private void createLogger(ServletContextEvent servletContextEvent) {
		ServletContext context = servletContextEvent.getServletContext();
		String location = "log4j-config-location";
		String log4jConfigFile = context.getInitParameter(location);
		String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;

		PropertyConfigurator.configure(fullPath);
	}
}
