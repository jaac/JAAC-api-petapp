package lpa.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.TokenController;
import lpa.api.dtos.TokenOutputDto;
import lpa.api.resources.TokenResource;

@RestController
@RequestMapping(TokenResource.TOKENS)
public class TokenResource {
	public static final String TOKENS = "/tokens";

    public static final String AUTHENTICATED = "/authenticated";

    @Autowired
    private TokenController tokenController;

    @PreAuthorize("authenticated")
    @RequestMapping(method = RequestMethod.POST)
    public TokenOutputDto login(@AuthenticationPrincipal User activeUser) {
        return tokenController.login(activeUser.getUsername());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
    @RequestMapping(value = AUTHENTICATED, method = RequestMethod.GET)
    public boolean tokenRoles(@AuthenticationPrincipal User activeUser) {
        return true;
    }
}
