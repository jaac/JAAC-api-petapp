package lpa.api.repositories.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Comments;

public interface PetCommentsRepository extends MongoRepository<Comments, String> {

	@Query(value = "{ 'active': true }", fields = " {'_id' : 0, 'comment' : 1, 'iSaw' : 1, 'date' : 1, 'user' : 1, 'petImage' : 1, 'petLocation' : 1 }")
	public List<Comments> findPetCommentsAll();

	@Query(value = "{ 'active': true, 'banned': false }", fields = " {'_id' : 1, 'comment' : 1, 'iSaw' : 1,'user' : 1, 'date' : 1, 'petImage' : 1, 'location' : 1 }")
	public Page<Comments> findByLostPet(LostPet lostPet, Pageable pageable);

	public Comments findByComment(String comment);

}
