package fi.jyu.tietokonekauppa.repositories;


import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.Disk;
import org.springframework.data.repository.CrudRepository;

public interface CaseRepository extends CrudRepository<Case, Long> {

}