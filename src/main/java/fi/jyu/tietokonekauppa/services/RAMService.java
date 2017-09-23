package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.RAM;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RAMService {

    public List<RAM> getAll() {
        // TODO to implement
        return null;
    }

    public List<RAM> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<RAM> result = new ArrayList<>();
        List<RAM> all = getAll();
        for(RAM item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public RAM get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isRAMExist(RAM item) {
        // TODO to implement
        return false;
    }

    public RAM add(RAM item) {
        // TODO to implement
        return null;
    }

    public RAM update(RAM item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
