package fi.jyu.tietokonekauppa.web.controllers.common;

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

public class OrderResource {
    @Autowired
    private OrderService orderService;

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
        orderService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    public OrderResource() {

    }

    public OrderService getOrderService() {
        return orderService;
    }

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
