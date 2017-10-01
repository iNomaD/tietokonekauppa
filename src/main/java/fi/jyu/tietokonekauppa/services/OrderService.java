package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.OrderRepository;
import fi.jyu.tietokonekauppa.repositories.UserRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataExistsException;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import fi.jyu.tietokonekauppa.web.exceptions.FormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ComponentService componentService;

    public List<Order> getAll() {
        List<Order> result = new ArrayList<>();
        orderRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Order> getAll(User user) {
        List<Order> result = new ArrayList<>();
        for(Order item : getAll()){
            if (user != null && item.getUserName() != null && item.getUserName().equals(user.getLogin()))
                result.add(item);
        }
        return result;
    }

    public Order get(long id) {
        return orderRepository.findOne(id);
    }

    public Order get(long id, User user) {
        Order order = orderRepository.findOne(id);
        if(user != null && user.getLogin().equals(order.getUserName()) || user.getRole().contains(User.ADMIN)){
            return order;
        }
        return null;
    }

    public boolean isOrderExist(Order item) {
        return orderRepository.exists(item.getId());
    }

    public Order add(Order item) {
        item.setId(new Long(0));
        return orderRepository.save(item);
    }

    public Order add(List<Component> items, String note, User user) throws DataNotFoundException{
        List<Component> recognizedItems = new ArrayList<>();
        for(Component component : items){
            Component.Type type = Component.Type.getType(component);
            Component retrieved = componentService.getComponentByIdAndItemType(component.getId(), type);
            if(retrieved == null){
                throw new DataNotFoundException("Component id="+component.getId()+" type="+type.toString()+" not found");
            }
            int amount = component.getAmountAvailable();
            if(amount <= 0){
                throw new DataExistsException("["+component.getId()+"]"+component.getName() +" not available");
            }
            recognizedItems.add(retrieved);
        }
        Order order = new Order(recognizedItems, user.getName(), user.getEmail(), new Date(), note);
        order = orderRepository.save(order);

        // decrease amount available after successful creation of order
        for(Component component : recognizedItems){
            component.setAmountAvailable(component.getAmountAvailable() - 1);
            componentService.update(component);
        }
        return order;
    }

    public Order update(Order item) {
        return orderRepository.save(item);
    }

    public void remove(long id) {
        orderRepository.delete(id);
    }
}
