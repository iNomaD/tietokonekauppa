package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.services.CaseService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/admin/cases")
public class CaseController {

    private CaseService caseService = new CaseService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCase(){
        List<Case> list = caseService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getCase(@PathParam("id") long id){
        Case item = caseService.get(id);
        if(item == null){
            throw new DataNotFoundException("Case with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCase (Case item, @Context UriInfo uriInfo){
        if(caseService.isCaseExist(item)){
            throw new DataExistsException("Case already exists");
        }
        item = caseService.add(item);
        if(item == null){
            throw new DataNotFoundException("Case was not created");
        }
        //addLinks(item, uriInfo, CaseController.class, fi.jyu.tietokonekauppa.web.controllers.common.CaseController.class);
        //item = caseService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCase(@PathParam("id") long id, Case item){
        Case current = caseService.get(id);
        if(current == null){
            throw new DataNotFoundException("Case with id "+id+" not found");
        }
        item.setId(id);
        item = caseService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCase (@PathParam("id") long id){
        Case item = caseService.get(id);
        if(item == null){
            throw new DataNotFoundException("Case with id "+id+" not found");
        }
        caseService.remove(id);
        return Response.noContent().build();
    }
}
