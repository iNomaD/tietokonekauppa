package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.CommentService;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.*;

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

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment (@PathParam("id") long itemId, @PathParam("type") String type,
                                @QueryParam("contents") String contents,
                                @Context UriInfo uriInfo){
        System.out.println("DEBUG contents "+contents);
        if(contents == null){
            List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
            Map<String, String[]> fields = new HashMap<String, String[]>() {{
                put("contents", new String[]{"not provided"});
            }};
            throw new FormException(errors, fields);
        }
        if(commentService.getCommentByContents(contents) != null){
            throw new DataExistsException("Comment with such content already exists.");
        }

        // TODO rework method according to API reference
        // TODO we should get get user from SecurityContext
        User user = null;
        Comment item = commentService.add(itemId, type, contents, user);
        if(item == null){
            throw new DataNotFoundException("Comment was not created");
        }
        item.setItemType(Component.Type.getType(item.getItem()));
        commentService.update(item);
        String newId = String.valueOf(item.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
        return Response.created(uri).entity(item).build();
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

    @DELETE
    @Path("/{comment_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") long id,
                               @PathParam("comment_id") long commentId){
        // TODO implement
        return null;
    }
}
