package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.Processor;
import fi.jyu.tietokonekauppa.services.ProcessorService;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService processorService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessors(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<Processor> list = processorService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessor(@PathParam("id") long id){
        Processor item = processorService.get(id);
        if(item == null){
            throw new DataNotFoundException("Processor with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }
}
