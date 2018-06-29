package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Pet;

public interface PetRepository extends MongoRepository<Pet, String>{

}
