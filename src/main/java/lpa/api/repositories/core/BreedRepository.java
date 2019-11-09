package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Breed;


public interface BreedRepository extends MongoRepository<Breed, String> {
    Breed findByName(String name);
}
