package by.maribo.java_beans_handbook.method.validation;

import by.maribo.java_beans_handbook.method.Method;

public final class MethodValidator {
	public static void isNecessity(String necessity) {

		for (Method.Necessity necessityType : Method.Necessity.values()) {
			if (necessity.equalsIgnoreCase(necessityType.name())){
				return;
			}
		}
		throw new InvalidNecessityException("Couldn't find necessity : " + necessity);
	}

	private MethodValidator() {
	}
}