package fi.jyu.tietokonekauppa.dao;

import fi.jyu.tietokonekauppa.models.components.Disk;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class DiskDAOImpl implements DiskDAO {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<Disk> getAllDisks() {
        return null;
    }

    @Override
    public Disk getDiskById(long id) {
        return null;
    }

    @Override
    public void addDisk(Disk disk) {

    }

    @Override
    public void updateDisk(Disk disk) {

    }

    @Override
    public void deleteDisk(long id) {

    }
}
