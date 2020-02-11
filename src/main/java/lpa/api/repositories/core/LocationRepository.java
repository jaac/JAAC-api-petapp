package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Location;


public interface LocationRepository extends MongoRepository<Location, String> {

    Location findBycountry(String country);

}
