package lpa.api.repositories.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.LostPet;
import lpa.api.repositories.core.LostPetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class LostPetRepositoryIT {

	@Autowired
	private LostPetRepository lostPetRepository;

	private LostPet lostpet;

	@Before
	public void testSeedDb() {
		this.lostpet = new LostPet();
		this.lostPetRepository.save(this.lostpet);
	}

	@Test
	public void testFindLostPetNear3_7KM() {
		// AnimalApp coordinates = -1.2304894, 38.0462415
		// User Location 38.049283, -1.195401 = from pet 3.7KM
		Point userLocation = new Point(-1.195401, 38.049283);
		Distance dmax = new Distance(3.70, Metrics.KILOMETERS);
		Pageable pageable = new PageRequest(0, 3);
		assertEquals(3, this.lostPetRepository.findByLocationNear(userLocation, dmax, pageable).getContent().size());
	}

	@Test
	public void testFindbyPetName() {
		assertEquals("Naluda Hey", this.lostPetRepository.findByPetName("Naluda Hey").getPet().getName());
	}

	@After
	public void delete() {
		this.lostPetRepository.delete(lostpet);
	}

}
