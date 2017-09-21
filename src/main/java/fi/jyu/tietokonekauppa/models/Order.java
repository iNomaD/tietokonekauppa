package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class Order {

    @JsonProperty("id")
    Long id;

    @JsonProperty("components")
    List<Component> components;

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("user_email")
    String userEmail;

    @JsonProperty("date")
    Date date;

    public Order(List<Component> components, String userName, String userEmail, Date date) {
        this.components = components;
        this.userName = userName;
        this.userEmail = userEmail;
        this.date = date;
    }

    public List<Component> getComponents() {
        return components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
