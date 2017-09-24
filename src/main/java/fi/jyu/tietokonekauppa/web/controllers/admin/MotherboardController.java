package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.Motherboard;
import fi.jyu.tietokonekauppa.services.LinkService;
import fi.jyu.tietokonekauppa.services.MotherboardService;
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

@Path("/admin/motherboards")
public class MotherboardController {

    @Autowired
    private MotherboardService MotherboardService;

    @Autowired
    private LinkService linkService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMotherboard(){
        List<Motherboard> list = MotherboardService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getMotherboard(@PathParam("id") long id){
        Motherboard item = MotherboardService.get(id);
        if(item == null){
            throw new DataNotFoundException("Motherboard with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase (Motherboard item, @Context UriInfo uriInfo){
        if(item.getId() != null && MotherboardService.isMotherboardExist(item)){
            throw new DataExistsException("Motherboard already exists");
        }
        item = MotherboardService.add(item);
        if(item == null){
            throw new DataNotFoundException("Motherboard was not created");
        }
        linkService.addLinks(item, uriInfo, MotherboardController.class, fi.jyu.tietokonekauppa.web.controllers.common.MotherboardController.class);
        item = MotherboardService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMotherboard(@PathParam("id") long id, Motherboard item){
        Motherboard current = MotherboardService.get(id);
        if(current == null){
            throw new DataNotFoundException("Motherboard with id "+id+" not found");
        }
        item.setId(id);
        item = MotherboardService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMotherboard (@PathParam("id") long id){
        Motherboard item = MotherboardService.get(id);
        if(item == null){
            throw new DataNotFoundException("Motherboard with id "+id+" not found");
        }
        MotherboardService.remove(id);
        return Response.noContent().build();
    }
}
