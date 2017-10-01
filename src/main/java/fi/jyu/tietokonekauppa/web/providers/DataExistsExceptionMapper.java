package fi.jyu.tietokonekauppa.web.providers;

import fi.jyu.tietokonekauppa.web.ErrorMessage;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Provider // the annotation preregisters our Mapper for JAX-RS to be used
public class DataExistsExceptionMapper implements ExceptionMapper<DataExistsException> {
    @Override
    public Response toResponse(DataExistsException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errors.add("DataExistsException");
        ErrorMessage errorMessage = new ErrorMessage("error", errors, null);
        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorMessage)
                .build();
    }
}