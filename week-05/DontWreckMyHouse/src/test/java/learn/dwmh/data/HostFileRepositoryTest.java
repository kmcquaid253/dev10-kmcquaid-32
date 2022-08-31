package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HostFileRepositoryTest {

    private static final String SEED_PATH = "./data/host-seed.csv";
    private static final String TEST_PATH = "./data/host-test.csv";

    private HostFileRepository repository = new HostFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH),
                Paths.get(TEST_PATH),
                StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindSeventeenHosts() throws DataException{
        List<Host> actual = repository.findAll();

        //assert it's not null
        assertNotNull(actual);
        assertEquals(17, actual.size());// Want 5 guests inside actual
    }

    @Test
    void shouldFindExistingHost(){
        Host rhodes = repository.findByEmail("krhodes1@posterous.com");
        assertNotNull(rhodes);
        assertEquals("Rhodes", rhodes.getLastName());
    }

    @Test
    void shouldNotFindNonExistingHost(){
        Host nope = repository.findByEmail("this-email-doesnt-exit@gmail.com");
        assertNull(nope);

    }
}
