package by.maribo.java_beans_handbook.structure.controller.command.oauth;

import by.maribo.java_beans_handbook.structure.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static by.maribo.java_beans_handbook.structure.ControlConst.INDEX;

public class LogOff implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(INDEX);
    }
}