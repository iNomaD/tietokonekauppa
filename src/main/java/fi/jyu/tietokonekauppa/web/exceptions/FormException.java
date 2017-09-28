package fi.jyu.tietokonekauppa.web.exceptions;

import java.util.*;

public class FormException extends RuntimeException{
    private static final long serialVersionUID = -6672553621376928689L;
    List<String> errors;
    Map<String, String[]> fields;

    public FormException(List<String> errors, Map<String, String[]> fields){
        super();
        this.errors = errors;
        this.fields = fields;
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