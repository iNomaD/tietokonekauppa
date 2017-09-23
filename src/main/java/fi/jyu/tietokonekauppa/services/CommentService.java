package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Comment;
import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    DiskService diskService;

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
                /*
                Component item = null;
                switch (c.getItemType()){
                    case Disk:
                        item = diskService.get(itemId);
                }
                if(item != null){
                    result.add(c);
                }*/
                result.add(c);
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
            /*
            Component item = null;
            switch (comment.getItemType()){
                case Disk:
                    item = diskService.get(itemId);
            }
            if(item != null){
                return comment;
            }*/
            return comment;
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

    public Comment update(Comment item) {
        return commentRepository.save(item);
    }

    public void remove(long id) {
        commentRepository.delete(id);
    }
}
