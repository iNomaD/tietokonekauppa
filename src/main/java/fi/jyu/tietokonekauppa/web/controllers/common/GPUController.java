package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.GPU;
import fi.jyu.tietokonekauppa.services.GPUService;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/gpus")
public class GPUController {

    @Autowired
    private GPUService gpuService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisks(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<GPU> list = gpuService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }
}
