package by.maribo.web_service.view.dialog;

import javax.swing.*;
import java.awt.*;

final class Sizer {
	static final int PANEL_WIDTH = 500;

	static void setSize(JDialog dialog) {
		Dimension size = new Dimension(600, 500);
		dialog.setSize(size);
		dialog.setPreferredSize(size);
		dialog.setMinimumSize(size);
		dialog.setMaximumSize(size);
	}

	private Sizer() {}
}
