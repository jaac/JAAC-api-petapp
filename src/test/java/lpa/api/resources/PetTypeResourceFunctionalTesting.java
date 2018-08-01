package lpa.api.resources;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.PetType;
import lpa.api.dtos.BreedDto;
import lpa.api.dtos.PetTypeDto;
import lpa.api.repositories.core.PetTypeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class PetTypeResourceFunctionalTesting {

	@Autowired
	private RestService restService;

	private PetTypeDto petTypeDto;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private BreedDto breedDto;

	@Autowired
	private PetTypeRepository petTypeRepository;

	private PetType pettype;

	@Before
	public void Before() {
		this.petTypeDto = new PetTypeDto("Turtle");
	}

	public void createPetTypeBreed(PetType pettype, BreedDto breedDto) {
		this.pettype = pettype;
		System.out.println(breedDto);
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(breedDto).patch().build();
	}

	@Test
	public void testCreatePetTypeAsAdmin() {
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
	}

	@Test
	public void testCreatePetTypeRepeatedException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();

	}

	@Test
	public void testCreatePetTypeNameNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.petTypeDto.setName(null);
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
	}

	@Test
	public void testAddBreed() {
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
		PetType pettype = this.petTypeRepository.findByName("Dog Test");

		this.breedDto = new BreedDto("Salchicha");
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();
	}

	@Test
	public void testAddRepeatedBreedException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
		PetType pettype = this.petTypeRepository.findByName("Dog Test");

		this.breedDto = new BreedDto("Salchicha");
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();
	}

	@Test
	public void testDeleteBreed() {
		PetType pettype = this.petTypeRepository.findByName("Dog Test");
		this.breedDto = new BreedDto("Salchicha");
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.path(PetTypeResource.PETTYPE_DELETEBREED).param("petType_id", pettype.getId())
				.param("breed", "Salchicha").patch().build();
	}

	@Test
	public void testUpdateBreed() {
		this.breedDto = new BreedDto("Husky Update");
		this.createPetTypeBreed(this.petTypeRepository.findByName("Dog Test"), this.breedDto);

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", this.pettype.getId()).param("breed", "Husky").body(this.breedDto).patch().build();
	}

	@After
	public void deletePetType() {
		if (this.petTypeDto.getName() != null) {
			restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPENAME)
					.expand(this.petTypeDto.getName()).delete().build();
		}
	}
}
