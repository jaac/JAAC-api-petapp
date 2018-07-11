package lpa.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.resources.RestBuilder;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")

public class LostPetResourceFunctionalTesting {

	@Autowired
	private RestService restService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String lostPetId;

	private LostPetMinimumDto[] lostPetList;

	@Before
	public void createLostPetList() {
		this.lostPetList = restService.loginAdmin().restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).get().build();
		this.lostPetId = this.lostPetList[0].getId();
	}

	@Test
	public void testReadPetLostAll() {
		assertEquals(3, Arrays.asList(lostPetList).size());
	}

	@Test
	public void testReadLostPetAsAdmin() {
		LostPetOutputDto lostPetOutputDto = restService.loginAdmin().restBuilder(new RestBuilder<LostPetOutputDto>())
				.clazz(LostPetOutputDto.class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_ID)
				.expand(this.lostPetId).get().build();
		assertTrue(lostPetOutputDto.isActive());
	}

	@Test
	public void testReadLostPetAsRegisteredNoPetLost() {
		LostPetOutputDto lostPetOutputDto = restService.loginRegisteredNoPetLost()
				.restBuilder(new RestBuilder<LostPetOutputDto>()).clazz(LostPetOutputDto.class)
				.path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_ID).expand(this.lostPetId).get().build();
		assertEquals("Healthy", lostPetOutputDto.getHealthCondition());
	}

	@Test
	public void testReadLostPetMinimumFromCurrentPosition() {
		LostPetMinimumDto[] lostPetMinimumListDto = restService.restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_NEAR)
				.path(LostPetResource.LOSTPET_LONG).expand(-1.195401).path(LostPetResource.LOSTPET_LAT).expand(38.049283)
				.path(LostPetResource.LOSTPET_DISTANCE).expand(5).get().build();
		assertEquals(1, lostPetMinimumListDto.length);
	}

}
