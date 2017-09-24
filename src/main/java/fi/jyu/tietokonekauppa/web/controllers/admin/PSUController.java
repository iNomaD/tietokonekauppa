package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.PSU;
import fi.jyu.tietokonekauppa.services.PSUService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/admin/psus")
public class PSUController {

    private PSUService PSUService = new PSUService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPSU(){
        List<PSU> list = PSUService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getPSU(@PathParam("id") long id){
        PSU item = PSUService.get(id);
        if(item == null){
            throw new DataNotFoundException("PSU with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase (PSU item, @Context UriInfo uriInfo){
        if(PSUService.isPSUExist(item)){
            throw new DataExistsException("PSU already exists");
        }
        item = PSUService.add(item);
        if(item == null){
            throw new DataNotFoundException("PSU was not created");
        }
        //addLinks(item, uriInfo, PSUController.class, fi.jyu.tietokonekauppa.web.controllers.common.PSUController.class);
        //item = PSUService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePSU(@PathParam("id") long id, PSU item){
        PSU current = PSUService.get(id);
        if(current == null){
            throw new DataNotFoundException("PSU with id "+id+" not found");
        }
        item.setId(id);
        item = PSUService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePSU (@PathParam("id") long id){
        PSU item = PSUService.get(id);
        if(item == null){
            throw new DataNotFoundException("PSU with id "+id+" not found");
        }
        PSUService.remove(id);
        return Response.noContent().build();
    }
}
