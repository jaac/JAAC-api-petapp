package lpa.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.Breed;
import lpa.api.documents.core.PetType;
import lpa.api.dtos.BreedDto;
import lpa.api.dtos.PetTypeDto;
import lpa.api.repositories.core.PetTypeRepository;

@Controller
public class PetTypeController {
	@Autowired
	private PetTypeRepository petTypeRepository;

	private List<Breed> breedlist;

	public List<PetTypeDto> readAll() {
		List<PetType> pettypelist = this.petTypeRepository.findAll();
		List<PetTypeDto> pettypeDtolist = new ArrayList<>();
		for (PetType petType : pettypelist) {
			pettypeDtolist.add(new PetTypeDto(petType));
		}
		return pettypeDtolist;

	}

	public void createPetType(PetTypeDto petTypeDto) {
		PetType newPetType = new PetType(petTypeDto.getName());
		this.petTypeRepository.save(newPetType);
	}

	public boolean alreadyExistPetType(String name) {
		PetType petType = this.petTypeRepository.findByName(name);
		if (petType != null) {
			return true;
		}
		return false;
	}

	public boolean deletePetType(String name) {
		PetType petType = this.petTypeRepository.findByName(name);
		if (petType == null) {
			return true;
		} else {
			this.petTypeRepository.delete(petType);
			return true;
		}
	}

	public boolean addBreed(String petType_id, BreedDto breedDto) {
		PetType petType = this.getType(petType_id);
		if (petType != null) {
			Breed breed = new Breed(breedDto.getName());
			if (petType.getBreed() == null) {
				this.breedlist = new ArrayList<>();
				this.breedlist.add(breed);
				petType.setBreed(this.breedlist.toArray(new Breed[0]));
			} else {
				Breed[] breedlistArray = Arrays.copyOf(petType.getBreed(), petType.getBreed().length + 1);
				breedlistArray[petType.getBreed().length] = breed;
				petType.setBreed(breedlistArray);
			}
			this.petTypeRepository.save(petType);
			return true;
		} else {
			return false;
		}
	}

	public boolean isBreedAlreadyExist(String petType_id, BreedDto breedDto) {
		PetType petType = this.getType(petType_id);
		breedlist = Arrays.asList(petType.getBreed());
		if (this.findBreed(petType_id, breedDto.getName()) >= 0) {
			return true;
		}
		return false;
	}

	public boolean deleteBreed(String petType_id, String breed) {
		PetType petType = this.getType(petType_id);
		int breedKey = this.findBreed(petType_id, breed);

		if (breedKey == -1) {
			return true;
		} else if (breedKey >= 0) {
			Breed[] breedlistArray = petType.getBreed();
			int i = 0;
			Breed[] breedlistArrayNew = new Breed[petType.getBreed().length - 1];
			for (Breed breed2 : breedlistArray) {
				if (!new String(breed2.getName()).equals(breed)) {
					breedlistArrayNew[i] = breed2;
				}
				i++;
			}
			petType.setBreed(breedlistArrayNew);
			this.petTypeRepository.save(petType);
			return true;
		} else {
			return false;
		}
	}

	private PetType getType(String petType_id) {
		return this.petTypeRepository.findOne(petType_id);
	}

	private int findBreed(String petType_id, String breed) {
		PetType petType = this.getType(petType_id);
		Breed[] breedlistArray = petType.getBreed();
		int breedKey = -1;
		int i = 0;
		for (Breed breed2 : breedlistArray) {
			if (new String(breed2.getName()).equals(breed)) {
				breedKey = i;
				break;
			}
			i++;
		}
		return breedKey;
	}

	public boolean updateBreed(String petType_id, BreedDto breedDto) {
		PetType petType = this.getType(petType_id);
		int breedKey = this.findBreed(petType_id, breedDto.getName());
		Breed[] breedlistArray = petType.getBreed();
		if (breedKey >= 0) {
			breedlistArray[breedKey] = new Breed(breedDto.getName());
			petType.setBreed(breedlistArray);
			this.savePetType(petType);
			return true;
		}
		return false;
	}

	private void savePetType(PetType petType) {
		this.petTypeRepository.save(petType);
	}

}
