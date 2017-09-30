package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComponentService {

    @Autowired
    CaseService caseService;
    @Autowired
    DiskService diskService;
    @Autowired
    GPUService gpuService;
    @Autowired
    MotherboardService motherboardService;
    @Autowired
    ProcessorService processorService;
    @Autowired
    PSUService psuService;
    @Autowired
    RAMService ramService;

    public Component getComponentByIdAndItemType(long id, Component.Type type){
        Component item = null;
        if(type != null) {
            switch (type) {
                case Case:
                    item = caseService.get(id);
                    break;
                case Disk:
                    item = diskService.get(id);
                    break;
                case GPU:
                    item = gpuService.get(id);
                    break;
                case Motherboard:
                    item = motherboardService.get(id);
                    break;
                case Processor:
                    item = processorService.get(id);
                    break;
                case PSU:
                    item = psuService.get(id);
                    break;
                case RAM:
                    item = ramService.get(id);
                    break;
            }
        }
        return item;
    }
}
