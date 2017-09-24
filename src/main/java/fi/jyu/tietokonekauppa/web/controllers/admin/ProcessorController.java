package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.Processor;
import fi.jyu.tietokonekauppa.services.LinkService;
import fi.jyu.tietokonekauppa.services.ProcessorService;
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

@Path("/admin/processors")
public class ProcessorController {

    @Autowired
    private ProcessorService ProcessorService;

    @Autowired
    private LinkService linkService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessor(){
        List<Processor> list = ProcessorService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getProcessor(@PathParam("id") long id){
        Processor item = ProcessorService.get(id);
        if(item == null){
            throw new DataNotFoundException("Processor with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase (Processor item, @Context UriInfo uriInfo){
        if(item.getId() != null && ProcessorService.isProcessorExist(item)){
            throw new DataExistsException("Processor already exists");
        }
        item = ProcessorService.add(item);
        if(item == null){
            throw new DataNotFoundException("Processor was not created");
        }
        linkService.addLinks(item, uriInfo, ProcessorController.class, fi.jyu.tietokonekauppa.web.controllers.common.ProcessorController.class);
        item = ProcessorService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProcessor(@PathParam("id") long id, Processor item){
        Processor current = ProcessorService.get(id);
        if(current == null){
            throw new DataNotFoundException("Processor with id "+id+" not found");
        }
        item.setId(id);
        item = ProcessorService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteProcessor (@PathParam("id") long id){
        Processor item = ProcessorService.get(id);
        if(item == null){
            throw new DataNotFoundException("Processor with id "+id+" not found");
        }
        ProcessorService.remove(id);
        return Response.noContent().build();
    }
}
