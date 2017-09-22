package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.components.Motherboard;
import fi.jyu.tietokonekauppa.services.MotherboardService;
import fi.jyu.tietokonekauppa.web.PriceUnits;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/motherboards")
public class MotherboardController {

    private MotherboardService MotherboardService = new MotherboardService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMotherboards(@QueryParam("min_price") @DefaultValue("0") int minPrice, @QueryParam("max_price") @DefaultValue("1000000") int maxPrice,
                               @QueryParam("price_units") PriceUnits priceUnits){
        List<Motherboard> list = MotherboardService.getAll(minPrice, maxPrice, priceUnits);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}/comments")
    public CommentController getCommentResource(){
        return new CommentController(Component.Type.Motherboard);
    }
}
