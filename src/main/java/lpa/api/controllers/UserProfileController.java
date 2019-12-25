package lpa.api.controllers;

import lpa.api.documents.core.User;
import lpa.api.documents.core.UserProfile;
import lpa.api.documents.core.Image;
import lpa.api.dtos.UserProfileDto;
import lpa.api.repositories.core.ImageRepository;
import lpa.api.repositories.core.UserProfileRepository;
import lpa.api.repositories.core.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;


@Controller
public class UserProfileController {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public Optional<UserProfileDto> createUserProfile(UserProfileDto userProfileDto, String user) {
        if (this.userProfileRepository.findByuser(this.userRepository.findByusername(user)) == null) {
            Image image = this.imageRepository.save(new Image(userProfileDto.getImage()));

            UserProfile userProfile = this.userProfileRepository.save(new UserProfile(
                    this.userRepository.findByusername(user), userProfileDto.getName(),
                    userProfileDto.getLastName(), userProfileDto.getMobile(),
                    userProfileDto.getAddress1(), userProfileDto.getAddress2(),
                    userProfileDto.getCity(), userProfileDto.getZipCode(), userProfileDto.getCountry(),
                    image, userProfileDto.getDescription()));

            return Optional.of(new UserProfileDto(userProfile));
        }
        return Optional.empty();
    }

    public UserProfile updateUserProfile(UserProfileDto userProfileDto, String user) {
        UserProfile userProfile = this.userProfileRepository.findByuser(this.userRepository.findByusername(user));
        Image image = this.imageRepository.findByimageUrl(userProfile.getImage().getImageUrl());
        if (image != null) {
            image.setImageUrl(userProfileDto.getImage());
        } else {
            image = new Image(userProfileDto.getImage());
        }
        userProfile.setAddress1(userProfileDto.getAddress1());
        userProfile.setAddress2(userProfileDto.getAddress2());
        userProfile.setCountry(userProfileDto.getCity());
        userProfile.setCity(userProfileDto.getCountry());
        userProfile.setDescription(userProfileDto.getDescription());
        userProfile.setImage(this.imageRepository.save(image));
        userProfile.setLastName(userProfileDto.getLastName());
        userProfile.setName(userProfileDto.getName());
        userProfile.setZipCode(userProfileDto.getZipCode());
        userProfile.setMobile(userProfileDto.getMobile());
        return this.userProfileRepository.save(userProfile);
    }

    public void deleteUserProfile(User user) {
        UserProfile userProfileDb = this.userProfileRepository.findByuser(user);
        if (userProfileDb != null) {
            this.userProfileRepository.delete(userProfileDb.getId());
        }
    }

    public Optional<UserProfileDto> readUserProfile(User user) {
        UserProfile userProfile = this.userProfileRepository.findByuser(user);
        if (userProfile != null) {
            return Optional.of(new UserProfileDto(userProfile));
        }
        return Optional.empty();
    }

    public User readUser(String user) {
        return this.userRepository.findByusername(user);
    }
}
