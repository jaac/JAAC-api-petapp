package lpa.api.resources;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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

import lpa.api.documents.core.Breed;
import lpa.api.documents.core.Type;
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

	private Type pettype;

	@Before
	public void Before() {
		this.petTypeDto = new PetTypeDto("Dog Test 2");
		this.breedDto = new BreedDto("Husky");
	}

	public void createPetTypeBreed(Type pettype, BreedDto breedDto) {
		this.pettype = pettype;
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(breedDto).patch().build();
	}

	@Test
	public void testCreatePetType() {
		this.petTypeDto.setName("newpettype");
		this.createPetType(petTypeDto);
	}

	@Test
	public void testReadPetType() {
		Type pettype = this.petTypeRepository.findByName("Dog Test");
		PetTypeDto petTypeDto = restService.loginAdmin().restBuilder(new RestBuilder<PetTypeDto>())
				.clazz(PetTypeDto.class).path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_ID)
				.expand(pettype.getId()).get().build();

		assertNotNull(petTypeDto.getId());
	}

	@Test
	public void testReadPetTypeAll() {
		PetTypeDto[] petTypeDtoList = restService.loginAdmin().restBuilder(new RestBuilder<PetTypeDto[]>())
				.clazz(PetTypeDto[].class).path(PetTypeResource.PETTYPE).get().build();
		assertTrue(petTypeDtoList.length > 0);
	}

	@Test
	public void testReadPetTypeNotFoundException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder(new RestBuilder<PetTypeDto>()).clazz(PetTypeDto.class)
				.path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_ID).expand("22").get().build();
	}

	@Test
	public void testUpdatePetType() {
		this.createPetType(petTypeDto);
		this.pettype = this.petTypeRepository.findByName(petTypeDto.getName());

		this.pettype.setName("Dog Test Updated");
		this.petTypeDto = new PetTypeDto(this.pettype);

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).param("petType_id", pettype.getId())
				.body(petTypeDto).put().build();
		assertNotNull(this.petTypeRepository.findByName("Dog Test Updated"));
		assertNotNull(this.petTypeRepository.findByName("Dog Test Updated").getBreed()[0]);
	}

	@Test
	public void testUpdatePetTypeNotExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).param("petType_id", "Typetest")
				.body(petTypeDto).put().build();
	}

	private void createPetType(PetTypeDto petTypeDto) {
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(petTypeDto).post().build();
		this.createBreedList();
	}

	private void createBreedList() {
		Breed[] breedList = new Breed[1];
		breedList[0] = new Breed(this.breedDto.getName());
		Type pettype = this.petTypeRepository.findByName(petTypeDto.getName());
		pettype.setBreed(breedList);
		this.petTypeRepository.save(pettype);
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

		this.createPetType(this.petTypeDto);

		Type pettype = this.petTypeRepository.findByName(this.petTypeDto.getName());

		this.breedDto.setName("Hober");

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();

	}

	@Test
	public void testAddBreedPetTypeNotExsistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.breedDto = new BreedDto("Salchicha");
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", "fake456").body(this.breedDto).patch().build();
	}

	@Test
	public void testAddBreedEmptyNameException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).body(this.petTypeDto).post().build();
		Type pettype = this.petTypeRepository.findByName("Dog Test");

		this.breedDto = new BreedDto(null);
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", pettype.getId()).body(this.breedDto).patch().build();
	}

	@Test
	public void testAddRepeatedBreedException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.createPetType(petTypeDto);
		this.pettype = this.petTypeRepository.findByName(this.petTypeDto.getName());

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", this.pettype.getId()).body(this.breedDto).patch().build();
	}

	@Test
	public void testDeleteBreed() {
		Type pettype = this.petTypeRepository.findByName("Dog Test");
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.path(PetTypeResource.PETTYPE_DELETEBREED).param("petType_id", pettype.getId()).param("breed", "Husky")
				.patch().build();
	}

	@Test
	public void testDeleteBreedPetTypeNoExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.path(PetTypeResource.PETTYPE_DELETEBREED).param("petType_id", "7788").param("breed", "Husky Test")
				.patch().build();
	}

	@Test
	public void testUpdateBreed() {
		this.breedDto = new BreedDto("Husky Update");
		this.createPetTypeBreed(this.petTypeRepository.findByName("Dog Test"), this.breedDto);

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", this.pettype.getId()).param("breed", "BullDog").body(this.breedDto).patch()
				.build();
	}

	@Test
	public void testUpdateBreedBreedNoExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.breedDto = new BreedDto("Husky Update");
		this.createPetType(this.petTypeDto);
		this.pettype = this.petTypeRepository.findByName(this.petTypeDto.getName());

		restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPE_BREED)
				.param("petType_id", this.pettype.getId()).param("breed", "Husky no exist").body(this.breedDto).patch()
				.build();
	}

	@After
	public void deletePetType() {
		if (this.petTypeDto.getName() != null) {
			restService.loginAdmin().restBuilder().path(PetTypeResource.PETTYPE).path(PetTypeResource.PETTYPENAME)
					.expand(this.petTypeDto.getName()).delete().build();
		}
	}
}
