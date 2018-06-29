package lpa.api.repositories.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.User;
import lpa.api.dtos.UserMinimumDto;


public interface UserRepository extends MongoRepository<User, String> {

    // @Query("{ 'mobile' : ?0 }")
    public User findByMobile(String mobile);

    public User findByEmail(String email);
    
    public User findById(String id);

    @Query("{ 'token.value' : ?0 }")
    public User findByTokenValue(String tokenValue);

    @Query(value = "{'roles' : 'REGISTERED'}", fields = "{ '_id' : 0, 'mobile' : 1, 'username' : 1}")
    public List<UserMinimumDto> findCustomerAll();
}
