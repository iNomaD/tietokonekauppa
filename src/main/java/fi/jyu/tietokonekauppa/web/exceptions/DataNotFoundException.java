package fi.jyu.tietokonekauppa.web.exceptions;

public class DataNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -6672553621676928689L;
    public DataNotFoundException(String message){
        super(message);
    }
}
