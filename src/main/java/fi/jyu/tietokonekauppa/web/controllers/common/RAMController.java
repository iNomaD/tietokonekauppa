package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.RAM;
import fi.jyu.tietokonekauppa.services.RAMService;
import fi.jyu.tietokonekauppa.web.controllers.common.models.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/rams")
public class RAMController {

    @Autowired
    private RAMService RAMService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRAMs(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<RAM> list = RAMService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRAM(@PathParam("id") long id){
        RAM item = RAMService.get(id);
        if(item == null){
            throw new DataNotFoundException("RAM with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }
}
