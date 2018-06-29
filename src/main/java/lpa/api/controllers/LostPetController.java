package lpa.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.dtos.LostPetDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.repositories.core.LostPetRepository;

@Controller
public class LostPetController {

	@Autowired
	private LostPetRepository lostPetRepository;

	public void createLostPet(LostPetDto lostPetDto) {
		LostPet lostPet = new LostPet(lostPetDto.isFound(), lostPetDto.getPetLocation(), lostPetDto.getDescription(),
				lostPetDto.getHealthCondition(), lostPetDto.getPet(), lostPetDto.getUser(), lostPetDto.getLostWay(),
				lostPetDto.isGratification());

		this.lostPetRepository.save(lostPet);

	}

	public List<LostPetMinimumDto> readLostPetAll() {

		return this.lostPetRepository.findLostPetAll();
	}

	public Optional<LostPetDto> readLostPetDto(String id) {
		LostPet lostPetdb = this.lostPetRepository.findOne(id);

		if (lostPetdb == null) {
			return Optional.empty();
		} else {
			return Optional.of(new LostPetDto(lostPetdb));
		}

	}
	

}
