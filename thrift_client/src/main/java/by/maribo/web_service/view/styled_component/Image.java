package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import java.net.URL;
import java.util.Objects;

public class Image {
	private ImageIcon icon;

	public Image(String fileName) {
		String folder = "pics/";
		ClassLoader classLoader = getClass().getClassLoader();
		URL notNullResourceFolder = Objects.requireNonNull(classLoader.getResource(folder + fileName));
		String path = notNullResourceFolder.getPath();
		icon = new ImageIcon(path);
	}

	public ImageIcon getIcon() {
		return icon;
	}
}