package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.services.CaseService;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cases")
public class CaseController {

    @Autowired
    private CaseService caseService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCases(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<Case> list = caseService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCase(@PathParam("id") long id){
        Case item = caseService.get(id);
        if(item == null){
            throw new DataNotFoundException("Case with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }
}
