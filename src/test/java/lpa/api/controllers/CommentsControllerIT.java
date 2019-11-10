package lpa.api.controllers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.Location;
import lpa.api.dtos.PetCommentsInputDto;
import lpa.api.repositories.core.LostPetRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CommentsControllerIT {

	@Autowired
	private PetCommentsController petCommentsController;

	@Autowired
	private LostPetRepository lostPetRepository;

	@Test
	public void testCreatePetComment() {
		PetCommentsInputDto petCommentsInputDto = new PetCommentsInputDto();
		Image petImage = new Image("image.jpg");
		Location location = new Location();
		petCommentsInputDto.setComment("comment from test");
		petCommentsInputDto.setiSaw(true);
		petCommentsInputDto.setLostPetId(getLostPetId());
		petCommentsInputDto.setPetImage(petImage);
		petCommentsInputDto.setLocation(location);

		petCommentsInputDto.setUserId(this.lostPetRepository.findOne(getLostPetId()).getUser().getId());
		assertTrue(this.petCommentsController.createPetComment(petCommentsInputDto));

	}

	private String getLostPetId() {
		return this.lostPetRepository.findByPetName("Naluda Hey").getId();
	}
}
