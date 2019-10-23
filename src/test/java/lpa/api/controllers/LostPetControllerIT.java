package lpa.api.controllers;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.dtos.LostPetMinimumDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class LostPetControllerIT {

	@Autowired
	private LostPetController lostPetController;

	@Test
	public void testFindLostPetAll() {
		Pageable pageable = new PageRequest(0, 3);
		List<LostPetMinimumDto> lostPetList = lostPetController.readLostPetAll(pageable);
		assertNotNull(lostPetList);
	}

	@Test
	public void testReadPetsNear() {
		Page<LostPetMinimumDto> lostPetList = lostPetController.readLostPetNearMinimumDto(5, 38.0464065,-1.2024834000000055, 0, 25);
		System.out.println(lostPetList.getContent());
		assertNotNull(lostPetList.getContent());
	}

}
