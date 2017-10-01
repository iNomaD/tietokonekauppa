package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Motherboard;
import fi.jyu.tietokonekauppa.repositories.MotherboardRepository;
import fi.jyu.tietokonekauppa.web.controllers.common.models.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MotherboardService {
    @Autowired
    private MotherboardRepository motherboardRepository;

    public List<Motherboard> getAll() {
        List<Motherboard> result = new ArrayList<>();
        motherboardRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Motherboard> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Motherboard> result = new ArrayList<>();
        List<Motherboard> all = getAll();
        for(Motherboard item : all){
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

    public Motherboard get(long id) {
        return motherboardRepository.findOne(id);
    }

    public boolean isMotherboardExist(Motherboard item) {
        return motherboardRepository.exists(item.getId());
    }

    public Motherboard add(Motherboard item) {
        item.setId(new Long(0));
        return motherboardRepository.save(item);
    }

    public Motherboard update(Motherboard item) {
        return motherboardRepository.save(item);
    }

    public void remove(long id) {
        motherboardRepository.delete(id);
    }
}
