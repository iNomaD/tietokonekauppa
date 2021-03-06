package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.OrderService;
import fi.jyu.tietokonekauppa.web.exceptions.AccessDeniedException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Path("/orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrders(){
        if (!securityContext.isUserInRole(User.ADMIN) && !securityContext.isUserInRole(User.CUSTOMER)){
            throw new AccessDeniedException("Not available for User: " + securityContext.getUserPrincipal());
        }
        User user = (User) securityContext.getUserPrincipal();
        List<Order> list = orderService.getAll(user);
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") long id){
        if (!securityContext.isUserInRole(User.ADMIN) && !securityContext.isUserInRole(User.CUSTOMER)){
            throw new AccessDeniedException("Not available for User: " + securityContext.getUserPrincipal());
        }
        User user = (User) securityContext.getUserPrincipal();
        Order order = orderService.get(id, user);
        if(order == null){
            throw new AccessDeniedException("Not available for User: " + securityContext.getUserPrincipal());
        }
        return Response.ok().entity(order).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder (@QueryParam("notes") String notes, List<Component> items, @Context UriInfo uriInfo){
        if (!securityContext.isUserInRole(User.ADMIN) && !securityContext.isUserInRole(User.CUSTOMER)){
            throw new AccessDeniedException("Not available for User: " + securityContext.getUserPrincipal());
        }
        System.out.println("DEBUG notes "+notes);
        System.out.println("DEBUG items "+items);
        if(items != null){
            for(Component c : items){
                System.out.println("DEBUG "+c);
            }
        }
        if(notes == null){
            notes = "Accepted";
        }
        if(items == null || items.isEmpty()){
            List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
            Map<String, String[]> fields = new HashMap<String, String[]>() {{
                put("items", new String[]{"not provided"});
            }};
            throw new FormException(errors, fields);
        }
        User user =  (User) securityContext.getUserPrincipal();
        Order item;
        try {
            item = orderService.add(items, notes, user);
        }
        catch (DataNotFoundException e){
            throw e;
        }
        if(item == null){
            throw new DataNotFoundException("Order was not created");
        }
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }
}
