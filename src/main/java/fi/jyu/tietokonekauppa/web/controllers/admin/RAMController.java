package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.RAM;
import fi.jyu.tietokonekauppa.services.LinkService;
import fi.jyu.tietokonekauppa.services.RAMService;
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

@Path("/admin/rams")
public class RAMController {

    @Autowired
    private RAMService RAMService;

    @Autowired
    private LinkService linkService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRAM(){
        List<RAM> list = RAMService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getRAM(@PathParam("id") long id){
        RAM item = RAMService.get(id);
        if(item == null){
            throw new DataNotFoundException("RAM with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase (RAM item, @Context UriInfo uriInfo){
        if(item.getId() != null && RAMService.isRAMExist(item)){
            throw new DataExistsException("RAM already exists");
        }
        item = RAMService.add(item);
        if(item == null){
            throw new DataNotFoundException("RAM was not created");
        }
        linkService.addLinks(item, uriInfo, RAMController.class, fi.jyu.tietokonekauppa.web.controllers.common.RAMController.class);
        item = RAMService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateRAM(@PathParam("id") long id, RAM item){
        RAM current = RAMService.get(id);
        if(current == null){
            throw new DataNotFoundException("RAM with id "+id+" not found");
        }
        item.setId(id);
        item = RAMService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRAM (@PathParam("id") long id){
        RAM item = RAMService.get(id);
        if(item == null){
            throw new DataNotFoundException("RAM with id "+id+" not found");
        }
        RAMService.remove(id);
        return Response.noContent().build();
    }
}
