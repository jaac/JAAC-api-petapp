package lpa.api.documents.core;

public class Breed {

	private String name;

	public Breed() {

	}

	public Breed(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		return "Breed[ name= " + this.name + "]";

	}
}
