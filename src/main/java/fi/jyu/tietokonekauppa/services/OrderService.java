package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.OrderRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import fi.jyu.tietokonekauppa.web.exceptions.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
            //if(result.getUser != null && result.getUser == user)
            result.add(item);
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

    public Order add(List<Component> items, String note, User user) throws DataNotFoundException{
        for(Component component : items){
            Component.Type type = Component.Type.getType(component);
            Component retrieved = componentService.getComponentByIdAndItemType(component.getId(), type);
            if(retrieved == null){
                throw new DataNotFoundException("Component id="+component.getId()+" type="+type.toString()+" not found");
            }
        }
        Order order = new Order(items, "userName???", "userEmail???", new Date(), note);
        return orderRepository.save(order);
        // TODO check whether each of the components are available and decrease current amount by 1
    }

    public Order update(Order item) {
        return orderRepository.save(item);
    }

    public void remove(long id) {
        orderRepository.delete(id);
    }
}
