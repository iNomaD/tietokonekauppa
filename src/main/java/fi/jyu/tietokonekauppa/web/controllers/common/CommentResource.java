package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.services.CommentService;
import fi.jyu.tietokonekauppa.web.StringStatus;
import fi.jyu.tietokonekauppa.web.exceptions.AccessDeniedException;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

@Path("/{type:cases|disks|gpus|motherboards|processors|psus|rams}/{id}/comments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Autowired
    private CommentService commentService;

    @Context
    private SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("id") long id){
        List<Comment> comments = commentService.getAllComments(id);
        return Response.ok().entity(comments).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment (@PathParam("id") long itemId, @PathParam("type") String type,
                                @QueryParam("contents") String contents,
                                @Context UriInfo uriInfo){
        if (!securityContext.isUserInRole(User.ADMIN) && !securityContext.isUserInRole(User.CUSTOMER)){
            throw new WebApplicationException("Not authorized", 401);
        }

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

        User user = (User) securityContext.getUserPrincipal();
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
    public Response deleteComment(@PathParam("id") long id,@PathParam("comment_id") long commentId){
        if (!securityContext.isUserInRole(User.ADMIN) && !securityContext.isUserInRole(User.CUSTOMER)){
            throw new WebApplicationException("Not authorized", 401);
        }
        User user = (User) securityContext.getUserPrincipal();
        Comment comment = commentService.getComment(id, commentId);
        if(comment == null){
            throw new DataNotFoundException("Comment with id "+commentId+" not found");
        }

        // if user is not admin and the comment does not belong to him
        if(!user.getRole().contains(User.ADMIN) &&
            !(comment.getUserName() != null && comment.getUserName().equals(user.getLogin()))){
            throw new AccessDeniedException("Resource is not available for this user");
        }
        commentService.remove(commentId);
        return Response.ok().entity(new StringStatus("ok")).build();
    }
}
