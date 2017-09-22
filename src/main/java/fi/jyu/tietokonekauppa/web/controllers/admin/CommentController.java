package fi.jyu.tietokonekauppa.web.controllers.admin;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.services.CommentService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/admin/comments")
public class CommentController {

    private CommentService commentService = new CommentService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(){
        List<Comment> list = commentService.getAll();
        return Response.ok().entity(list).build();
    }

    @GET
    @Path("/{id}")
    public Response getComment(@PathParam("id") long id){
        Comment item = commentService.get(id);
        if(item == null){
            throw new DataNotFoundException("Comment with id "+id+" not found");
        }
        return Response.ok().entity(item).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment (Comment item, @Context UriInfo uriInfo){
        if(commentService.isCommentExist(item)){
            throw new DataExistsException("Comment already exists");
        }
        item = commentService.add(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateComment(@PathParam("id") long id, Comment item){
        Comment current = commentService.get(id);
        if(current == null){
            throw new DataNotFoundException("Comment with id "+id+" not found");
        }
        item.setId(id);
        item = commentService.update(item);
        return Response.ok().entity(item).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteComment (@PathParam("id") long id){
        Comment item = commentService.get(id);
        if(item == null){
            throw new DataNotFoundException("Comment with id "+id+" not found");
        }
        commentService.remove(id);
        return Response.noContent().build();
    }
}