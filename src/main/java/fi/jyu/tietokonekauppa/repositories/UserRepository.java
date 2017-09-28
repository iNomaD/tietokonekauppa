package fi.jyu.tietokonekauppa.repositories;

import fi.jyu.tietokonekauppa.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
