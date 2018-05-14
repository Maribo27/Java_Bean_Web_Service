package by.maribo.java_beans_handbook.method;

import by.maribo.java_beans_handbook.structure.dao.NothingFoundException;

public class MethodNotFoundException extends NothingFoundException {
	MethodNotFoundException(String message) {
		super(message);
	}
}
