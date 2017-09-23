package fi.jyu.tietokonekauppa.repositories;

import fi.jyu.tietokonekauppa.models.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
