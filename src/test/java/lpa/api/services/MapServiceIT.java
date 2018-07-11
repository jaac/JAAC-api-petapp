package lpa.api.services;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.Location;
//import lpa.api.repositories.core.LostPetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MapServiceIT {

	@Autowired
	private MapService mapService;

	@Autowired
	private BoundingCoordinatesService BCService;
	

	@Test
	public void testGetLostPetIdByLocationList() {

		double latitude = 32.888;
		double longitude = -12.5555;

		double radius = 6371;
		double distance = 2;

		Location location = new Location();

		location.setCountry("España");
		
		///Traer el array y luego comprobar si tienen los mismos lat/long min/max

		List<String> listPetId = this.mapService.getLatLongMatchListByLatLong(radius,
				this.BCService.fromDegrees(latitude, longitude), distance);
		
		assertEquals("[asd4s445,sd555656a]", listPetId);
	}
}
