package fi.jyu.tietokonekauppa.web.exceptions;

public class AccessDeniedException extends RuntimeException{
    private static final long serialVersionUID = -6672553621676928388L;
    public AccessDeniedException(String message){
        super(message);
    }
}
