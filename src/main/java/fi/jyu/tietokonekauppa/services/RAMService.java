package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.models.components.RAM;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.DiskRepository;
import fi.jyu.tietokonekauppa.repositories.RAMRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RAMService {
    @Autowired
    private RAMRepository ramRepository;

    public List<RAM> getAll() {
        List<RAM> result = new ArrayList<>();
        ramRepository.findAll().forEach(result::add);
        return result;
    }

    public List<RAM> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<RAM> result = new ArrayList<>();
        List<RAM> all = getAll();
        for(RAM item : all){
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

    public RAM get(long id) {
        return ramRepository.findOne(id);
    }

    public boolean isRAMExist(RAM item) {
        return ramRepository.exists(item.getId());
    }

    public RAM add(RAM item) {
        item.setId(new Long(0));
        return ramRepository.save(item);
    }

    public RAM update(RAM item) {
        return ramRepository.save(item);
    }

    public void remove(long id) {
        ramRepository.delete(id);
    }
}
