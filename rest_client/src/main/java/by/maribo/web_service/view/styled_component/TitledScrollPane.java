package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class TitledScrollPane {
	private JScrollPane scroll;

	public TitledScrollPane(JTextArea textArea, String title) {
		scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBackground(Color.LIGHT_GRAY);

		scroll.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			@Override
			protected void configureScrollBarColors() {
				this.thumbColor = Color.DARK_GRAY;
				this.thumbHighlightColor = Color.LIGHT_GRAY;
			}
			@Override
			protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
				c.setBackground(Color.LIGHT_GRAY);
			}

		});

		scroll.setBorder(new TitledBorder(title));
	}

	public JScrollPane getScroll() {
		return scroll;
	}
}
