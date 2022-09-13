package learn.foraging.data;

import learn.foraging.models.Forager;

import java.util.List;

public interface ForagerRepository {
    Forager getForagerByLocation(String firstName, String lastName, String state);

    Forager findById(String id);

    Forager add(Forager forager) throws DataException;

    List<Forager> findAll();

    List<Forager> findByState(String stateAbbr);
}
