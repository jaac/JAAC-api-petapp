package lpa.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.PetCommentsController;

import lpa.api.dtos.PetCommentsInputDto;
import lpa.api.dtos.PetCommentsOutputDto;
import lpa.api.resources.exceptions.PetCommentsException;

@RestController
@RequestMapping(PetCommentsResource.PETCOMMENTS)
public class PetCommentsResource {

	public static final String PETCOMMENTS = "/comments";
	public static final String PETLOSTID = "/{plId}";
	public static final String PETCOMMENTS_GET = "/get";

	@Autowired
	private PetCommentsController petCommentsController;

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
	@RequestMapping(method = RequestMethod.GET)
	public List<PetCommentsOutputDto> readPetCommentsAll() {
		return this.petCommentsController.readPetCommentsAll();
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.POST)
	public void createPetComment(@RequestBody PetCommentsInputDto comment) throws PetCommentsException {
		System.out.println(comment);
		if (!this.petCommentsController.createPetComment(comment)) {

			throw new PetCommentsException(comment.getUserId());
		}
	}

	@RequestMapping(value = PetCommentsResource.PETLOSTID
			+ PetCommentsResource.PETCOMMENTS_GET, method = RequestMethod.GET)
	public Page<PetCommentsOutputDto> readPetLostCommentsAll(@PathVariable String plId, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return this.petCommentsController.readLostPetCommentsAll(plId, page, size);
	}
}
