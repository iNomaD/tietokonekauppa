package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.components.Case;
import fi.jyu.tietokonekauppa.models.components.Disk;
import fi.jyu.tietokonekauppa.models.components.Processor;
import fi.jyu.tietokonekauppa.repositories.CaseRepository;
import fi.jyu.tietokonekauppa.repositories.DiskRepository;
import fi.jyu.tietokonekauppa.repositories.ProcessorRepository;
import fi.jyu.tietokonekauppa.web.PriceUnits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProcessorService {
    @Autowired
    private ProcessorRepository processorRepository;

    public List<Processor> getAll() {
        List<Processor> result = new ArrayList<>();
        processorRepository.findAll().forEach(result::add);
        return result;
    }

    public List<Processor> getAll(int minPrice, int maxPrice, PriceUnits priceUnits) {
        List<Processor> result = new ArrayList<>();
        List<Processor> all = getAll();
        for(Processor item : all){
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

    public Processor get(long id) {
        return processorRepository.findOne(id);
    }

    public boolean isProcessorExist(Processor item) {
        return processorRepository.exists(item.getId());
    }

    public Processor add(Processor item) {
        item.setId(new Long(0));
        return processorRepository.save(item);
    }

    public Processor update(Processor item) {
        return processorRepository.save(item);
    }

    public void remove(long id) {
        processorRepository.delete(id);
    }
}
