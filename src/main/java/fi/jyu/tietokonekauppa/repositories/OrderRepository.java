package fi.jyu.tietokonekauppa.repositories;

import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.components.Case;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {

}