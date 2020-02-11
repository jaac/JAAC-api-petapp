package lpa.api.repositories.core;

import lpa.api.documents.core.SpeciesBreed;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpeciesBreedRepository  extends MongoRepository<SpeciesBreed, String> {

}
