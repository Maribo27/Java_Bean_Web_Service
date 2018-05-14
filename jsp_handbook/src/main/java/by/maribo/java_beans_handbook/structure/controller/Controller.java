package by.maribo.java_beans_handbook.structure.controller;

import by.maribo.java_beans_handbook.structure.controller.command.Command;
import by.maribo.java_beans_handbook.structure.controller.command.CommandDirector;
import by.maribo.java_beans_handbook.structure.controller.command.CommandType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String CONTENT_TYPE = "text/html";
    private final CommandDirector director = CommandDirector.getInstance();

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        Command command = director.getCommand(CommandType.GET_ENTITIES.name());
        command.execute(request, response);
        command = director.getCommand(CommandType.GET_METHODS.name());
        command.execute(request, response);
        String commandName = request.getParameter(COMMAND);
        command = director.getCommand(commandName);
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
