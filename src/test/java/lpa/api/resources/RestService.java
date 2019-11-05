package lpa.api.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import lpa.api.dtos.TokenOutputDto;


@Service
public class RestService {
    
    @Autowired
    private Environment environment;

    @Value("${server.contextPath}")
    private String contextPath;

    @Value("${lpa.admin.username}")
    private String adminUsername;

    @Value("${lpa.admin.password}")
    private String adminPassword;
    
    @Value("${lpa.databaseSeeder.ymlFileName}")
    private String testFile;

    private TokenOutputDto tokenDto;

    private int port() {
        return Integer.parseInt(environment.getProperty("local.server.port"));
    }

    public <T> RestBuilder<T> restBuilder(RestBuilder<T> restBuilder) {
        restBuilder.port(this.port());
        restBuilder.path(contextPath);
        if (tokenDto != null) {
            restBuilder.basicAuth(tokenDto.getToken());
        }
        return restBuilder;
    }

    public RestBuilder<Object> restBuilder() {
        RestBuilder<Object> restBuilder = new RestBuilder<>(this.port());
        restBuilder.path(contextPath);
        if (tokenDto != null) {
            restBuilder.basicAuth(tokenDto.getToken());
        }
        return restBuilder;
    }

    public RestService loginAdmin() {
        this.tokenDto = new RestBuilder<TokenOutputDto>(this.port()).path(contextPath).path(TokenResource.TOKENS)
                .basicAuth(this.adminUsername, this.adminPassword).clazz(TokenOutputDto.class).post().build();
        return this;
    }

    public RestService loginManager() {
        this.tokenDto = new RestBuilder<TokenOutputDto>(this.port()).path(contextPath).path(TokenResource.TOKENS).basicAuth("666666001", "p001")
                .clazz(TokenOutputDto.class).post().build();
        return this;
    }

    public RestService loginOperator() {
        this.tokenDto = new RestBuilder<TokenOutputDto>(this.port()).path(contextPath).path(TokenResource.TOKENS).basicAuth("666666005", "p005")
                .clazz(TokenOutputDto.class).post().build();
        return this;
    }

    public RestService loginRegistered() {
        this.tokenDto = new RestBuilder<TokenOutputDto>(this.port()).path(contextPath).path(TokenResource.TOKENS).basicAuth("666666002", "p002")
                .clazz(TokenOutputDto.class).post().build();
        return this;
    }
    
    public RestService loginRegisteredNoPetLost() {
        this.tokenDto = new RestBuilder<TokenOutputDto>(this.port()).path(contextPath).path(TokenResource.TOKENS).basicAuth("666666007", "p007")
                .clazz(TokenOutputDto.class).post().build();
        return this;
    }

    public RestService logout() {
        this.tokenDto = null;
        return this;
    }
    
    public void reLoadTestDB() {
        this.loginAdmin().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).delete().build();
        this.loginAdmin().restBuilder().path(AdminResource.ADMINS).path(AdminResource.DB).body(testFile).post().build();        
    }

    public TokenOutputDto getTokenDto() {
        return tokenDto;
    }

    public void setTokenDto(TokenOutputDto tokenDto) {
        this.tokenDto = tokenDto;
    }

    public String getAdminusername() {
        return adminUsername;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

}
