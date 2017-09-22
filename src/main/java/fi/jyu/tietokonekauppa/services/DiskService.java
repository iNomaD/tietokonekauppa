package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.web.PriceUnits;

import java.util.ArrayList;
import java.util.List;

public class DiskService {

    public List<Disk> getAll() {
        // TODO to implement
        return null;
    }

    public List<Disk> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Disk> result = new ArrayList<>();
        List<Disk> all = getAll();
        for(Disk item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public Disk get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isDiskExist(Disk item) {
        // TODO to implement
        return false;
    }

    public Disk add(Disk item) {
        // TODO to implement
        return null;
    }

    public Disk update(Disk item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}