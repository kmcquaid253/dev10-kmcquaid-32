package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.domain.Result;
import learn.dwmh.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.*;

public class GuestFileRepositoryTest {

    private static final String SEED_PATH = "./data/guest-seed.csv";
    private static final String TEST_PATH = "./data/guest-test.csv";

    GuestFileRepository repo = new GuestFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH), Paths.get(TEST_PATH), StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindFiveGuests() throws DataException{
        List<Guest> actual = repo.findAll();

        //assert it's not null
        assertNotNull(actual);
        assertEquals(5, actual.size());// Want 5 guests inside actual
    }

    @Test
    void shouldFindAllWithSpecificData() {

        //Arrange
        //not much to do, the repo is built
        //and method has no inputs

        //Act
        List<Guest> allGuest = repo.findAll();

        //Assert
        assertEquals( 5, allGuest.size() );

        Guest first = allGuest.get(0);
        /*
            guest.setId(1);
            guest.setFirstName("Jacob");
            guest.setLastName("Lannister");
            guest.setEmail("jacobl@gmail.com");
            guest.setPhone("909-324-9475");
            guest.setStateCode("53215");
         */

        assertEquals( 1, first.getId());
        assertEquals( "Sullivan", first.getFirstName());
        assertEquals( "Lomas", first.getLastName());
        assertEquals( "slomas0@mediafire.com", first.getEmail());
        assertEquals( "(702) 7768761", first.getPhone());
        assertEquals( "NV", first.getStateCode());

        Guest last = allGuest.get( allGuest.size() - 1 );

        assertEquals( 5, last.getId());
        assertEquals( "Berta", last.getFirstName());
        assertEquals( "Seppey", last.getLastName());
        assertEquals( "bseppey4@yahoo.com", last.getEmail());
        assertEquals( "(202) 2668098", last.getPhone());
        assertEquals( "DC", last.getStateCode());
    }

    @Test
    void shouldFindExistingGuest(){
        Guest olympie = repo.findByEmail("ogecks1@dagondesign.com");
        assertNotNull(olympie);
        assertEquals("Olympie", olympie.getFirstName());
    }

    @Test
    void shouldNotFindNonExistingGuest(){
        Guest nope = repo.findByEmail("this-email-doesnt-exit@gmail.com");
        assertNull(nope);

    }

}
