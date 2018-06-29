package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.LostWay;

public interface LostWayRepository extends MongoRepository<LostWay, String>{

}
