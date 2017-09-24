package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.PSU;
import fi.jyu.tietokonekauppa.services.PSUService;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/psus")
public class PSUController {

    @Autowired
    private PSUService PSUService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPSUs(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<PSU> list = PSUService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPSU(@PathParam("id") long id){
        PSU item = PSUService.get(id);
        if(item == null){
            throw new DataNotFoundException("Disk with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }
}
