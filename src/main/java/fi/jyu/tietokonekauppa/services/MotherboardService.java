package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Motherboard;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotherboardService {

    public List<Motherboard> getAll() {
        // TODO to implement
        return null;
    }

    public List<Motherboard> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Motherboard> result = new ArrayList<>();
        List<Motherboard> all = getAll();
        for(Motherboard item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public Motherboard get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isMotherboardExist(Motherboard item) {
        // TODO to implement
        return false;
    }

    public Motherboard add(Motherboard item) {
        // TODO to implement
        return null;
    }

    public Motherboard update(Motherboard item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
