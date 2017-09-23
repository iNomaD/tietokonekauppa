package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("item")
    private Component item;

    @Enumerated(EnumType.STRING)
    @JsonProperty("item_type")
    private Component.Type itemType;

    @JsonProperty("contents")
    private String contents;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("date")
    private Date date;

    public Comment(){};

    public Comment(Component item, String contents, String userName, Date date) {
        this.item = item;
        this.itemType = Component.Type.getType(item);
        this.contents = contents;
        this.userName = userName;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Component getItem() {
        return item;
    }

    public void setItem(Component item) {
        this.item = item;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Component.Type getItemType() {
        return itemType;
    }

    public void setItemType(Component.Type itemType) {
        this.itemType = itemType;
    }
}
