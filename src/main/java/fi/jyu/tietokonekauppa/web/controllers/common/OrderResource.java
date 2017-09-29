package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.services.OrderService;
import fi.jyu.tietokonekauppa.web.StringStatus;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/orders")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(){
        // TODO implement
        return null;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder (@QueryParam("notes") String notes, List<Component> items, @Context UriInfo uriInfo){
        System.out.println("DEBUG contents "+notes);
        System.out.println("DEBUG items "+items);
        if(notes == null){
            List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
            Map<String, String[]> fields = new HashMap<String, String[]>() {{
                if(notes == null) put("notes", new String[]{"not provided"});
            }};
            throw new FormException(errors, fields);
        }
        // TODO rework method according to API reference
        // TODO we should get get user from SecurityContext
        /*
        if(item.getId() != null && orderService.isOrderExist(item)){
            throw new DataExistsException("Order already exists");
        }
        item = orderService.add(item);
        if(item == null){
            throw new DataNotFoundException("Order was not created");
        }
        orderService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
        */
        return Response.ok().entity(new StringStatus("ok")).build();
    }
}
