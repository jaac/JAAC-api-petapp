package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Location;


public interface PetLocationRepository extends MongoRepository<Location, String>{

}
