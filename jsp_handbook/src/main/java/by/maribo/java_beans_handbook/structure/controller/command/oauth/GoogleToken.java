package by.maribo.java_beans_handbook.structure.controller.command.oauth;

import by.maribo.java_beans_handbook.structure.bean.User;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import static by.maribo.java_beans_handbook.structure.ControlConst.*;

public class GoogleToken implements Command {
	private static final Logger logger = LoggerFactory.getLogger(GoogleToken.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String code = request.getParameter(CODE);

		ResourceBundle rb = ResourceBundle.getBundle(OAUTH_BASE_NAME);
		HttpClient httpClient = HttpClients.createDefault();

		HttpPost post = new HttpPost(rb.getString("oauth.google.tokenURI"));
		String parameters = createAddress(code, rb);
		post.setEntity(new StringEntity(parameters, ContentType.create("application/x-www-form-urlencoded")));

		HttpEntity entity = httpClient.execute(post).getEntity();
		InputStream responseStream = entity.getContent();

		JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
		if (!jsonObject.has(ACCESS_TOKEN)) {
			logger.info("Cannot authorize to google: wrong token");
			logger.info(jsonObject.toString());
		} else {
			logIn(request, rb, httpClient, jsonObject);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX);
		dispatcher.forward(request, response);
	}

	private String createAddress(String code, ResourceBundle rb) {
		return "code=" + code +
				"&" + "grant_type=" + rb.getString("oauth.google.grantType") +
				"&" + "client_id=" + rb.getString("oauth.google.clientId") +
				"&" + "client_secret=" + rb.getString("oauth.google.secret") +
				"&" + "redirect_uri=" + rb.getString("oauth.google.redirectURI");
	}

	private void logIn(HttpServletRequest request, ResourceBundle rb, HttpClient httpClient, JSONObject jsonObject) throws IOException {
		String accessToken = jsonObject.getString(ACCESS_TOKEN);
		HttpGet get = new HttpGet(rb.getString("oauth.google.userInfo") + "?alt=json&access_token=" + accessToken);
		InputStream userEntityContent = httpClient.execute(get).getEntity().getContent();
		JSONObject jsonUser = new JSONObject(new JSONTokener(new InputStreamReader(userEntityContent)));
		if (jsonUser.has(ERROR)) {
			logger.info(jsonUser.toString());
		} else {
			User user = createUserFromJSON(jsonUser);
			HttpSession session = request.getSession();
			session.setAttribute(USER, user);
		}
	}

	private User createUserFromJSON(JSONObject jsonUser) {
		User user = new User();
		String name = getName(jsonUser, "given_name");
		user.setFirstName(name);
		name = getName(jsonUser, "family_name");
		user.setLastName(name);
		return user;
	}

	private String getName(JSONObject jsonUser, String parameter) {
		String name = jsonUser.getString(parameter);
		try {
			byte text[] = name.getBytes("cp1251");
			name = new String(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("Cannot encode string - %s - from cp-1251 to utf-8", name);
		}
		return name;
	}
}