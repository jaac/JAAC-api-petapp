package lpa.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lpa.api.repositories.core.BreedRepository;
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

    public PetType getType(String petType_id) {
        return this.petTypeRepository.findOne(petType_id);
    }

    private void savePetType(PetType petType) {
        this.petTypeRepository.save(petType);
    }

    public boolean updatePetType(String petType_id, PetTypeDto petTypeDto) {
        PetType petType = this.petTypeRepository.findOne(petType_id);
        if (petType != null) {
            petType.setName(petTypeDto.getName());
            this.savePetType(petType);
            return true;
        }
        return false;
    }

    public Optional<PetTypeDto> readPetType(String petType_id) {
        PetType petType = this.petTypeRepository.findOne(petType_id);
        if (petType == null) {
            return Optional.empty();
        } else {
            return Optional.of(new PetTypeDto(petType));
        }
    }

}
