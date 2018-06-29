package lpa.api.resources;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

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
import lpa.api.dtos.LostPetDto;
import lpa.api.dtos.LostPetMinimumDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")

public class LostPetResourceFunctionalTesting {

	@Autowired
	private RestService restService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String id;

	public void setId(String id) {
		this.id = id;
	}

	@Test
	public void testReadPetLostAll() {
		LostPetMinimumDto[] lostPet = restService.loginAdmin().restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).get().build();
		this.id = lostPet[0].getId();
		assertEquals(2, Arrays.asList(lostPet).size());
	}

	@Test
	public void testReadLostPetAsADamin() {
		LostPetDto lostPetDto = restService.loginAdmin().restBuilder(new RestBuilder<LostPetDto>())
				.clazz(LostPetDto.class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_ID).expand(this.id)
				.get().build();
		assertEquals("Healthy", lostPetDto.getHealthCondition());
	}

}
