package fi.jyu.tietokonekauppa.web.controllers.common.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class ErrorMessage {

    @JsonProperty("status")
    private String status;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("fields")
    private Map<String, String[]> fields;

    public ErrorMessage(){}

    public ErrorMessage(String status, List<String> errors, Map<String, String[]> fields) {
        this.status = status;
        this.errors = errors;
        this.fields = fields;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Map<String, String[]> getFields() {
        return fields;
    }

    public void setFields(Map<String, String[]> fields) {
        this.fields = fields;
    }
}