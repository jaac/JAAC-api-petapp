package lpa.api.repositories.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.repositories.core.LostPetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class LostPetRepositoryIT {

	@Autowired
	private LostPetRepository lostPetRepository;

	@Test
	public void testFindLostPetNear3_7KM() {
		// AnimalApp coordinates = -1.2304894, 38.0462415

		// User Location 38.049283, -1.195401 = from pet 3.7KM
		Point userLocation = new Point(-1.195401, 38.049283);

		Distance dmax = new Distance(3.70, Metrics.KILOMETERS);
		assertEquals(1, this.lostPetRepository.findByLocationNear(userLocation, dmax).size());
	}

}
