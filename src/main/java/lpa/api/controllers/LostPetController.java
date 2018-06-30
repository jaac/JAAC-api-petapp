package lpa.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetFrontDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.repositories.core.LostPetRepository;

@Controller
public class LostPetController {

	@Autowired
	private LostPetRepository lostPetRepository;

	public void createLostPet(LostPetInputDto lostPetInputDto) {
		LostPet lostPet = new LostPet(lostPetInputDto.isFound(), lostPetInputDto.getPetLocation(), lostPetInputDto.getDescription(),
				lostPetInputDto.getHealthCondition(), lostPetInputDto.getPet(), lostPetInputDto.getUser(), lostPetInputDto.getLostWay(),
				lostPetInputDto.isGratification());
				
		this.lostPetRepository.save(lostPet);

	}

	public List<LostPetMinimumDto> readLostPetAll() {

		return this.lostPetRepository.findLostPetAll();
	}

	public Optional<LostPetOutputDto> readLostPetOutputDto(String id) {
		LostPet lostPetdb = this.lostPetRepository.findOne(id);

		if (lostPetdb == null) {
			return Optional.empty();
		} else {
			return Optional.of(new LostPetOutputDto(lostPetdb));
		}

	}

	public Optional<LostPetFrontDto> readLostPetFrontDto(String id) {
		LostPet lostPetdb = this.lostPetRepository.findOne(id);

		if (lostPetdb == null) {
			return Optional.empty();
		} else {
			return Optional.of(new LostPetFrontDto(lostPetdb));
		}
	}

}
