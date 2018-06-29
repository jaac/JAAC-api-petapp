package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.HealthCondition;



public interface HealthConditionRepository extends MongoRepository<HealthCondition, String>{

}
