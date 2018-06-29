package lpa.api.documents.core;

import org.springframework.data.annotation.Id;

public class HealthCondition {

	@Id
	private String id;

	private String name;

	public HealthCondition() {

	}

	public HealthCondition(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return (id.equals(((HealthCondition) obj).id));
	}

	@Override
	public String toString() {

		return "HealthCondition[ id= " + this.id + ", name= " + this.name + "]";

	}

}
