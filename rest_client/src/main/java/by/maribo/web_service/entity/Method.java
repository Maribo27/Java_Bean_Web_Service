package by.maribo.web_service.entity;

import java.util.Objects;

public class Method {
	private int id;
	private String name;
	private String description;
	private String necessity;

	public Method() {
	}

	public Method(int id, String name, String description, String necessity) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.necessity = necessity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNecessity() {
		return necessity;
	}

	public void setNecessity(String necessity) {
		this.necessity = necessity;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Method method = (Method) o;
		return id == method.id &&
				Objects.equals(name, method.name) &&
				Objects.equals(description, method.description) &&
				Objects.equals(necessity, method.necessity);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, necessity);
	}

	@Override
	public String toString() {
		return "Method{" +
				"id=" + id +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				", necessity='" + necessity + '\'' +
				'}';
	}
}