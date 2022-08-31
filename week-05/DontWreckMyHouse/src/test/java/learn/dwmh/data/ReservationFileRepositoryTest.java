package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationFileRepositoryTest {

    private static final String SEED_PATH = "./data/reservation-seed.csv";
    private static final String TEST_PATH = "./data/reservation_data_test/";
    ReservationRepository repository = new ReservationFileRepository(TEST_PATH);

    Host host = new Host();
    Guest guest = new Guest();

    void makeHost(Host host){
        //de Clerk,kdeclerkdc@sitemeter.com,(208) 9496329,2 Debra Way,Boise,ID,83757,200,250
        host.setId("2e72f86c-b8fe-4265-b4f1-304dea8762db");
        host.setLastName("de Clerk");
        host.setEmail("kdeclerkdc@sitemeter.com");
        host.setPhone("(208) 9496329");
        host.setAddress("2 Debra Way");
        host.setCity("Boise");
        host.setStateCode("ID");
        host.setPostalCode("83757");
        host.setStandardRate(new BigDecimal(200));
        host.setWeekendRate(new BigDecimal(250));
    }

    void makeGuest(Guest guest){
        //1,Sullivan,Lomas,slomas0@mediafire.com,(702) 7768761,NV
        guest.setId(1);
        guest.setFirstName("Sullivan");
        guest.setLastName("Lomas");
        guest.setEmail("slomas0@mediafire.com");
        guest.setPhone("(702) 7768761");
        guest.setStateCode("NV");
    }

    @BeforeEach
    void setup() throws IOException {
        Files.copy(Paths.get(SEED_PATH),
                Paths.get(TEST_PATH + "2e72f86c-b8fe-4265-b4f1-304dea8762db.csv"),
                StandardCopyOption.REPLACE_EXISTING);

        makeHost(host);
        makeGuest(guest);
    }

    @Test
    void shouldFindTwelveReservations() throws DataException{
        List<Reservation> actual = repository.findAll();

        //assert it's not null
        assertNotNull(actual);
        assertEquals(12, actual.size());// Want 5 guests inside actual
    }
}
