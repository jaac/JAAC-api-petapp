package lpa.api.repositories.core;

import java.util.List;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import lpa.api.documents.core.LostPet;
//import lpa.api.documents.core.Location;

public interface LostPetRepository extends MongoRepository<LostPet, String> {

	@Query(value = "{'active' : true}", fields = "{ '_id' : 1, 'found':1, 'pet':1, 'registrationDate': 1, 'petLocation':1 }")
	public List<LostPet> findLostPetAll();

	// @Query(value = "{'active' : true, 'petLocation.country' : ?0 }", fields = "{
	// '_id' : 1}")
	// public List<LostPet> findByPetLocationAndCountry(String country);

	// @Query(value = "{ location: { $geoNear: { $centerSphere: [[ ?0, ?1 ], ?2 ]} }
	// }", fields = "{ '_id' : 1}")
	public List<LostPet> findByLocationNear(Point location, Distance distance);

}
