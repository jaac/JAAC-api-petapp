package lpa.api.controllers;

import lpa.api.documents.core.Image;
import lpa.api.repositories.core.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Properties;

@Controller
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    public Image createImage(String imageUrl) {
        return this.imageRepository.save(new Image(imageUrl));
    }

    public Image readImage(String image) {
        return this.imageRepository.findByimageUrl(image);
    }

}
