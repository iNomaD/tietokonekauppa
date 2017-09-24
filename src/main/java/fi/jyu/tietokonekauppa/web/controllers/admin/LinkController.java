package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.Link;
import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.services.LinkService;
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

@Path("/admin/links")
@Produces(MediaType.APPLICATION_JSON)
public class LinkController {
    @Autowired
    private LinkService linkService;

    @GET
    public Response getLinks(){
        List<Link> list = linkService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getLink(@PathParam("id") long id){
        Link item = linkService.get(id);
        if(item == null){
            throw new DataNotFoundException("Link with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addLink (Link item, @Context UriInfo uriInfo){
        if(item.getId() != null && linkService.isLinkExist(item)){
            throw new DataExistsException("Link already exists");
        }
        item = linkService.add(item);
        if(item == null){
            throw new DataNotFoundException("Link was not created");
        }
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateLink(@PathParam("id") long id, Link item){
        Link current = linkService.get(id);
        if(current == null){
            throw new DataNotFoundException("Link with id "+id+" not found");
        }
        item.setId(id);
        item = linkService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLink (@PathParam("id") long id){
        Link item = linkService.get(id);
        if(item == null){
            throw new DataNotFoundException("Link with id "+id+" not found");
        }
        linkService.remove(id);
        return Response.noContent().build();
    }
}
