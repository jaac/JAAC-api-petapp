package lpa.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.PetComments;
import lpa.api.dtos.PetCommentsDto;
import lpa.api.repositories.core.PetCommentsRepository;

@Controller
public class PetCommentsController {

	@Autowired
	private PetCommentsRepository petCommentsRepository;

	public List<PetCommentsDto> readPetCommentsAll() {

		return this.petCommentsRepository.findPetCommentsAll();
	}

	public List<PetComments> readLostPetCommentsAll(LostPet lostPet) {
		return this.petCommentsRepository.findByLostPet(lostPet);
	}

}
