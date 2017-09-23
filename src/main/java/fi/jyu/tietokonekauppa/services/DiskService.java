package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.repositories.DiskRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiskService {
    @Autowired
    private DiskRepository diskRepository;

    public List<Disk> getAll() {
        List<Disk> result = new ArrayList<>();
        diskRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Disk> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Disk> result = new ArrayList<>();
        List<Disk> all = getAll();
        for(Disk item : all){
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

    public Disk get(long id) {
        return diskRepository.findOne(id);
    }

    public boolean isDiskExist(Disk item) {
        return diskRepository.exists(item.getId());
    }

    public Disk add(Disk item) {
        item.setId(new Long(0));
        return diskRepository.save(item);
    }

    public Disk update(Disk item) {
        return diskRepository.save(item);
    }

    public void remove(long id) {
        diskRepository.delete(id);
    }
}
