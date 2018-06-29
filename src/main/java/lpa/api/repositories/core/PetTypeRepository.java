package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.PetType;

public interface PetTypeRepository extends MongoRepository<PetType, String>{

}
