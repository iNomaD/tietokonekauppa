package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;

import java.util.ArrayList;
import java.util.List;

public class CommentService {

    public List<Comment> getAll() {
        // TODO to implement
        return null;
    }

    public List<Comment> getAllComments(Component.Type type, long itemId) {
        List<Comment> result = new ArrayList<>();
        List<Comment> comments = getAll();
        for(Comment c : comments){
            if(c.getItemType() == type){
                result.add(c);
            }
        }
        return result;
    }

    public Comment get(long id) {
        // TODO to implement
        return null;
    }

    public Comment getComment(Component.Type type, long itemId, long commentId) {
        return get(commentId);
    }

    public boolean isCommentExist(Comment item) {
        // TODO to implement
        return false;
    }

    public Comment add(Comment item) {
        // TODO to implement
        return null;
    }

    public Comment update(Comment item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
