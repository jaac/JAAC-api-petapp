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
import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.resources.exceptions.LostPetIdNotFoundException;

@RestController
@RequestMapping(LostPetResource.LOSTPET)
public class LostPetResource {

	public static final String LOSTPET = "/lostPet";

	public static final String LOSTPET_ID = "/{id}";

	public static final String LOSTPET_NEAR = "/near";

	public static final String LOSTPET_DISTANCE = "/{distance}";
	
	public static final String LOSTPET_LONG = "/{longi}";
	
	public static final String LOSTPET_LAT = "/{lat}";

	@Autowired
	private LostPetController lostPetController;

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.POST)
	public void createLostPet(@Valid @RequestBody LostPetInputDto lostPetInputDto) {
		this.lostPetController.createLostPet(lostPetInputDto);
	}

	@RequestMapping(value = LOSTPET_ID, method = RequestMethod.GET)
	public LostPetOutputDto readLostPet(@PathVariable String id) throws LostPetIdNotFoundException {
		return this.lostPetController.readLostPetOutputDto(id).orElseThrow(() -> new LostPetIdNotFoundException(id));
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetAll() {
		return this.lostPetController.readLostPetAll();
	}

	@RequestMapping(value = LOSTPET_NEAR + LOSTPET_LONG + LOSTPET_LAT + LOSTPET_DISTANCE, method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetNear(@PathVariable double distance, @PathVariable double longi,
			@PathVariable double lat) {
		return this.lostPetController.readLostPetNearMinimumDto(distance, longi, lat);
	}
}
