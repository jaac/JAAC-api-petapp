package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import lpa.api.documents.core.Species;

public interface SpeciesRepository extends MongoRepository<Species, String> {

	Species findByName(String name);

}
