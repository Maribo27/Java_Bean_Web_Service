package by.maribo.web_service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entity implements Serializable {
	@XmlElement
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private String description;
	@XmlElement
	private String type;

	public Entity() {
	}

	public Entity(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
}
