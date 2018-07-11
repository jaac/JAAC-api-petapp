package lpa.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.repositories.core.LostPetRepository;

@Controller
public class LostPetController {

	@Autowired
	private LostPetRepository lostPetRepository;

	public void createLostPet(LostPetInputDto lostPetInputDto) {
		LostPet lostPet = new LostPet(lostPetInputDto.isFound(), lostPetInputDto.getPetLocation(),
				lostPetInputDto.getDescription(), lostPetInputDto.getHealthCondition(), lostPetInputDto.getPet(),
				lostPetInputDto.getUser(), lostPetInputDto.getLostWay(), lostPetInputDto.isGratification());
		this.lostPetRepository.save(lostPet);
	}

	public List<LostPetMinimumDto> readLostPetAll() {
		List<LostPetMinimumDto> LostPetMinimumDtoList = new ArrayList<>();
		List<LostPet> lostPetList = this.lostPetRepository.findLostPetAll();
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

	public List<LostPetMinimumDto> readLostPetNearMinimumDto(double distancekm, double longi, double lat) {
		Point location = new Point(longi, lat);
		Distance distance = new Distance(distancekm, Metrics.KILOMETERS);
		List<LostPetMinimumDto> lostPetNearList = new ArrayList<>();
		List<LostPet> LostPetList = this.lostPetRepository.findByLocationNear(location, distance);
		for (LostPet lostpet : LostPetList) {
			LostPetMinimumDto lostPetMinimumDto = new LostPetMinimumDto(lostpet);
			lostPetNearList.add(lostPetMinimumDto);
		}
		return lostPetNearList;
	}

}
