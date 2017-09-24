package fi.jyu.tietokonekauppa.repositories;


import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.PSU;
import org.springframework.data.repository.CrudRepository;

public interface PSURepository extends CrudRepository<PSU, Long> {

}