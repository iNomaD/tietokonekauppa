package fi.jyu.tietokonekauppa.web.providers;

import fi.jyu.tietokonekauppa.web.controllers.common.models.ErrorMessage;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

@Provider // the annotation preregisters our Mapper for JAX-RS to be used
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {
    @Override
    public Response toResponse(DataNotFoundException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        errors.add("DataNotFoundException");
        ErrorMessage errorMessage = new ErrorMessage("error", errors, null);
        return Response.status(Response.Status.OK)
                .entity(errorMessage)
                .build();
    }
}