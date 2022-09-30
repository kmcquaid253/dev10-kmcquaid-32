package learn.dwmh.data;

import learn.dwmh.domain.Result;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationFileRepositoryTest {
    static final String TEST_DIR_PATH = "./data/reservation_data_test";
    static final String SEED_DIR_PATH = "./data/reservation_data_seed";
    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);
    final LocalDate start = LocalDate.of(2023, 02, 8);
    final LocalDate end = LocalDate.of(2023, 02, 9);

    Host host = new Host();
    Guest guest = new Guest();

    Reservation reservation = new Reservation();
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

    void makeReservation(Reservation reservation){
        reservation.setId(10);
        reservation.setStart(start);
        reservation.setEnd(end);
        reservation.setTotal(new BigDecimal(200));
        reservation.setGuest(GuestRepositoryDouble.GUEST);
        reservation.setHost(HostRepositoryDouble.HOST);
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
        //1. Find every file in the test folder and delete it.
        // 1.a Create a Path object to the folder.
        Path testFolderPath = Paths.get(TEST_DIR_PATH);

        // 1.b Get File object describing the folder from the Path
        File testFolder = testFolderPath.toFile();

        // 1.c Get an array of all the File objects under the test folder
        File[] testFiles = testFolder.listFiles();

        // 1.d loop through each testFile and delete() it
        for( File testFile : testFiles ){
            testFile.delete();
        }

        //2. Copy every file in the seed folder to the test folder.
        Path seedFolderPath = Paths.get( SEED_DIR_PATH );
        File seedFolder = seedFolderPath.toFile();
        File[] seedFiles = seedFolder.listFiles();

        for( File seedFile : seedFiles ){

            Path seedFilePath = seedFile.toPath();
            Path testFilePath = Paths.get(TEST_DIR_PATH, seedFile.getName());

            Files.copy( seedFilePath, testFilePath, StandardCopyOption.REPLACE_EXISTING );
        }
        makeHost(host);
        makeGuest(guest);
        makeReservation(reservation);
    }

    @Test
    void shouldFindByHost() throws DataException {
        List<Reservation> reservations = repository.findByHost(host.getId());
        assertEquals(12, reservations.size());
    }

    @Test
    void shouldAddNewReservation() throws DataException {
        Reservation reservation = new Reservation();
        reservation.setId(100);
        reservation.setStart(start);
        reservation.setEnd(end);


        Guest guest = new Guest();
        guest.setId(145);
        reservation.setGuest(guest);

        Host host1 = new Host();
        host1.setId("jhklreg-4930-fjai-dhjkwq4234ds-cvw");
        reservation.setHost(host1);

        reservation.setTotal(new BigDecimal(30));

        reservation = repository.add(reservation);

        assertEquals(1, reservation.getId());
    }

    @Test
    void shouldUpdateExistingReservation() throws  DataException{

        reservation.setId(11);
        reservation.setStart(start);
        reservation.setEnd(end);

        Guest guest = new Guest();
        guest.setId(145);
        reservation.setGuest(guest);
        reservation.setHost(host);
        reservation.setTotal(new BigDecimal(30));

        boolean actual = repository.update(reservation);
        assertTrue(actual);
    }
}

