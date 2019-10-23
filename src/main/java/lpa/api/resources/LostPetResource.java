package lpa.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.LostPetController;
import lpa.api.documents.core.Role;
import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.dtos.LostPetUpdateInputDto;
import lpa.api.resources.exceptions.ForbiddenException;
import lpa.api.resources.exceptions.LostPetBadRequest;
import lpa.api.resources.exceptions.LostPetIdNotFoundException;
import lpa.api.resources.exceptions.LostPetDistanceNotAllowedException;
import lpa.api.resources.exceptions.LostPetException;

@RestController
@RequestMapping(LostPetResource.LOSTPET)
public class LostPetResource {

	public static final String LOSTPET = "/lostPet";

	public static final String LOSTPET_ID = "/{id}";

	public static final String LOSTPET_NEAR = "/near";

	public static final String LOSTPET_DEACTIVE = "/deactive/{id}";

	public static final String LOSTPET_GET = "/get";

	@Autowired
	private LostPetController lostPetController;

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.POST)
	public void createLostPet(@Valid @RequestBody LostPetInputDto lostPetInputDto) throws LostPetBadRequest {
		System.out.println(lostPetInputDto);
		this.lostPetController.createLostPet(lostPetInputDto);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.PUT)
	public void putLostPet(@Valid @RequestBody LostPetUpdateInputDto lostPetPutInputDto)
			throws LostPetBadRequest, ForbiddenException {
		if (!this.lostPetController.putLostPet(lostPetPutInputDto, new Role[] { Role.ADMIN, Role.OPERATOR })) {
			if (lostPetPutInputDto.getLostPet() == null) {
				throw new LostPetBadRequest("Not lost pet data");
			}
			if (this.lostPetController.notOwner(lostPetPutInputDto)) {
				throw new ForbiddenException("Not user Owner");
			}
		}
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
	@RequestMapping(value = LOSTPET_ID, method = RequestMethod.DELETE)
	public void deleteLostPet(@PathVariable String id) {
		this.lostPetController.deleteLostPet(id);
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(value = LOSTPET_DEACTIVE, method = RequestMethod.PATCH)
	public void deactiveLostPet(@PathVariable String id) throws ForbiddenException {
		if (!this.lostPetController.deactiveLostPet(id)) {
			throw new ForbiddenException();
		}
	}

	@RequestMapping(value = LOSTPET_ID, method = RequestMethod.GET)
	public LostPetOutputDto readLostPet(@PathVariable String id) throws LostPetIdNotFoundException {
		return this.lostPetController.readLostPetOutputDto(id).orElseThrow(() -> new LostPetIdNotFoundException(id));
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
	@RequestMapping(value = LOSTPET_GET, params = { "page", "size" }, method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		Pageable pageable = new PageRequest(page, size);
		return this.lostPetController.readLostPetAll(pageable);
	}

	// @PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or
	// hasRole('REGISTERED')")
	@RequestMapping(value = LOSTPET_GET + LOSTPET_NEAR, params = { "longi", "lat", "distance", "page",
			"size" }, method = RequestMethod.GET)
	public Page<LostPetMinimumDto> readLostPetNear(@RequestParam("distance") double distance,
			@RequestParam("longi") double longi, @RequestParam("lat") double lat, @RequestParam("page") int page,
			@RequestParam("size") int size) throws LostPetDistanceNotAllowedException, LostPetException {
		if (size > 100) {
			throw new LostPetException("Size exeded");
		}
		if (distance >= 21) {
			throw new LostPetDistanceNotAllowedException();
		} else {
			
			return this.lostPetController.readLostPetNearMinimumDto(distance, longi, lat, page, size);
		}

	}
}
