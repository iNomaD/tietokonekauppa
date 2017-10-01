package fi.jyu.tietokonekauppa.web.controllers.common.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserToken {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("email")
    private String email;

    @JsonProperty("first_name")
    private String first_name;

    @JsonProperty("last_name")
    private String last_name;

    @JsonProperty("role")
    private List<String> role;

    @JsonProperty("token")
    private String token;

    public UserToken(){};

    public UserToken(Long id, String login, String email, String first_name, String last_name, List<String> role, String token) {
        this.id = id;
        this.login = login;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.role = role;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", role=" + (role != null ? Arrays.toString(role.toArray()) : null)+
                ", token='" + token + '\'' +
                '}';
    }
}
