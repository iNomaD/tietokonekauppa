package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.GPU;
import fi.jyu.tietokonekauppa.repositories.GPURepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GPUService {
    @Autowired
    private GPURepository gpuRepository;

    public List<GPU> getAll() {
        List<GPU> result = new ArrayList<>();
        gpuRepository.findAll().forEach(result::add);
        return result;
    }

    public List<GPU> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<GPU> result = new ArrayList<>();
        List<GPU> all = getAll();
        for(GPU item : all){
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

    public GPU get(long id) {
        return gpuRepository.findOne(id);
    }

    public boolean isGPUExist(GPU item) {
        return gpuRepository.exists(item.getId());
    }

    public GPU add(GPU item) {
        item.setId(new Long(0));
        return gpuRepository.save(item);
    }

    public GPU update(GPU item) {
        return gpuRepository.save(item);
    }

    public void remove(long id) {
        gpuRepository.delete(id);
    }
}
