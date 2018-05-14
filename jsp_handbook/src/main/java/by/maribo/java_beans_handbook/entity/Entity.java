package by.maribo.java_beans_handbook.entity;

import java.io.Serializable;
import java.util.Objects;

public class Entity implements Serializable {
	private int id;
	private String name;
	private String description;
	private Type type;

	public Entity() {
	}

	public Entity(String name, String description, String type) {
		this.name = name;
		this.description = description;
		this.type = Type.valueOf(type.toUpperCase());
	}

	public Entity(int id, String name, String description, String type) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = Type.valueOf(type.toUpperCase());
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

	public Type getType() {
		return type;
	}

	public void setType(String type) {
		this.type = Type.valueOf(type.toUpperCase());
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Entity entity = (Entity) o;
		return id == entity.id &&
				Objects.equals(name, entity.name) &&
				Objects.equals(type, entity.type) &&
				Objects.equals(description, entity.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, description, type);
	}

	@Override
	public String toString() {
		return "Entity{" +
				"id=" + id +
				", type='" + type + '\'' +
				", name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}

	public enum Type {
		EJB_INFO,
		EJB_TYPE,
		PROPERTY,
		ROLE,
		RULE,
		TYPE
	}
}
