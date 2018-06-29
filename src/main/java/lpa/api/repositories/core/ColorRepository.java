package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Color;

public interface ColorRepository extends MongoRepository<Color, String>{

}
