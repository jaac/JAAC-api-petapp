package lpa.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.LostPetController;
import lpa.api.dtos.LostPetDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.resources.exceptions.LostPetIdNotFoundException;

@PreAuthorize("hasRole('REGISTERED') or hasRole('ADMIN')")
@RestController
@RequestMapping(LostPetResource.LOSTPET)
public class LostPetResource {

	public static final String LOSTPET = "/lostPet";

	public static final String LOSTPET_ID = "/{id}";

	@Autowired
	private LostPetController lostPetController;

	@RequestMapping(method = RequestMethod.POST)
	public void createLostPet(@Valid @RequestBody LostPetDto lostPetDto) {
		this.lostPetController.createLostPet(lostPetDto);
	}

	@RequestMapping(value = LOSTPET_ID, method = RequestMethod.GET)
	public LostPetDto readLostPet(@PathVariable String id) throws LostPetIdNotFoundException {
		return this.lostPetController.readLostPetDto(id).orElseThrow(() -> new LostPetIdNotFoundException(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetAll() {
		return this.lostPetController.readLostPetAll();
	}
}
