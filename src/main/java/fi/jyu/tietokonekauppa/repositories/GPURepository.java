package fi.jyu.tietokonekauppa.repositories;


import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.GPU;
import org.springframework.data.repository.CrudRepository;

public interface GPURepository extends CrudRepository<GPU, Long> {

}