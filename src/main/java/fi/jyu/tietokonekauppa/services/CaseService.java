package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaseService {

    public List<Case> getAll() {
        // TODO to implement
        return null;
    }

    public List<Case> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Case> result = new ArrayList<>();
        List<Case> all = getAll();
        for(Case item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public Case get(long id) {
        // TODO to implement
        return null;
    }

    public boolean isCaseExist(Case item) {
        // TODO to implement
        return false;
    }

    public Case add(Case item) {
        // TODO to implement
        return null;
    }

    public Case update(Case item) {
        // TODO to implement
        return null;
    }

    public void remove(long id) {
        // TODO to implement
    }
}
