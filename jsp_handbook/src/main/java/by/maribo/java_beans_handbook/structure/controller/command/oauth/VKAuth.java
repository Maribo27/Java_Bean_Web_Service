package by.maribo.java_beans_handbook.structure.controller.command.oauth;

import by.maribo.java_beans_handbook.structure.controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

import static by.maribo.java_beans_handbook.structure.ControlConst.OAUTH_BASE_NAME;

public class VKAuth implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ResourceBundle rb = ResourceBundle.getBundle(OAUTH_BASE_NAME);
        String authUri = rb.getString("oauth.vk.authURI") +
                "?client_id=" + rb.getString("oauth.vk.clientId") +
                "&display=" + rb.getString("oauth.vk.display") +
                "&redirect_uri=" + rb.getString("oauth.vk.redirectURI") +
                "&scope=" + rb.getString("oauth.vk.scope") +
                "&response_type=" + rb.getString("oauth.vk.responseType") +
                "&v=" + rb.getString("oauth.vk.version");
        System.out.println(authUri);
        response.sendRedirect(authUri);
    }
}