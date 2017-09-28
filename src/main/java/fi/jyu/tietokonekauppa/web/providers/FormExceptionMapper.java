package fi.jyu.tietokonekauppa.web.providers;

import fi.jyu.tietokonekauppa.web.ErrorMessage;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class FormExceptionMapper implements ExceptionMapper<FormException> {
    @Override
    public Response toResponse(FormException ex) {
        ErrorMessage errorMessage = new ErrorMessage("error", ex.getErrors(), ex.getFields());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}