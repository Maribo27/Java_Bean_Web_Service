package by.maribo.java_beans_handbook.structure.bean;

import java.io.Serializable;
import java.util.Objects;

public class Error implements Serializable {
	private int code;
	private String description;

	public Error() {
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Error error = (Error) o;
		return code == error.code &&
				Objects.equals(description, error.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description);
	}

	@Override
	public String toString() {
		return "Error " + code + " : " + description;
	}
}
