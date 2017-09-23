package fi.jyu.tietokonekauppa.web.controllers.common;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.services.CommentService;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

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

    public CommentResource() {

    }

    public CommentService getCommentService() {
        return commentService;
    }

    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
