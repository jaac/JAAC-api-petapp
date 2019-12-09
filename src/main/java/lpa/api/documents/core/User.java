package lpa.api.documents.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document
public class User {

    @Id
    private String id;

    @Indexed(unique = true)
    private String username;

    private String password;

    private boolean active;

    private String email;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateAdd;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateUpd;

    private Role[] roles;

    private Token token;

    public User() {
        this.dateAdd = new Date();
        this.active = true;
    }

    public User(String username, String password, String email) {
        this();
        this.username = username;
        this.email = email;
        this.setPassword(password);
        this.roles = new Role[]{Role.REGISTERED};
    }


    public User(String username, String password) {
        this(username, password, null);
    }

    public String getId() {
        return id;
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
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role[] getRoles() {
        return roles;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
    }

    private String dateFormat(Date dateAdd) {
        return dateAdd != null ? new SimpleDateFormat("dd-MMM-yyyy").format(this.dateAdd.getTime()) : "null";
    }

    @Override
    public int hashCode() {
        return this.username.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return username.equals(((User) obj).username);
    }

    @Override
    public String toString() {

        return "User [username=" + username + ", password=" + password + ", active=" + active
                + ", email=" + email + ", dateAdd=" + this.dateFormat(dateAdd) + ", dateUpd=" + this.dateFormat(dateUpd) + ", roles="
                + java.util.Arrays.toString(roles) + ", token=" + token + "]";
    }

}
