package by.maribo.java_beans_handbook.structure.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command{
    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
