package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.web.controllers.common.models.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaseService {
    @Autowired
    private CaseRepository caseRepository;

    public List<Case> getAll() {
        List<Case> result = new ArrayList<>();
        caseRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Case> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Case> result = new ArrayList<>();
        List<Case> all = getAll();
        for(Case item : all){
            UnitConverterService.convert(item, priceUnits);
            if(item.getPrice() == null){
                //if data is missing we can pass it
                result.add(item);
            }
            else if(item.getPrice() >= minPrice && item.getPrice() <= maxPrice){
                result.add(item);
            }
        }
        return result;
    }

    public Case get(long id) {
        return caseRepository.findOne(id);
    }

    public boolean isCaseExist(Case item) {
        return caseRepository.exists(item.getId());
    }

    public Case add(Case item) {
        item.setId(new Long(0));
        return caseRepository.save(item);
    }

    public Case update(Case item) {
        return caseRepository.save(item);
    }

    public void remove(long id) {
        caseRepository.delete(id);
    }
}
