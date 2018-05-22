package by.maribo.java_beans_handbook.structure.controller.command;

public class UnknownCommandException extends RuntimeException {
	UnknownCommandException(String message) {
		super(message);
	}
}
