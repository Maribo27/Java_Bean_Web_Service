package by.maribo.java_beans_handbook.structure;

import javax.servlet.http.HttpServletRequest;

import static by.maribo.java_beans_handbook.structure.controller.command.CommandType.GET_ENTITY;
import static by.maribo.java_beans_handbook.structure.controller.command.CommandType.GET_METHOD;

public final class Util {

	private static final String JAVA_BEANS_COMMAND = "/java_beans?command=";
	private static final String ID = "&id=";
	private static final String TYPE = "&type=";
	private static final String ID_1_TYPE_TYPE = "&id=1&type=type";

	public static String makeEntityAddress(HttpServletRequest request, String id, String type) {
		return request.getContextPath() + JAVA_BEANS_COMMAND + GET_ENTITY.name() + ID + id + TYPE + type;
	}

	public static String makeMethodAddress(HttpServletRequest request, String id) {
		return request.getContextPath() + JAVA_BEANS_COMMAND + GET_METHOD.name() + ID + id;
	}

	public static String makeMainAddress(HttpServletRequest request) {
		return request.getContextPath() + JAVA_BEANS_COMMAND + GET_ENTITY.name() + ID_1_TYPE_TYPE;
	}

	private Util() {
	}
}
