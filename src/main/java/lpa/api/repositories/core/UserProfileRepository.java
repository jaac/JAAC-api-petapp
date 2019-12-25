package lpa.api.repositories.core;

import lpa.api.documents.core.User;
import lpa.api.documents.core.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    UserProfile findByuser(User user);
}
