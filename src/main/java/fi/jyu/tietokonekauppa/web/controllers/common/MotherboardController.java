package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.Motherboard;
import fi.jyu.tietokonekauppa.services.MotherboardService;
import fi.jyu.tietokonekauppa.web.controllers.common.models.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/motherboards")
public class MotherboardController {

    @Autowired
    private MotherboardService motherboardService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMotherboards(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<Motherboard> list = motherboardService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMotherboard(@PathParam("id") long id){
        Motherboard item = motherboardService.get(id);
        if(item == null){
            throw new DataNotFoundException("Motherboard with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }
}
