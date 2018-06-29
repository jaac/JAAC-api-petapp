package lpa.api.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.PetCommentsController;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.PetComments;
import lpa.api.dtos.PetCommentsDto;
import lpa.api.dtos.PetCommentsOutputDto;
import lpa.api.repositories.core.LostPetRepository;

//@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(PetCommentsResource.PETCOMMENTS)
public class PetCommentsResource {

	public static final String PETCOMMENTS = "/comments";
	public static final String PETLOSTID = "/plId={plId}";

	@Autowired
	private PetCommentsController petCommentsController;
	@Autowired
	private LostPetRepository lostPetRepository;

	@RequestMapping(method = RequestMethod.GET)
	public List<PetCommentsDto> findPetCommentsAll() {

		return this.petCommentsController.readPetCommentsAll();

	}

	@RequestMapping(value = PetCommentsResource.PETLOSTID, method = RequestMethod.GET)
	public List<PetCommentsOutputDto> findPetLostCommentsAll(@PathVariable String plId) {

		LostPet lostPet = this.lostPetRepository.findOne(plId);

		List<PetComments> petCommentslist = this.petCommentsController.readLostPetCommentsAll(lostPet);

		List<PetCommentsOutputDto> petCommentsOutputDtoList = new ArrayList<>();

		for (PetComments petComments : petCommentslist) {
			PetCommentsOutputDto petCommentsOutputDto = new PetCommentsOutputDto(petComments);

			petCommentsOutputDtoList.add(petCommentsOutputDto);

		}

		return petCommentsOutputDtoList;
	}
}
