package lpa.api.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.LostPetController;
import lpa.api.documents.core.Role;
import lpa.api.dtos.LostPetDeactivateInputDto;
import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.dtos.LostPetUpdateInputDto;
import lpa.api.resources.exceptions.ForbiddenException;
import lpa.api.resources.exceptions.LostPetBadRequest;
import lpa.api.resources.exceptions.LostPetIdNotFoundException;
import lpa.api.resources.exceptions.LotPetDistanceNotAllowedExecption;

@RestController
@RequestMapping(LostPetResource.LOSTPET)
public class LostPetResource {

	public static final String LOSTPET = "/lostPet";

	public static final String LOSTPET_ID = "/{id}";

	public static final String LOSTPET_NEAR = "/near";

	public static final String LOSTPET_DISTANCE = "/{distance}";

	public static final String LOSTPET_LONG = "/{longi}";

	public static final String LOSTPET_LAT = "/{lat}";

	public static final String LOSTPET_DEACTIVE = "/deactive";

	public static final String LOSTPET_PAGE = "/page";

	public static final String LOSTPET_PAGE_NUMBER = "/{page}";

	public static final String LOSTPET_PAGE_LIMIT = "/limit";

	public static final String LOSTPET_PAGE_LIMIT_NUMBER = "/{limit}";

	@Autowired
	private LostPetController lostPetController;

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.POST)
	public void createLostPet(@Valid @RequestBody LostPetInputDto lostPetInputDto) throws LostPetBadRequest {
		if (lostPetInputDto.getUser() == null) {
			throw new LostPetBadRequest("Usuario error");
		}
		if (lostPetInputDto.getPet() == null) {
			throw new LostPetBadRequest("Pet error");
		}
		if (lostPetInputDto.getPetLocation() == null) {
			throw new LostPetBadRequest("Location error");
		}
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

			if (lostPetPutInputDto.getLostPetId() == null) {
				throw new LostPetBadRequest("Not Lost Pet id");
			}

			if (lostPetPutInputDto.getUserId() == null) {
				throw new LostPetBadRequest("Not current user id");
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
	public void deactiveLostPet(@Valid @RequestBody LostPetDeactivateInputDto lostPetDeactivateInputDto)
			throws ForbiddenException {
		if (this.lostPetController.deactiveLostPet(lostPetDeactivateInputDto)) {
			throw new ForbiddenException();
		}
	}

	@RequestMapping(value = LOSTPET_ID, method = RequestMethod.GET)
	public LostPetOutputDto readLostPet(@PathVariable String id) throws LostPetIdNotFoundException {
		return this.lostPetController.readLostPetOutputDto(id).orElseThrow(() -> new LostPetIdNotFoundException(id));
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('OPERATOR')")
	@RequestMapping(value = LOSTPET_PAGE + LOSTPET_PAGE_NUMBER + LOSTPET_PAGE_LIMIT
			+ LOSTPET_PAGE_LIMIT_NUMBER, method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetAll(@PathVariable int page, @PathVariable int limit) {
		Pageable pageable = new PageRequest(page, limit);
		return this.lostPetController.readLostPetAll(pageable);
	}

	@RequestMapping(value = LOSTPET_NEAR + LOSTPET_LONG + LOSTPET_LAT + LOSTPET_DISTANCE + LOSTPET_PAGE
			+ LOSTPET_PAGE_NUMBER + LOSTPET_PAGE_LIMIT + LOSTPET_PAGE_LIMIT_NUMBER, method = RequestMethod.GET)
	public List<LostPetMinimumDto> readLostPetNear(@PathVariable double distance, @PathVariable double longi,
			@PathVariable double lat, @PathVariable int page, @PathVariable int limit)
			throws LotPetDistanceNotAllowedExecption {
		Pageable pageable = new PageRequest(page, limit);
		if (distance >= 21) {
			throw new LotPetDistanceNotAllowedExecption();
		} else {
			return this.lostPetController.readLostPetNearMinimumDto(distance, longi, lat, pageable);
		}

	}
}
