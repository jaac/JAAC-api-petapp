package lpa.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.Token;
import lpa.api.documents.core.User;
import lpa.api.dtos.TokenOutputDto;
import lpa.api.repositories.core.UserRepository;

@Controller
public class TokenController {

    @Autowired
    private UserRepository userRepository;

    public TokenOutputDto login(String mobile) {
        User user = userRepository.findByMobile(mobile);
        assert user != null;
        user.setToken(new Token());
        userRepository.save(user);
        return new TokenOutputDto(user);
    }

}
