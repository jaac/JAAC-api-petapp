package lpa.api.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.Comments;
import lpa.api.dtos.PetCommentsInputDto;
import lpa.api.dtos.PetCommentsOutputDto;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.PetCommentsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CommentsResourceFunctionalTesting {
	@Autowired
	private RestService restService;

	@Autowired
	private LostPetRepository lostPetRepository;

	@Autowired
	private PetCommentsRepository petCommentsRepository;

	@Test
	public void testReadAllComments() {
		PetCommentsOutputDto[] petCommentsOutputDtoList = restService.loginAdmin()
				.restBuilder(new RestBuilder<PetCommentsOutputDto[]>()).clazz(PetCommentsOutputDto[].class)
				.path(PetCommentsResource.PETCOMMENTS).get().build();
		System.out.println(petCommentsOutputDtoList[3]);
		assertTrue(petCommentsOutputDtoList.length > 1);
	}

	@Test
	public void testReadAllLostPetComments() {
		String id = this.getLostPetId();
		PetCommentsOutputDto[] petCommentsOutputDtoList = restService.loginAdmin()
				.restBuilder(new RestBuilder<PetCommentsOutputDto[]>()).clazz(PetCommentsOutputDto[].class)
				.path(PetCommentsResource.PETCOMMENTS).path(PetCommentsResource.PETLOSTID).expand(id)
				.path(PetCommentsResource.PETCOMMENTS_GET).param("page", "0").param("size", "10").get().build();
		assertTrue(petCommentsOutputDtoList.length > 1);
	}

	@Test
	public void testCreateLostPetComment() {
		PetCommentsInputDto petCommentsInputDto = new PetCommentsInputDto();
		Image petImage = new Image("image.jpg");
		Location location = new Location();
		petCommentsInputDto.setComment("comment from test");
		petCommentsInputDto.setiSaw(true);
		petCommentsInputDto.setLostPetId(getLostPetId());
		petCommentsInputDto.setPetImage(petImage);
		petCommentsInputDto.setLocation(location);
		petCommentsInputDto.setUserId(this.lostPetRepository.findOne(getLostPetId()).getUser().getId());
		restService.loginRegistered().restBuilder().path(PetCommentsResource.PETCOMMENTS).body(petCommentsInputDto)
				.post().build();
		String comment = this.petCommentsRepository.findByComment("comment from test").getComment();
		assertEquals("comment from test", comment);
	}
	
	@Test
	public void testCreateLostPetCommentNoLocation() {
		PetCommentsInputDto petCommentsInputDto = new PetCommentsInputDto();
		Image petImage = new Image("image.jpg");
		//Location location = new Location();
		petCommentsInputDto.setComment("comment from test3");
		petCommentsInputDto.setiSaw(true);
		petCommentsInputDto.setLostPetId(getLostPetId());
		petCommentsInputDto.setPetImage(petImage);
		petCommentsInputDto.setLocation(null);
		petCommentsInputDto.setUserId(this.lostPetRepository.findOne(getLostPetId()).getUser().getId());
		restService.loginRegistered().restBuilder().path(PetCommentsResource.PETCOMMENTS).body(petCommentsInputDto)
				.post().build();
		String comment = this.petCommentsRepository.findByComment("comment from test3").getComment();
		assertEquals("comment from test3", comment);
	}

	private String getLostPetId() {
		return this.lostPetRepository.findByPetName("Naluda Hey").getId();
	}

	@After
	public void deleteComment() {
		Comments com = this.petCommentsRepository.findByComment("comment from test");
		if (com != null) {
			this.petCommentsRepository.delete(com);
		}
	}
}
