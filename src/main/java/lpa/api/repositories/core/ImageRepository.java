package lpa.api.repositories.core;

import lpa.api.documents.core.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {

    Image findByimageUrl(String image);

}
