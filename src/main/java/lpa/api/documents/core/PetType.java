package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PetType {
	@Id
	private String id;
	private String name;
	private Breed[] breed;

	public PetType() {

	}

	public PetType(String name) {
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

	public Breed[] getBreed() {
		return breed;
	}

	public void setBreed(Breed[] breed) {
		this.breed = breed;
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
		return (id.equals(((PetType) obj).id));
	}

	@Override
	public String toString() {

		return "PetType[ id= " + this.id + ", name= " + this.name + ", breeds=" + this.breed + " ]";

	}

}
