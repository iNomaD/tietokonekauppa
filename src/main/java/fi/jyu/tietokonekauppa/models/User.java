package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.security.auth.Subject;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Principal {

    public static final String ADMIN = "admin";
    public static final String CUSTOMER = "customer";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("login")
    private String login;

    @NotNull
    @JsonProperty("email")
    private String email;

    @NotNull
    @JsonProperty("password")
    private String password;

    @NotNull
    @JsonProperty("first_name")
    private String first_name;

    @NotNull
    @JsonProperty("last_name")
    private String last_name;

    @ElementCollection(fetch = FetchType.EAGER)
    @JsonProperty("role")
    private List<String> role;

    public User(){};

    public User(String login, String email, String password, String first_name, String last_name, List<String> role) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<String> getRole() {
        return role;
    }

    public void setRole(List<String> role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return this.first_name + " " + this.last_name;
    }

    @Override
    public boolean implies(Subject subject) {
        return false;
    }
}
