package by.maribo.java_beans_handbook.structure;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

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

	public static String encodeFromCp1251ToUtf8(String stringToEncode) throws UnsupportedEncodingException {
		byte text[] = stringToEncode.getBytes("cp1251");
		return new String(text, "UTF-8");
	}

	public static String decode(String encoded) throws UnsupportedEncodingException {
		return new String(encoded.getBytes(), "UTF-8");
	}

	private Util() {
	}
}
