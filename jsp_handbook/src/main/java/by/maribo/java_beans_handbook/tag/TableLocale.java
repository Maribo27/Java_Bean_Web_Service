package by.maribo.java_beans_handbook.tag;

import java.util.HashMap;
import java.util.Map;

import static by.maribo.java_beans_handbook.entity.Entity.Type.*;

final class TableLocale {
	private static Map<String, String> tableName = new HashMap<>();
	static {
		tableName.put(EJB_INFO.name(), "Общее");
		tableName.put(EJB_TYPE.name(), "Типы");
		tableName.put(PROPERTY.name(), "Свойства");
		tableName.put(ROLE.name(), "Роли");
		tableName.put(RULE.name(), "Правила");
		tableName.put(TYPE.name(), "Типы");
		tableName.put("METHOD", "Методы");
	}


	static String getName(String type) {
		return tableName.get(type.toUpperCase());
	}

	private TableLocale() {}
}