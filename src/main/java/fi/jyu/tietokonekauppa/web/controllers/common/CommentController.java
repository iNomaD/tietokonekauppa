package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.services.CommentService;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/")
public class CommentController {

    private CommentService commentService = new CommentService();
    Component.Type type;

    public CommentController(Component.Type type){
        this.type = type;
    }

    @GET
    public Response getComments(@PathParam("id") long itemId){
        List<Comment> comments = commentService.getAllComments(type, itemId);
        return Response.ok().entity(comments).build();
    }

    @GET
    @Path("/{comment_id}")
    public Response getComment(@PathParam("id") long itemId,
                              @PathParam("comment_id") long commentId){
        Comment comment = commentService.getComment(type, itemId, commentId);
        if(comment == null){
            throw new DataNotFoundException("Comment with id "+commentId+" not found");
        }
        return Response.ok().entity(comment).build();
    }
}
