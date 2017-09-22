package fi.jyu.tietokonekauppa.web.exceptions;

public class DataExistsException extends RuntimeException {
    private static final long serialVersionUID = -6672553621676928688L;
    public DataExistsException(String message){
        super(message);
    }
}