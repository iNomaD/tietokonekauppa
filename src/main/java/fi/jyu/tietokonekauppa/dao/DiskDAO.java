package fi.jyu.tietokonekauppa.dao;

import fi.jyu.tietokonekauppa.models.components.Disk;

import java.util.List;

public interface DiskDAO {
    List<Disk> getAllDisks();
    Disk getDiskById(long id);
    void addDisk(Disk disk);
    void updateDisk(Disk disk);
    void deleteDisk(long id);
}
