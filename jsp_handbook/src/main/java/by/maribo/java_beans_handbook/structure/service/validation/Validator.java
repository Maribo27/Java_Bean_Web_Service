package by.maribo.java_beans_handbook.structure.service.validation;

public class Validator {
	private static final String NUMBER = "[1-9]\\d*";
	private static final String NAME = "^[A-ZА-Я][a-zа-я]{1,50}$";

	public static void isName(String name) throws InputException {
		boolean incorrectName = name == null || !name.matches(NAME);
		if (incorrectName) {
			throw new InputException("Incorrect name");
		}
	}

	public static void isID(String number) {
		if (!number.matches(NUMBER)){
			throw new NotNumberException(String.format("%s - is not a right number", number));
		}
	}
}