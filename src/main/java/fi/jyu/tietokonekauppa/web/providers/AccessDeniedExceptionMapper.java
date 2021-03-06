package fi.jyu.tietokonekauppa.web.providers;

import fi.jyu.tietokonekauppa.web.controllers.common.models.ErrorMessage;
import fi.jyu.tietokonekauppa.web.exceptions.AccessDeniedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider
public class AccessDeniedExceptionMapper implements ExceptionMapper<AccessDeniedException> {
    @Override
    public Response toResponse(AccessDeniedException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errors.add("AccessDeniedException");
        ErrorMessage errorMessage = new ErrorMessage("error", errors, null);
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(errorMessage)
                .build();
    }
}
