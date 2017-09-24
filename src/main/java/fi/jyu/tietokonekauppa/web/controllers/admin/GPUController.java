package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.GPU;
import fi.jyu.tietokonekauppa.services.GPUService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/admin/gpus")
public class GPUController {

    private GPUService gpuService = new GPUService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGPU(){
        List<GPU> list = gpuService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getGPU(@PathParam("id") long id){
        GPU item = gpuService.get(id);
        if(item == null){
            throw new DataNotFoundException("GPU with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGPU (GPU item, @Context UriInfo uriInfo){
        if(gpuService.isGPUExist(item)){
            throw new DataExistsException("GPU already exists");
        }
        item = gpuService.add(item);
        if(item == null){
            throw new DataNotFoundException("GPU was not created");
        }
        //linkService.addLinks(item, uriInfo, GPUController.class, fi.jyu.tietokonekauppa.web.controllers.common.GPUController.class);
        //item = gpuService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateGPU(@PathParam("id") long id, GPU item){
        GPU current = gpuService.get(id);
        if(current == null){
            throw new DataNotFoundException("GPU with id "+id+" not found");
        }
        item.setId(id);
        item = gpuService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteGPU (@PathParam("id") long id){
        GPU item = gpuService.get(id);
        if(item == null){
            throw new DataNotFoundException("GPU with id "+id+" not found");
        }
        gpuService.remove(id);
        return Response.noContent().build();
    }
}
