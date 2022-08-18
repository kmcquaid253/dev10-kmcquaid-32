package learn.unexplained.data;

//import jdk.internal.icu.text.UnicodeSet;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//import static org.junit.jupiter.api.Assertions.fail;

public class EncounterRepositoryDouble implements EncounterRepository {

    List<Encounter> allEncounters = new ArrayList<>();

    public EncounterRepositoryDouble(){
        //building test data
        //this is what we'll expect at the start
        //of every service test




//        this.description = shortDescription;
//        this.occurrences = occurrences;

        Encounter e1 = new Encounter();
        e1.setEncounterId( 100 );
        e1.setWhen( "2/2/2016" );
        e1.setType(EncounterType.UFO);
        e1.setDescription("Test description #1");
        e1.setOccurrences(4);

        allEncounters.add(e1);



        Encounter e2 = new Encounter();
        e2.setEncounterId( 101 );
        e2.setWhen( "3/3/2016" );
        e2.setType(EncounterType.CREATURE);
        e2.setDescription("Test description #2");
        e2.setOccurrences(5);

        allEncounters.add(e2);

        Encounter e3 = new Encounter();
        e3.setEncounterId( 102);
        e3.setWhen( "4/4/2017" );
        e3.setType(EncounterType.CREATURE;
        e3.setDescription("Test description #3");
        e3.setOccurrences(6);

        allEncounters.add(e3);

    }

    @Override
    public List<Encounter> findAll() throws DataAccessException {
        return List.of(new Encounter(2, EncounterType.CREATURE, "1/1/2015", "test description", 1));
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        //


        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        return false;
    }


@Override
    public List<Encounter> findByType(EncounterType type) throws DataAccessException{
        return List.of(new Encounter(3, EncounterType.CREATURE, "2/2/2015", "Test description", 1));
    }

    public boolean update(Encounter encounter) throws DataAccessException {
        return false;
    }

    }
