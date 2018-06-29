package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.PetLocation;


public interface PetLocationRepository extends MongoRepository<PetLocation, String>{

}
