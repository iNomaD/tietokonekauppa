package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.services.DiskService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

import static fi.jyu.tietokonekauppa.web.Utils.addLinks;

@Path("/admin/disks")
public class DiskController {

    private DiskService diskService = new DiskService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDisks(){
        List<Disk> list = diskService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getDisk(@PathParam("id") long id){
            Disk item = diskService.get(id);
            if(item == null){
                throw new DataNotFoundException("Disk with id "+id+" not found");
            }
            return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDisk (Disk item, @Context UriInfo uriInfo){
        if(diskService.isDiskExist(item)){
            throw new DataExistsException("Disk already exists");
        }
        item = diskService.add(item);
        if(item == null){
            throw new DataNotFoundException("Disk was not created");
        }
        addLinks(item, uriInfo, DiskController.class, fi.jyu.tietokonekauppa.web.controllers.common.DiskController.class);
        item = diskService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDisk(@PathParam("id") long id, Disk item){
        Disk current = diskService.get(id);
        if(current == null){
            throw new DataNotFoundException("Disk with id "+id+" not found");
        }
        item.setId(id);
        item = diskService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDisk (@PathParam("id") long id){
        Disk item = diskService.get(id);
        if(item == null){
            throw new DataNotFoundException("Disk with id "+id+" not found");
        }
        diskService.remove(id);
        return Response.noContent().build();
    }
}
