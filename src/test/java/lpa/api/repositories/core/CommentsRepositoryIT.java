package lpa.api.repositories.core;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.Comments;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class CommentsRepositoryIT {

	@Autowired
	PetCommentsRepository petCommentsRepository;

	@Test
	public void testReadPetCommentsAll() {
		List<Comments> comments = this.petCommentsRepository.findPetCommentsAll();
		assertEquals(11, comments.size());
	}

	@Test
	public void testFindByComment() {
		this.petCommentsRepository.findByComment("as");
	}

}
