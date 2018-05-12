package by.maribo.web_service.view.styled_component;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class DropdownList {
	private JComboBox<String> dropdownList;

	public DropdownList() {
		dropdownList = new JComboBox<>();
		dropdownList.addItem("обязателен");
		dropdownList.addItem("если присутствует соответствующее поле");
		dropdownList.addItem("если присутствует соответствующее индексированное поле");
		dropdownList.setPreferredSize(new Dimension(500,50));
		dropdownList.setBackground(Color.LIGHT_GRAY);
		dropdownList.setBorder(new TitledBorder("Наличие"));
		dropdownList.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	public JComboBox<String> getDropdownList() {
		return dropdownList;
	}
}
