package lpa.api.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import lpa.api.documents.core.*;
import lpa.api.repositories.core.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;

import lpa.api.dtos.LostPetInputDto;
import lpa.api.dtos.LostPetMinimumDto;
import lpa.api.dtos.LostPetOutputDto;
import lpa.api.dtos.LostPetUpdateInputDto;
import lpa.api.dtos.PetInputDto;
import lpa.api.repositories.core.LostPetRepository;
import lpa.api.repositories.core.SpeciesRepository;
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

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private SpeciesRepository petTypeRepository;

    public LostPetOutputDto createLostPet(LostPetInputDto lostPetInputDto) {
        LostPet lostPet = new LostPet(lostPetInputDto.isFound(), lostPetInputDto.getPetLocation(),
                lostPetInputDto.getDescription(), lostPetInputDto.getHealthCondition(), this.buildNewPet(lostPetInputDto.getPet()),
                this.userRepository.findById(lostPetInputDto.getUserId()), lostPetInputDto.getLostWay(), lostPetInputDto.isGratification());
        return  new LostPetOutputDto(this.lostPetRepository.save(lostPet));
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

    public Page<LostPetMinimumDto> readLostPetNearMinimumDto(double distancekm, double longi, double lat, int page,
                                                             int size) {
        Point location = new Point(longi, lat);
        Distance distance = new Distance(distancekm, Metrics.KILOMETERS);
        Page<LostPet> lostpetpage = this.lostPetRepository.findByLocationNear(location, distance,
                new PageRequest(page, size));
        List<LostPet> LostPetList = lostpetpage.getContent();
        List<LostPetMinimumDto> lostPetMinimumDtoNearList = new ArrayList<>();

        for (LostPet lostpet : LostPetList) {
            LostPetMinimumDto lostPetMinimumDto = new LostPetMinimumDto(lostpet);
            lostPetMinimumDtoNearList.add(lostPetMinimumDto);
        }

        Page<LostPetMinimumDto> lostPetNearPageList = new PageImpl<LostPetMinimumDto>(lostPetMinimumDtoNearList,
                new PageRequest(page, size), lostpetpage.getTotalElements());

        return lostPetNearPageList;
    }

    public void deleteLostPet(String id) {
        this.lostPetRepository.delete(id);
    }

    public boolean putLostPet(LostPetUpdateInputDto lostPetUpdateInputDto, Role[] roles) {
        User user = this.userRepository.findOne(lostPetUpdateInputDto.getUserId());
        LostPet lostPet = this.lostPetRepository.findOne(lostPetUpdateInputDto.getLostPetId());
        assert user != null;
        assert lostPet != null;
        if (Arrays.asList(roles).containsAll(Arrays.asList(user.getRoles()))) {
            this.bindData(lostPetUpdateInputDto, lostPet);
            this.lostPetRepository.save(lostPet);
        } else if (Arrays.asList(new Role[]{Role.REGISTERED}).containsAll(
                Arrays.asList(this.userRepository.findById(lostPetUpdateInputDto.getUserId()).getRoles()))) {
            if (this.userRepository.findById(lostPetUpdateInputDto.getUserId()).equals(user)) {
                this.bindData(lostPetUpdateInputDto, lostPet);
                this.lostPetRepository.save(lostPet);
            } else {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

    private void bindData(LostPetUpdateInputDto lostPetPutInputDto, LostPet lostPet) {
        lostPet.setDescription(lostPetPutInputDto.getDescription());
        lostPet.setFound(lostPetPutInputDto.isFound());
        lostPet.setGratification(lostPetPutInputDto.isGratification());
        lostPet.setHealthCondition(lostPetPutInputDto.getHealthCondition());
        lostPet.setLocation(lostPetPutInputDto.getPetLocation());
        lostPet.setLostWay(lostPetPutInputDto.getLostWay());
        lostPet.setPet(this.buildNewPet(lostPetPutInputDto.getPet()));
    }

    private Pet buildNewPet(PetInputDto petInputDto) {
        Pet pet = new Pet();
        Species petSpecies = this.petTypeRepository.findOne(petInputDto.getPetType());
        pet.setAge(petInputDto.getAge());
        pet.setBreed(this.breedRepository.findByName(petInputDto.getBreed()));
        pet.setGender(Gender.valueOf(petInputDto.getGender()));
        pet.setName(petInputDto.getName());
        pet.setSpecies(petSpecies);
        return pet;
    }

    public boolean notOwner(LostPetUpdateInputDto lostPetPutInputDto) {
        if (lostPetPutInputDto.getUserId().equals(this.authenticationFacade.getCurrentUser().getId())) {
            return true;
        }
        return false;
    }

    public boolean deactiveLostPet(String id) {

        LostPet lostpet = this.lostPetRepository.findOne(id);
        if (lostpet == null) {
            System.out.println(lostpet);
            return false;
        }
        User userIdOwner = lostpet.getUser();
        User currentUser = this.authenticationFacade.getCurrentUser();
        User user = this.userRepository.findOne(currentUser.getId());
        if (userIdOwner.equals(user) || Arrays.asList(new Role[]{Role.ADMIN, Role.OPERATOR})
                .containsAll(Arrays.asList(user.getRoles()))) {
            lostpet.setActive(false);
            this.lostPetRepository.save(lostpet);
            return true;
        } else {
            return false;
        }

    }

}
