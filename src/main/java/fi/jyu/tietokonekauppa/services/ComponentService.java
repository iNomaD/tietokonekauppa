package fi.jyu.tietokonekauppa.services;

import fi.jyu.tietokonekauppa.models.Component;
import fi.jyu.tietokonekauppa.models.components.*;
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

    public Component update(Component item){
        Component.Type type = Component.Type.getType(item);
        if(type != null) {
            switch (type) {
                case Case:
                    return caseService.update((Case)item);
                case Disk:
                    return diskService.update((Disk)item);
                case GPU:
                    return gpuService.update((GPU)item);
                case Motherboard:
                    return motherboardService.update((Motherboard)item);
                case Processor:
                    return processorService.update((Processor)item);
                case PSU:
                    return psuService.update((PSU)item);
                case RAM:
                    return ramService.update((RAM)item);
            }
        }
        return null;
    }
}
