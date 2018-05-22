package by.maribo.java_beans_handbook.structure.controller.command.oauth;

import by.maribo.java_beans_handbook.structure.bean.User;
import by.maribo.java_beans_handbook.structure.controller.command.Command;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
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
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import static by.maribo.java_beans_handbook.structure.ControlConst.*;

public class VKToken implements Command {
	private static final Logger logger = LoggerFactory.getLogger(VKToken.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String code = request.getParameter(CODE);
		HttpClient httpClient = HttpClients.createDefault();

		try {
			URI uri = createURIFromCode(code);
			HttpGet get = new HttpGet(uri);
			InputStream responseStream = httpClient.execute(get).getEntity().getContent();
			JSONObject jsonObject = new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));

			if (!jsonObject.has(ACCESS_TOKEN)) {
				logger.info("Cannot authorize to vk: wrong token");
				logger.info(jsonObject.toString());
			} else {
				logIn(request, httpClient, jsonObject);
			}
		} catch (AuthException e) {
			logger.error(e.getMessage());
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(INDEX);
		dispatcher.forward(request, response);
	}

	private void logIn(HttpServletRequest request, HttpClient httpClient, JSONObject jsonObject) throws IOException {
		URI uri = createURIFromJSON(jsonObject);
		HttpGet get = new HttpGet(uri);
		InputStream responseStream = httpClient.execute(get).getEntity().getContent();
		JSONObject jsonUser = new JSONObject(new JSONTokener(new InputStreamReader(responseStream)));
		if (jsonUser.has(ERROR)) {
			logger.info(jsonUser.toString());
		}
		User user = createUserFromJSON(jsonUser);
		HttpSession session = request.getSession();
		session.setAttribute(USER, user);
	}


	private User createUserFromJSON(JSONObject jsonUser) {
		User user = new User();
		JSONArray response = jsonUser.getJSONArray("response");
		for (int entry = 0; entry < response.length(); entry++) {
			JSONObject object = response.getJSONObject(entry);
			String name = getName(object, "first_name");
			user.setFirstName(name);
			name = getName(object, "last_name");
			user.setLastName(name);
		}
		return user;
	}

	private String getName(JSONObject object, String parameter) {
		if (!object.has(parameter)) {
			throw new JSONContentException("Cannot find parameter: " + parameter);
		}
		String objectString = object.getString(parameter);
		try {
			objectString = new String(objectString.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.info("Cannot encode string - %s - from cp-1251 to utf-8", objectString);
		}
		return objectString;
	}

	private URI createURIFromJSON(JSONObject jsonObject) {
		ResourceBundle rb = ResourceBundle.getBundle(OAUTH_BASE_NAME);
		try {
			int id = jsonObject.getInt("user_id");
			String userId = String.valueOf(id);
			return new URIBuilder()
					.setScheme("https")
					.setHost("api.vk.com")
					.setPath("method/users.get")
					.addParameter("user_ids", userId)
					.addParameter("v", rb.getString("oauth.vk.version"))
					.addParameter(ACCESS_TOKEN, jsonObject.getString(ACCESS_TOKEN))
					.build();
		} catch (URISyntaxException e) {
			throw new AuthException("Cannot build uri: " + e.getMessage());
		}
	}

	private URI createURIFromCode(String code) {
		ResourceBundle rb = ResourceBundle.getBundle(OAUTH_BASE_NAME);
		try {
			return new URIBuilder()
					.setScheme("https")
					.setHost("oauth.vk.com")
					.setPath(ACCESS_TOKEN)
					.addParameter("client_id", rb.getString("oauth.vk.clientId"))
					.addParameter("client_secret", rb.getString("oauth.vk.secret"))
					.addParameter("redirect_uri", rb.getString("oauth.vk.redirectURI"))
					.addParameter(CODE, code)
					.build();
		} catch (URISyntaxException e) {
			throw new AuthException("Cannot build uri: " + e.getMessage());
		}
	}
}