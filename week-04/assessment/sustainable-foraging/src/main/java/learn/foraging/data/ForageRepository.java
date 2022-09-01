package learn.foraging.data;

import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;

import java.time.LocalDate;
import java.util.List;

public interface ForageRepository {
    List<Forage> findByDate(LocalDate date);

    Forage add(Forage forage) throws DataException;

    boolean update(Forage forage) throws DataException;

    List<Forage> findAll(LocalDate date);////////////////////////////////////////////////////////////////////////////////

    Forage getForageByLocation(LocalDate date, String forager, int item);//////////////////////////////////////////////
}
