package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.OrderRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        orderRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Order> getAllOrders(long itemId) {
        List<Order> result = new ArrayList<>();
        List<Order> all = getAll();
        for(Order item : all){
            if(item.getId() == itemId){
                result.add(item);
            }
        }
        return result;
    }

    public Order get(long id) {
        return orderRepository.findOne(id);
    }

    public boolean isOrderExist(Order item) {
        return orderRepository.exists(item.getId());
    }

    public Order add(Order item) {
        item.setId(new Long(0));
        return orderRepository.save(item);
    }

    public Order update(Order item) {
        return orderRepository.save(item);
    }

    public void remove(long id) {
        orderRepository.delete(id);
    }
}
