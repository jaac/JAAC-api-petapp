package lpa.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

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

import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.dtos.LostPetUpdateInputDto;
import lpa.api.documents.core.Breed;
import lpa.api.documents.core.HealthCondition;
import lpa.api.documents.core.Image;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Pet;
import lpa.api.documents.core.Type;
import lpa.api.documents.core.User;

import lpa.api.dtos.LostPetInputDto;
import lpa.api.repositories.core.HealthConditionRepository;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.PetTypeRepository;
import lpa.api.repositories.core.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")

public class LostPetResourceFunctionalTesting {

	@Autowired
	private RestService restService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private String idDelete;

	private LostPet newLostPet;

	private boolean plCreated;

	private String lostPetId;

	private List<LostPet> lostPetList;

	private LostPetInputDto lostPetInputDto;

	private User user1;

	@Autowired
	private UserRepository UserRepository;

	private Location location;
	@Autowired
	private HealthConditionRepository healthConditionRepository;

	private HealthCondition healthCondition;

	private Pet pet;

	private Type pettype;

	private Image[] images;

	private Breed breed;

	private Color color;

	private Color color2;

	private LostWay lostWay;

	@Autowired
	private PetTypeRepository pettypeRepository;

	@Autowired
	private ColorRepository colorRepository;

	@Autowired
	private LostWayRepository lostWayRepository;

	@Autowired
	private LostPetRepository lostPetRepository;

	@Before
	public void createLostPetata() {
		this.lostPetList = this.lostPetRepository.findByDescription("Desc from yml seeder");
		this.lostPetId = this.lostPetList.get(1).getId();

		// User
		List<User> UList = this.UserRepository.findAll();
		this.user1 = UList.get(0);

		// Set Location
		double[] coordinates;
		coordinates = new double[2];
		// 47.404395, 8.525727 - Zúrich, Suiza
		coordinates[0] = 8.525727; // long
		coordinates[1] = 47.404395; // lat
		this.location = new Location(coordinates, "Suiza", "Zúrich", "Wipkingen", "Wipkingen", "oneStreet", 1,
				"1 Wipkingen - Zúrich - Suiza", 554);

		// Set Health condition
		List<HealthCondition> healthConditionList = this.healthConditionRepository.findAll();
		this.healthCondition = healthConditionList.get(0);

		// Set Pet Type
		List<Type> pettypeList = this.pettypeRepository.findAll();
		this.pettype = pettypeList.get(0);

		//Breed[] blist = pettype.getBreed();
		//this.breed = blist[0];

		// Set Colors
		this.color = this.colorRepository.findAll().get(0);
		this.color2 = this.colorRepository.findAll().get(0);

		// Set Images
		Image image1 = new Image("samall.jpg");

		images = new Image[4];
		images[0] = image1;

		// Set Pet
		//this.pet = new Pet(pettype, images, "female", "TeslaNala", 3, breed, color, color2);

		// Set Lost Way
		this.lostWay = this.lostWayRepository.findAll().get(0);

		// Set DTO
		// this.lostPetInputDto = new LostPetInputDto(false, this.location, "desc",
		// this.healthCondition, this.pet,
		// this.user1.getId(), this.lostWay, false, null);
	}

	@Test
	public void testCreateLostPetAsAdmin() {
		restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post().build();
		this.plCreated = true;
		// Assign the new Lost Pet created
		if (this.plCreated) {
			this.newLostPet = this.lostPetRepository.findByPet(this.pet);
		}
	}

	@Test
	public void testCreateLostPetAsRegistered() {
		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post()
				.build();
		// Assign the new Lost Pet created
		this.plCreated = true;
		this.newLostPet = this.lostPetRepository.findByPet(this.pet);
	}

	@Test
	public void testCreateLostPetAsOperator() {
		restService.loginOperator().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post()
				.build();
		// Assign the new Lost Pet created
		this.plCreated = true;
		this.newLostPet = this.lostPetRepository.findByPet(this.pet);
	}

	@Test
	public void testCreateLostPetAsAnon() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post().build();
		// Assign the new Lost Pet created
		this.plCreated = true;
		this.newLostPet = this.lostPetRepository.findByPet(this.pet);
	}

	@Test
	public void testUpdateLostPetAsAdmin() {
		LostPet lostPetEdit = this.lostPetRepository.findOne(this.lostPetId);

		lostPetEdit.setDescription("Put from test1");

		LostPetUpdateInputDto lostPetPutInputDto;
		lostPetPutInputDto = new LostPetUpdateInputDto(lostPetEdit, this.user1.getId(), this.lostPetId);
		restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).body(lostPetPutInputDto).put().build();
		LostPet lostPetEditTest = this.lostPetRepository.findOne(this.lostPetId);

		assertEquals("Put from test1", lostPetEditTest.getDescription());
	}

	@Test
	public void testUpdateLostPetAsRegisteredOwner() {
		LostPet lostPetEdit = this.lostPetRepository.findOne(this.lostPetId);

		lostPetEdit.setDescription("Put from test1");
		User userReg = this.UserRepository.findByEmail("u005@gmail.com");

		LostPetUpdateInputDto lostPetPutInputDto;
		lostPetPutInputDto = new LostPetUpdateInputDto(lostPetEdit, userReg.getId(), this.lostPetId);

		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).body(lostPetPutInputDto).put()
				.build();
		LostPet lostPetEditTest = this.lostPetRepository.findOne(this.lostPetId);

		assertEquals("Put from test1", lostPetEditTest.getDescription());
	}

	@Test
	public void testUpdateLostPetAsOperator() {
		LostPet lostPetEdit = this.lostPetRepository.findOne(this.lostPetId);

		lostPetEdit.setFound(true);
		User userReg = this.UserRepository.findByEmail("u005@gmail.com");

		LostPetUpdateInputDto lostPetPutInputDto;
		lostPetPutInputDto = new LostPetUpdateInputDto(lostPetEdit, userReg.getId(), this.lostPetId);

		restService.loginOperator().restBuilder().path(LostPetResource.LOSTPET).body(lostPetPutInputDto).put().build();
		LostPet lostPetEditTest = this.lostPetRepository.findOne(this.lostPetId);

		assertEquals(true, lostPetEditTest.isFound());
	}

	@Test
	public void testUpdateLostPetAsRegisteredNoOwnerException() {
		LostPet lostPetEdit = this.lostPetRepository.findOne(this.lostPetId);

		lostPetEdit.setDescription("Put from test1");
		User userRegNoOwner = this.UserRepository.findByEmail("u006@gmail.com");
		// System.out.println(userRegNoOwner);
		LostPetUpdateInputDto lostPetPutInputDto;
		lostPetPutInputDto = new LostPetUpdateInputDto(lostPetEdit, userRegNoOwner.getId(), this.lostPetId);

		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).body(lostPetPutInputDto).put()
				.build();
	}

	@Test
	public void testUpdateLostPetAsRegisteredNullLostpetExeception() {
		LostPet lostPetEdit = this.lostPetRepository.findOne(this.lostPetId);

		lostPetEdit.setDescription("Put from test1");
		User userRegNoOwner = this.UserRepository.findByEmail("u006@gmail.com");
		LostPetUpdateInputDto lostPetPutInputDto;
		lostPetPutInputDto = new LostPetUpdateInputDto(lostPetEdit, userRegNoOwner.getId(), this.lostPetId);
		lostPetPutInputDto.setLostPet(null);
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).body(lostPetPutInputDto).put()
				.build();
	}

	@Test
	public void testDeleteLostPetAsRegistered() {
		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_DEACTIVE)
				.expand(this.lostPetId).patch().build();
		assertFalse(this.lostPetRepository.findOne(lostPetId).isActive());
	}

	@Test
	public void testCreateLostPetUserNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.lostPetInputDto.setUserId(null);
		this.lostPetInputDto.setDescription("Fortnite");
		restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post().build();
	}

	@Test
	public void testCreateLostPetPetNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.lostPetInputDto.setPet(null);
		restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post().build();
	}

	@Test
	public void testCreateLostPetLocationNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.lostPetInputDto.setPetLocation(null);
		restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).body(this.lostPetInputDto).post().build();
	}

	@Test
	public void testReadAsAdminPetLostAll() {
		LostPetMinimumDto[] lostPetMinimumListDto = restService.loginAdmin()
				.restBuilder(new RestBuilder<LostPetMinimumDto[]>()).clazz(LostPetMinimumDto[].class)
				.path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_GET).param("page", "0").param("size", "3")
				.get().build();

		assertEquals(3, Arrays.asList(lostPetMinimumListDto).size());
	}

	@Test
	public void testReadLostPetAsAdmin() {
		LostPetOutputDto lostPetOutputDto = restService.loginAdmin().restBuilder(new RestBuilder<LostPetOutputDto>())
				.clazz(LostPetOutputDto.class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_ID)
				.expand(this.lostPetId).get().build();
		assertTrue(lostPetOutputDto.isActive());
	}

	@Test
	public void testReadLostPetMinimumFromCurrentPosition() {
		LostPetMinimumDto[] lostPetMinimumListDto = restService.restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_GET)
				.path(LostPetResource.LOSTPET_NEAR).param("longi", "-1.195401").param("lat", "38.049283")
				.param("distance", "5").param("page", "0").param("size", "5").get().build();
		System.out.println(lostPetMinimumListDto);
		assertEquals(2, lostPetMinimumListDto.length);
	}

	@Test
	public void testReadLostPetMinimumFromCurrentPositionSizeExededException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		LostPetMinimumDto[] lostPetMinimumListDto = restService.restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_GET)
				.path(LostPetResource.LOSTPET_NEAR).param("longi", "-1.195401").param("lat", "38.049283")
				.param("distance", "5").param("page", "0").param("size", "101").get().build();
		assertEquals(2, lostPetMinimumListDto.length);
	}

	@Test
	public void testReadLostPetMinimumFromCurrentPositionEmpty() {
		// La Miraña Oriental - Lugo 43.297184, -7.098994 - Current Position Molina de
		// segura - Murcia
		LostPetMinimumDto[] lostPetMinimumListDto = restService.restBuilder(new RestBuilder<LostPetMinimumDto[]>())
				.clazz(LostPetMinimumDto[].class).path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_GET)
				.path(LostPetResource.LOSTPET_NEAR).param("longi", "-7.098994").param("lat", "43.297184")
				.param("distance", "5").param("page", "0").param("size", "5").get().build();
		assertEquals(0, lostPetMinimumListDto.length);
	}

	@Test
	public void testDistanceMaxime20KMExededException() {
		// Distance allowed 5 - 10 - 15 - 20
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.restBuilder(new RestBuilder<LostPetMinimumDto[]>()).clazz(LostPetMinimumDto[].class)
				.path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_GET).path(LostPetResource.LOSTPET_NEAR)
				.param("longi", "-7.098994").param("lat", "43.297184").param("distance", "21").param("page", "0")
				.param("size", "5").get().build();

	}

	@Test
	public void testDeleteAsRegisteredException() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));

		restService.loginRegistered().restBuilder().path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_DEACTIVE)
				.expand("6566546s").patch().build();
	}

	@After
	public void delete() {
		// Delete new PetLost
		if (this.plCreated) {
			this.idDelete = this.newLostPet.getId();
			restService.loginAdmin().restBuilder().path(LostPetResource.LOSTPET).path(LostPetResource.LOSTPET_ID)
					.expand(this.idDelete).delete().build();
		}
	}

}
