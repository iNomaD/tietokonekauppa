package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.UserService;
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

@Path("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GET
    public Response getUser(){
        List<User> list = userService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") long id){
        User item = userService.get(id);
        if(item == null){
            throw new DataNotFoundException("User with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser (User item, @Context UriInfo uriInfo){
        if(item.getId() != null && userService.isUserExist(item)){
            throw new DataExistsException("User already exists");
        }
        item = userService.add(item);
        if(item == null){
            throw new DataNotFoundException("User was not created");
        }
        item = userService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") long id, User item){
        User current = userService.get(id);
        if(current == null){
            throw new DataNotFoundException("User with id "+id+" not found");
        }
        item.setId(id);
        item = userService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser (@PathParam("id") long id){
        User item = userService.get(id);
        if(item == null){
            throw new DataNotFoundException("User with id "+id+" not found");
        }
        userService.remove(id);
        return Response.noContent().build();
    }
}
