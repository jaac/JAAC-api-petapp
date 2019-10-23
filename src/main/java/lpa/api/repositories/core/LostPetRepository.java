package lpa.api.repositories.core;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Pet;
//import lpa.api.documents.core.Location;

public interface LostPetRepository extends MongoRepository<LostPet, String> {

	@Query(value = "{'active' : true}", fields = "{ '_id' : 1, 'found':1, 'pet':1, 'registrationDate': 1, 'petLocation':1 }")
	public List<LostPet> findLostPetAll(Pageable pageable);

	// @Query(value = "{'active' : true, 'petLocation.country' : ?0 }", fields = "{
	// '_id' : 1}")
	// public List<LostPet> findByPetLocationAndCountry(String country);

	// @Query(value = "{ location: { $geoNear: { $centerSphere: [[ ?0, ?1 ], ?2 ]} }
	// }", fields = "{ '_id' : 1}")
	public Page<LostPet> findByLocationNear(Point location, Distance distance, Pageable pageable);

	public LostPet findByPet(Pet pet);

	@Query(value = "{'active' : true, 'pet.name' : ?0 }", fields = "{'_id' : 1, 'pet': 1, 'location':1 }")
	public LostPet findByPetName(String name);

	public List<LostPet> findByDescription(String description);
}
