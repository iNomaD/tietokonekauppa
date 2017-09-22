package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.GPU;
import fi.jyu.tietokonekauppa.web.PriceUnits;

import java.util.ArrayList;
import java.util.List;

public class GPUService {

    public List<GPU> getAll() {
        // TODO to implement
        return null;
    }

    public List<GPU> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<GPU> result = new ArrayList<>();
        List<GPU> all = getAll();
        for(GPU item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public GPU get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isGPUExist(GPU item) {
        // TODO to implement
        return false;
    }

    public GPU add(GPU item) {
        // TODO to implement
        return null;
    }

    public GPU update(GPU item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
