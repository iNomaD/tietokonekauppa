package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.services.DiskService;
import fi.jyu.tietokonekauppa.web.PriceUnits;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/disks")
public class DiskController {
    DiskService diskService = new DiskService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisks(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<Disk> list = diskService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

}
