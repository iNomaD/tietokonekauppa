package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.Order;
import fi.jyu.tietokonekauppa.models.User;
import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.OrderRepository;
import fi.jyu.tietokonekauppa.repositories.UserRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
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
    private UserRepository userRepository;

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
//            if(result.getUser != null && result.getUser == user)
            if (item.getUserName() != null && item.getUserName().equals(user.getLogin()))
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
            int amount = component.getAmountAvailable();
            if(amount == 0){
                List<String> errors = new ArrayList<String>() {{ add("form exception"); }};
                Map<String, String[]> fields = new HashMap<String, String[]>() {{
                    put(component.getName(), new String[]{"not available"});
                }};
                throw new FormException(errors, fields);
            }else {
                component.setAmountAvailable(component.getAmountAvailable() - 1);
            }
        }
        Order order = new Order(items, user.getName(), user.getEmail(), new Date(), note);
        return orderRepository.save(order);
    }

    public Order update(Order item) {
        return orderRepository.save(item);
    }

    public void remove(long id) {
        orderRepository.delete(id);
    }
}
