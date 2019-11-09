package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Type;

public interface PetTypeRepository extends MongoRepository<Type, String> {

	Type findByName(String name);

}
