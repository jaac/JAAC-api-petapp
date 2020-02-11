package lpa.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.Species;
import lpa.api.dtos.PetTypeDto;
import lpa.api.repositories.core.SpeciesRepository;

@Controller
public class PetTypeController {
    @Autowired
    private SpeciesRepository petTypeRepository;

    public List<PetTypeDto> readAll() {
        List<Species> pettypelist = this.petTypeRepository.findAll();
        List<PetTypeDto> pettypeDtolist = new ArrayList<>();
        for (Species petSpecies : pettypelist) {
            pettypeDtolist.add(new PetTypeDto(petSpecies));
        }
        return pettypeDtolist;
    }

    public void createPetType(PetTypeDto petTypeDto) {
        Species newPetSpecies = new Species(petTypeDto.getName());
        this.petTypeRepository.save(newPetSpecies);
    }

    public boolean alreadyExistPetType(String name) {
        Species petSpecies = this.petTypeRepository.findByName(name);
        if (petSpecies != null) {
            return true;
        }
        return false;
    }

    public boolean deletePetType(String name) {
        Species petSpecies = this.petTypeRepository.findByName(name);
        if (petSpecies == null) {
            return true;
        } else {
            this.petTypeRepository.delete(petSpecies);
            return true;
        }
    }

    public Species getType(String petType_id) {
        return this.petTypeRepository.findOne(petType_id);
    }

    private void savePetType(Species petSpecies) {
        this.petTypeRepository.save(petSpecies);
    }

    public boolean updatePetType(String petType_id, PetTypeDto petTypeDto) {
        Species petSpecies = this.petTypeRepository.findOne(petType_id);
        if (petSpecies != null) {
            petSpecies.setName(petTypeDto.getName());
            this.savePetType(petSpecies);
            return true;
        }
        return false;
    }

    public Optional<PetTypeDto> readPetType(String petType_id) {
        Species petSpecies = this.petTypeRepository.findOne(petType_id);
        if (petSpecies == null) {
            return Optional.empty();
        } else {
            return Optional.of(new PetTypeDto(petSpecies));
        }
    }

}
