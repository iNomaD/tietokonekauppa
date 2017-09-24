package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JsonProperty("components")
    private List<Component> components;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("user_email")
    private String userEmail;

    @JsonProperty("date")
    private Date date;

    public Order(){};

    public Order(List<Component> components, String userName, String userEmail, Date date) {
        this.components = components;
        this.userName = userName;
        this.userEmail = userEmail;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
