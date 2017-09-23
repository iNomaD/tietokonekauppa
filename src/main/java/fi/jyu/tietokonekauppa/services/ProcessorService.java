package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Processor;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorService {

    public List<Processor> getAll() {
        // TODO to implement
        return null;
    }

    public List<Processor> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Processor> result = new ArrayList<>();
        List<Processor> all = getAll();
        for(Processor item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public Processor get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isProcessorExist(Processor item) {
        // TODO to implement
        return false;
    }

    public Processor add(Processor item) {
        // TODO to implement
        return null;
    }

    public Processor update(Processor item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
