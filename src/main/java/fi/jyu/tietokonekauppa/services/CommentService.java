package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ComponentService componentService;

    public List<Comment> getAll() {
        List<Comment> result = new ArrayList<>();
        commentRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Comment> getAllComments(long itemId) {
        List<Comment> result = new ArrayList<>();
        List<Comment> comments = getAll();
        for(Comment c : comments){
            // check id
            if(c.getItem().getId() == itemId){
                // check type
                Component item = componentService.getComponentByIdAndItemType(itemId, c.getItemType());
                if(item != null || c.getItemType() == null){
                    result.add(c);
                }
            }
        }
        return result;
    }

    public Comment get(long id) {
        return commentRepository.findOne(id);
    }

    public Comment getComment(long itemId, long commentId) {
        Comment comment = commentRepository.findOne(commentId);
        // check id
        if(comment != null && comment.getItem().getId()==itemId){
            // check type
            Component item = componentService.getComponentByIdAndItemType(itemId, comment.getItemType());
            if(item != null || comment.getItemType() == null){
                return comment;
            }
        }
        return null;
    }

    public boolean isCommentExist(Comment item) {
        return commentRepository.exists(item.getId());
    }

    public Comment add(Comment item) {
        item.setId(new Long(0));
        return commentRepository.save(item);
    }

    public Comment getCommentByContents(String contents) {
        List<Comment> all = getAll();
        for(Comment item : all){
            if(item.getContents().equals(contents)){
                return item;
            }
        }
        return null;
    }

    public Comment add(long itemId, String itemType, String contents, User user){
        Component.Type type = Component.Type.getType(itemType.substring(0, itemType.length() - 1).toLowerCase());
        Component item = componentService.getComponentByIdAndItemType(itemId, type);
        if(item != null){
            Comment comment = new Comment(item, contents, user.getLogin(), new Date());
            comment.setItemType(type);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment update(Comment item) {
        return commentRepository.save(item);
    }

    public void remove(long id) {
        commentRepository.delete(id);
    }
}
