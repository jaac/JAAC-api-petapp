package lpa.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lpa.api.documents.core.User;
import lpa.api.documents.core.Image;

public class UserDto {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";

    @NotNull
    @Pattern(regexp = USERNAME_PATTERN)
    private String username;

    private String password;

    private String email;

    private String address;

    private Boolean active;

    private Date dateAdd;

    private Date dateUpd;

    public UserDto() {
        // Empty for framework
    }

    public UserDto(String username, String name, String password, String email, String address, Boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.active = active;
    }

    public UserDto(String username) {
        this(username, "name" + username, "pass" + username, null, null, null);
    }

    public UserDto(User user) {
        this.username = String.valueOf(user.getUsername());
        this.email = user.getEmail();
        this.active = user.isActive();
        this.dateAdd = user.getDateAdd();
        this.dateUpd = user.getDateUpd();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email != null) {
            this.email = email.toLowerCase();
        } else {
            this.email = email;
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpd() {
        return dateUpd;
    }


    @Override
    public String toString() {
        return "UserDto [username=" + username + ", password=" + password + ", email=" + email
                + ", address=" + address + ", active=" + active + ", dateAdd=" + dateAdd + "]";
    }

}
