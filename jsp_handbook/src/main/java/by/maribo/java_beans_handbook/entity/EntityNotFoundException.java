package by.maribo.java_beans_handbook.entity;

import by.maribo.java_beans_handbook.structure.dao.NothingFoundException;

public class EntityNotFoundException extends NothingFoundException {
	EntityNotFoundException(String message) {
		super(message);
	}
}
