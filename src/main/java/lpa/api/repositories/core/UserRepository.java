package lpa.api.repositories.core;

import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;
import lpa.api.dtos.UserMinimumDto;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

    User findById(String id);

    @Query("{ 'token.value' : ?0 }")
    User findByTokenValue(String tokenValue);

/*    @Query(value = "{'roles' : ['REGISTERED']}", fields = "{ '_id' : 0, 'username' : 1, 'name' : 1}")
    public Page<UserMinimumDto> findRegisteredAll(Pageable pageable);*/

    @Query(value = "{'roles' : { $all : ?0 } }", fields = "{ '_id' : 0, 'username' : 1, 'name' : 1}")
    Page<User> findByRoles(Role[] role, Pageable pageable);

    User findByusername(String name);
}
