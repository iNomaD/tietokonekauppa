package fi.jyu.tietokonekauppa.web.controllers.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StringStatus {
    @JsonProperty("status")
    private String status;

    public StringStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
