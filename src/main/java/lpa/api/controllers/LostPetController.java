package lpa.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;

import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.dtos.LostPetUpdateInputDto;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.UserRepository;
import lpa.api.services.AuthenticationFacade;

@Controller
public class LostPetController {

	@Autowired
	private LostPetRepository lostPetRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationFacade authenticationFacade;

	public void createLostPet(LostPetInputDto lostPetInputDto) {
		LostPet lostPet = new LostPet(lostPetInputDto.isFound(), lostPetInputDto.getPetLocation(),
				lostPetInputDto.getDescription(), lostPetInputDto.getHealthCondition(), lostPetInputDto.getPet(),
				lostPetInputDto.getUser(), lostPetInputDto.getLostWay(), lostPetInputDto.isGratification());

		this.lostPetRepository.save(lostPet);
	}

	public List<LostPetMinimumDto> readLostPetAll(Pageable pageable) {
		List<LostPetMinimumDto> LostPetMinimumDtoList = new ArrayList<>();
		List<LostPet> lostPetList = this.lostPetRepository.findLostPetAll(pageable);
		for (LostPet lostPet : lostPetList) {
			LostPetMinimumDto lostPetMinimumDto = new LostPetMinimumDto(lostPet);
			LostPetMinimumDtoList.add(lostPetMinimumDto);
		}
		return LostPetMinimumDtoList;
	}

	public Optional<LostPetOutputDto> readLostPetOutputDto(String id) {
		LostPet lostPetdb = this.lostPetRepository.findOne(id);

		if (lostPetdb == null) {
			return Optional.empty();
		} else {
			return Optional.of(new LostPetOutputDto(lostPetdb));
		}
	}

	public Optional<LostPetOutputDto> readLostPetFrontDto(String id) {
		LostPet lostPetdb = this.lostPetRepository.findOne(id);
		if (lostPetdb == null) {
			return Optional.empty();
		} else {
			return Optional.of(new LostPetOutputDto(lostPetdb));
		}
	}

	public List<LostPetMinimumDto> readLostPetNearMinimumDto(double distancekm, double longi, double lat,
			Pageable pageable) {
		Point location = new Point(longi, lat);
		Distance distance = new Distance(distancekm, Metrics.KILOMETERS);
		List<LostPetMinimumDto> lostPetNearList = new ArrayList<>();
		List<LostPet> LostPetList = this.lostPetRepository.findByLocationNear(location, distance, pageable);
		for (LostPet lostpet : LostPetList) {
			LostPetMinimumDto lostPetMinimumDto = new LostPetMinimumDto(lostpet);
			lostPetNearList.add(lostPetMinimumDto);
		}
		return lostPetNearList;
	}

	public void deleteLostPet(String id) {
		this.lostPetRepository.delete(id);
	}

	public boolean putLostPet(LostPetUpdateInputDto lostPetPutInputDto, Role[] roles) {
		User user = this.userRepository.findOne(lostPetPutInputDto.getUserId());

		LostPet lostPet = this.lostPetRepository.findOne(lostPetPutInputDto.getLostPetId());

		assert user != null;
		assert lostPet != null;
		if (Arrays.asList(roles).containsAll(Arrays.asList(user.getRoles()))) {
			this.setdata(lostPetPutInputDto, lostPet);
			this.lostPetRepository.save(lostPet);
		} else if (Arrays.asList(new Role[] { Role.REGISTERED })
				.containsAll(Arrays.asList(lostPetPutInputDto.getUser().getRoles()))) {
			if (lostPetPutInputDto.getUser().equals(user)) {
				this.setdata(lostPetPutInputDto, lostPet);
				this.lostPetRepository.save(lostPet);
			} else {
				return false;
			}

		} else {
			return false;
		}
		return true;
	}

	private void setdata(LostPetUpdateInputDto lostPetPutInputDto, LostPet lostPet) {
		lostPet.setDescription(lostPetPutInputDto.getDescription());
		lostPet.setFound(lostPetPutInputDto.isFound());
		lostPet.setGratification(lostPetPutInputDto.isGratification());
		lostPet.setHealthCondition(lostPetPutInputDto.getHealthCondition());
		lostPet.setLocation(lostPetPutInputDto.getPetLocation());
		lostPet.setLostWay(lostPetPutInputDto.getLostWay());
		lostPet.setPet(lostPetPutInputDto.getPet());

	}

	public boolean notOwner(LostPetUpdateInputDto lostPetPutInputDto) {
		if (lostPetPutInputDto.getUser().getId() != lostPetPutInputDto.getUserId()) {
			return true;
		}
		return false;
	}

	public boolean deactiveLostPet(String id) {

		LostPet lostpet = this.lostPetRepository.findOne(id);
		User userIdOwner = lostpet.getUser();
		User currentUser = this.authenticationFacade.getCurrentUser();
		User user = this.userRepository.findOne(currentUser.getId());
		if (userIdOwner.equals(user) || Arrays.asList(new Role[] { Role.ADMIN, Role.OPERATOR })
				.containsAll(Arrays.asList(user.getRoles()))) {
			lostpet.setActive(false);
			this.lostPetRepository.save(lostpet);
			return false;
		} else {
			return true;
		}

	}

}
