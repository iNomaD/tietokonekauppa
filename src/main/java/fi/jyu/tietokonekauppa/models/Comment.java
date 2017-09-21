package fi.jyu.tietokonekauppa.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Comment {

    @JsonProperty("id")
    Long id;

    @JsonProperty("item")
    Component item;

    @JsonProperty("contents")
    String contents;

    @JsonProperty("user_name")
    String userName;

    @JsonProperty("date")
    Date date;

    public Comment(Component item, String contents, String userName, Date date) {
        this.item = item;
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
}
