package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.services.OrderService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/admin/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GET
    public Response getOrders(){
        List<Order> list = orderService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") long id){
        Order item = orderService.get(id);
        if(item == null){
            throw new DataNotFoundException("Order with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addOrder (Order item, @Context UriInfo uriInfo){
        if(item.getId() != null && orderService.isOrderExist(item)){
            throw new DataExistsException("Order already exists");
        }
        item = orderService.add(item);
        if(item == null){
            throw new DataNotFoundException("Order was not created");
        }
        item = orderService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrder (@PathParam("id") long id, Order item){
        Order current = orderService.get(id);
        if(current == null){
            throw new DataNotFoundException("Order with id "+id+" not found");
        }
        item.setId(id);
        item = orderService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteOrder (@PathParam("id") long id){
        Order item = orderService.get(id);
        if(item == null){
            throw new DataNotFoundException("Order with id "+id+" not found");
        }
        orderService.remove(id);
        return Response.noContent().build();
    }
}
