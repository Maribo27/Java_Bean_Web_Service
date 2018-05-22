package by.maribo.java_beans_handbook.structure.controller.command;

public class AccessIsNotAllowedException extends RuntimeException {
	AccessIsNotAllowedException(String message) {
		super(message);
	}
}
