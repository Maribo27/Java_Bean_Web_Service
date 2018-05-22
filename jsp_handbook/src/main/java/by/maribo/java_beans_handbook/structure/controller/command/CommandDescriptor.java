package by.maribo.java_beans_handbook.structure.controller.command;

import java.util.Objects;

public class CommandDescriptor {
	private RightType type;
	private Command command;

	CommandDescriptor(RightType type, Command command) {
		this.type = type;
		this.command = command;
	}

	public RightType getType() {
		return type;
	}

	public Command getCommand() {
		return command;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CommandDescriptor that = (CommandDescriptor) o;
		return type == that.type &&
				Objects.equals(command, that.command);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, command);
	}

	@Override
	public String toString() {
		return "CommandDescriptor{" +
				"type=" + type +
				", command=" + command +
				'}';
	}
}