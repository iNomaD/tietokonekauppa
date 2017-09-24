package fi.jyu.tietokonekauppa.repositories;


import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.Motherboard;
import org.springframework.data.repository.CrudRepository;

public interface MotherboardRepository extends CrudRepository<Motherboard, Long> {

}