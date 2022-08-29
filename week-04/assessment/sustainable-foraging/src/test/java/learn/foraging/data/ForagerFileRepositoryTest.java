package learn.foraging.data;

import learn.foraging.domain.Result;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForagerFileRepositoryTest {

    private static final String SEED_PATH = "./data/forager-seed.txt";
    private static final String TEST_PATH = "./data/forager-test.txt";

     ForagerFileRepository repo = new ForagerFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH), StandardCopyOption.REPLACE_EXISTING);
    }


    @Test
    void shouldFindThirtyTwo() {
        List<Forager> all = repo.findAll();
        assertEquals(30, all.size());
    }

    @Test
    void shouldFindAllWithSpecificData() {

        //Arrange
        //not much to do, the repo is built
        //and method has no inputs

        //Act
        List<Forager> allForagers = repo.findAll();

        //Assert
        assertEquals( 30, allForagers.size() );

        Forager first = allForagers.get(0);

        assertEquals( "Jilly", first.getFirstName());
        assertEquals( "Sisse", first.getLastName());
        assertEquals( "GA", first.getState());

        Forager last = allForagers.get( allForagers.size() - 1 );

        assertEquals( "Ermengarde", last.getFirstName());
        assertEquals( "Sansom", last.getLastName());
        assertEquals( "NM", last.getState());
    }

    @Test
    void shouldAddValidForager() throws DataException {
        //id,first_name,last_name,state
        Forager forager = new Forager();
        forager.setFirstName("Karen");
        forager.setLastName("Santiago");
        forager.setState("WI");

        Forager actual = repo.add(forager);

        assertNotNull(actual);
    }


    @Test
    void shouldAddValidForagers() throws DataException {
        //"Golden Path" test: good inputs should produce good outputs

        //Arrange
        Forager toAdd = new Forager();

        toAdd.setFirstName( "Karen" );
        toAdd.setLastName( "McQuaid" );
        toAdd.setState("CA");

        //Act
        Forager returnedCust = repo.add( toAdd );

        //Assert
        assertEquals( "Karen", returnedCust.getFirstName());
        assertEquals( "McQuaid", returnedCust.getLastName());
        assertEquals("CA", returnedCust.getState());
    }

    @Test
    void shouldFindThreeStatesWithTheSameState() throws DataException{
        List<Forager> actual = repo.findByState("TX");
        assertEquals(3, actual.size()); // seed data has 2 shareable memories
    }

    @Test
    void shouldFindOneState() throws DataException{
        List<Forager> actual = repo.findByState("OH");
        assertEquals(1, actual.size()); // seed data has 2 shareable memories
    }
}
