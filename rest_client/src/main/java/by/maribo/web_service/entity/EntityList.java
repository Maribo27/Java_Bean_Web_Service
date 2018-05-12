package by.maribo.web_service.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EntityList {
	@XmlElement(name = "entity")
	private List<Entity> entityList;

	public EntityList() {
		entityList = new ArrayList<>();
	}

	public EntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	public List<Entity> getEntityList() {
		return entityList;
	}

	public void setEntityList(List<Entity> entityList) {
		this.entityList = entityList;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EntityList that = (EntityList) o;
		return Objects.equals(entityList, that.entityList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(entityList);
	}
}
