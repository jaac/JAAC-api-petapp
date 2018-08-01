package lpa.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.PetTypeController;
import lpa.api.dtos.BreedDto;
import lpa.api.dtos.PetTypeDto;
import lpa.api.resources.exceptions.PetTypeAlreadyExistException;
import lpa.api.resources.exceptions.PetTypeException;

@RestController
@RequestMapping(PetTypeResource.PETTYPE)
@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
public class PetTypeResource {

	public static final String PETTYPE = "/pettype";

	public static final String PETTYPENAME = "/{name}";

	public static final String PETTYPE_DELETEBREED = "/deleteBreed";

	public static final String PETTYPE_BREED = "/breed";

	@Autowired
	private PetTypeController pettypecontroller;

	@RequestMapping(method = RequestMethod.GET)
	public List<PetTypeDto> readAllPetType() {
		return this.pettypecontroller.readAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createPetType(@Valid @RequestBody PetTypeDto petTypeDto)
			throws PetTypeAlreadyExistException, PetTypeException {
		if (petTypeDto.getName() == null) {
			throw new PetTypeException("No name of Pet Type");
		} else if (this.pettypecontroller.alreadyExistPetType(petTypeDto.getName())) {
			throw new PetTypeAlreadyExistException();
		} else {
			this.pettypecontroller.createPetType(petTypeDto);
		}

	}

	@RequestMapping(value = PETTYPENAME, method = RequestMethod.DELETE)
	public void deletePetType(@PathVariable String name) throws PetTypeException {
		if (!this.pettypecontroller.deletePetType(name)) {
			throw new PetTypeException();
		}
	}

	@RequestMapping(value = PETTYPE_BREED, params = { "petType_id" }, method = RequestMethod.PATCH)
	public void addBreed(@RequestParam("petType_id") String petType_id, @Valid @RequestBody BreedDto breedDto)
			throws PetTypeException {
		if (this.pettypecontroller.isBreedAlreadyExist(petType_id, breedDto)) {
			throw new PetTypeException("Breed already exist");
		}
		if (!this.pettypecontroller.addBreed(petType_id, breedDto)) {

			throw new PetTypeException();
		}
	}

	@RequestMapping(value = PETTYPE_BREED, params = { "petType_id", "breed" }, method = RequestMethod.PATCH)
	public void updateBreed(@RequestParam("petType_id") String petType_id, @RequestParam("breed") String breed,
			@Valid @RequestBody BreedDto breedDto) throws PetTypeException {
		System.out.println(breedDto);
		if (!this.pettypecontroller.updateBreed(petType_id, breedDto)) {

			throw new PetTypeException("Breed already exist");
		}
	}

	@RequestMapping(value = PETTYPE_BREED + PETTYPE_DELETEBREED, params = { "petType_id",
			"breed" }, method = RequestMethod.PATCH)
	public void deleteBreed(@RequestParam("petType_id") String petType_id, @RequestParam("breed") String breed)
			throws PetTypeException {

		if (!this.pettypecontroller.deleteBreed(petType_id, breed)) {
			throw new PetTypeException();
		}

	}
}
