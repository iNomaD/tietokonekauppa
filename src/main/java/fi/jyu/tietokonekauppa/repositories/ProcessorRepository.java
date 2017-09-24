package fi.jyu.tietokonekauppa.repositories;


import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.Processor;
import org.springframework.data.repository.CrudRepository;

public interface ProcessorRepository extends CrudRepository<Processor, Long> {

}