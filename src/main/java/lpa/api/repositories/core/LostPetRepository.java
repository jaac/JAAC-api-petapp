package lpa.api.repositories.core;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.LostPet;
import lpa.api.dtos.LostPetMinimumDto;

public interface LostPetRepository extends MongoRepository<LostPet, String> {

	@Query(value = "{'active' : true}", fields = "{ '_id' : 1, 'found':1, 'pet':1, 'registrationDate': 1, 'petLocation':1 }")
	public List<LostPetMinimumDto> findLostPetAll();
	
	//public LostPet findById()
	
}
