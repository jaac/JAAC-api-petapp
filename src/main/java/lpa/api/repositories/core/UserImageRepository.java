package lpa.api.repositories.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import lpa.api.documents.core.Image;

public interface UserImageRepository extends MongoRepository<Image, String> {

}
