package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.PSU;
import fi.jyu.tietokonekauppa.repositories.PSURepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PSUService {
    @Autowired
    private PSURepository psuRepository;

    public List<PSU> getAll() {
        List<PSU> result = new ArrayList<>();
        psuRepository.findAll().forEach(result::add);
        return result;
    }

    public List<PSU> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<PSU> result = new ArrayList<>();
        List<PSU> all = getAll();
        for(PSU item : all){
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

    public PSU get(long id) {
        return psuRepository.findOne(id);
    }

    public boolean isPSUExist(PSU item) {
        return psuRepository.exists(item.getId());
    }

    public PSU add(PSU item) {
        item.setId(new Long(0));
        return psuRepository.save(item);
    }

    public PSU update(PSU item) {
        return psuRepository.save(item);
    }

    public void remove(long id) {
        psuRepository.delete(id);
    }
}
