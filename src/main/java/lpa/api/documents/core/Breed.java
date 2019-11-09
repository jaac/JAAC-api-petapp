package lpa.api.documents.core;

import lpa.api.dtos.BreedDto;
import org.springframework.data.annotation.Id;

public class Breed {

    @Id
    private String id;
    private String name;

    public Breed() {

    }

    public Breed(String name) {
        this.name = name;
    }

	public Breed(BreedDto breedDto) {
        this.name = breedDto.getName();
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
    public String toString() {
        return "Breed{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
