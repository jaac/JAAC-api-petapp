package lpa.api.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.PetType;
import lpa.api.dtos.BreedDto;
import lpa.api.dtos.PetTypeDto;
import lpa.api.repositories.core.PetTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class PetTypeControllerIT {
	@Autowired
	PetTypeController petTypeController;
	private PetTypeDto petTypeDto;
	@Autowired
	private PetTypeRepository petTypeRepository;

	@Before
	public void create() {
		this.petTypeDto = new PetTypeDto("Cat test");
	}

	@Test
	public void testCreatePetType() {
		this.petTypeController.createPetType(petTypeDto);
	}

	@Test
	public void testAddBreed() {
		this.petTypeController.createPetType(petTypeDto);
		PetType pettype = this.petTypeRepository.findByName("Cat test");
		BreedDto breedDto = new BreedDto("Aad breed");
		BreedDto breedDto2 = new BreedDto("lana");
		petTypeController.addBreed(pettype.getId(), breedDto);
		petTypeController.addBreed(pettype.getId(), breedDto2);
	}

	@Test
	public void testUpdateBreed() {
		this.petTypeController.createPetType(petTypeDto);
		PetType pettype = this.petTypeRepository.findByName("Cat test");
		this.createBreed(pettype, "Angora");
		BreedDto breedDto = new BreedDto("Update breed");
		petTypeController.updateBreed(pettype.getId(), breedDto, "Angora");
	}

	@Test
	public void testDeleteBreed() {
		this.petTypeController.createPetType(petTypeDto);
		PetType pettype = this.petTypeRepository.findByName("Cat test");
		this.createBreed(pettype, "Angora");
		assertTrue(this.petTypeController.deleteBreed(pettype.getId(), "Angora"));
	}

	private void createBreed(PetType pettype, String name) {
		BreedDto breed = new BreedDto(name);
		petTypeController.addBreed(pettype.getId(), breed);
	}

	@After
	public void delete() {
		this.petTypeController.deletePetType(this.petTypeDto.getName());
	}

}
