package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.services.CommentService;
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

@Path("/{type:cases|disks|gpus|motherboards|processors|psus|rams}/{id}/comments")
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("id") long id){
        List<Comment> comments = commentService.getAllComments(id);
        return Response.ok().entity(comments).build();
    }

    @GET
    @Path("/{comment_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComment(@PathParam("id") long id,
                              @PathParam("comment_id") long commentId){
        Comment comment = commentService.getComment(id, commentId);
        if(comment == null){
            throw new DataNotFoundException("Comment with id "+commentId+" not found");
        }
        return Response.ok().entity(comment).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment (Comment item, @Context UriInfo uriInfo){
        if(item.getId() != null && commentService.isCommentExist(item)){
            throw new DataExistsException("Comment already exists");
        }
        item = commentService.add(item);
        if(item == null){
            throw new DataNotFoundException("Comment was not created");
        }
        item.setItemType(Component.Type.getType(item.getItem()));
        commentService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
    }
}
