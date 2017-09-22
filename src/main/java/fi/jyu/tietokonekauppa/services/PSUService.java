package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.PSU;
import fi.jyu.tietokonekauppa.web.PriceUnits;

import java.util.ArrayList;
import java.util.List;

public class PSUService {

    public List<PSU> getAll() {
        // TODO to implement
        return null;
    }

    public List<PSU> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<PSU> result = new ArrayList<>();
        List<PSU> all = getAll();
        for(PSU item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public PSU get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isPSUExist(PSU item) {
        // TODO to implement
        return false;
    }

    public PSU add(PSU item) {
        // TODO to implement
        return null;
    }

    public PSU update(PSU item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
