package lpa.api.controllers;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.User;
import lpa.api.dtos.LostPetDeactivateInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class LostPetControllerIT {

	@Autowired
	private LostPetController lostPetController;

	@Autowired
	private UserRepository userRepository;

	private List<LostPet> lostPetList;

	private String lostPetId;
	@Autowired
	private LostPetRepository lostPetRepository;

	@Test
	public void testFindLostPetAll() {
		List<LostPetMinimumDto> lostPetList = lostPetController.readLostPetAll();
		assertNotNull(lostPetList);
	}

	@Test
	public void testDeactivatePetLostAsRegistered() {
		this.lostPetList = this.lostPetRepository.findByDescription("Desc from yml seeder");
		this.lostPetId = this.lostPetList.get(1).getId();
		User userOwner = this.userRepository.findByMobile("666666003");
		LostPetDeactivateInputDto lostPetDeactivateInputDto = new LostPetDeactivateInputDto(userOwner.getId(),
				this.lostPetId);
		this.lostPetController.deactiveLostPet(lostPetDeactivateInputDto);
		assertEquals(false, this.lostPetRepository.findOne(lostPetId).isActive());
	}
}
