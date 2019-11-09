package lpa.api.controllers;

import lpa.api.documents.core.Breed;
import lpa.api.dtos.BreedDto;
import lpa.api.repositories.core.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class BreedController {

    @Autowired
    private BreedRepository breedRepository;

    public boolean addBreed(BreedDto breedDto) {
        return this.breedRepository.save(new Breed(breedDto)) != null;
    }

    public boolean isBreedAlreadyExist(BreedDto breedDto) {
        return  !this.findBreed(breedDto.getName()).equals(null) ? true : false;
    }

    public boolean updateBreed(String petType_id, BreedDto breedDto) {
        if (this.isBreedAlreadyExist(breedDto)) {
            if (!this.breedRepository.save(new Breed(breedDto)).equals(null)) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteBreed(String id) {
        this.breedRepository.delete(id);
        return true;
    }

    private Breed findBreed(String name) {
        return this.breedRepository.findByName(name);
    }
}
