package by.maribo.java_beans_handbook.structure.controller.command.oauth;

import by.maribo.java_beans_handbook.structure.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static by.maribo.java_beans_handbook.structure.ControlConst.OAUTH_BASE_NAME;

public class GoogleAuth implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle(OAUTH_BASE_NAME);
        String authUri = rb.getString("oauth.google.authURI") +
                "?client_id=" + rb.getString("oauth.google.clientId") +
                "&redirect_uri=" + rb.getString("oauth.google.redirectURI") +
                "&response_type=" + rb.getString("oauth.google.responseType") +
                "&access_type=" + rb.getString("oauth.google.accessType") +
                "&scope=" + rb.getString("oauth.google.scope");
        response.sendRedirect(authUri);
    }
}