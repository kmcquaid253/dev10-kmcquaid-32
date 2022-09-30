package learn.dwmh.domain;

import learn.dwmh.data.GuestRepositoryDouble;
import learn.dwmh.data.HostRepositoryDouble;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GuestServiceTest {
    GuestService service;

    @BeforeEach
    void setup() {
        GuestRepositoryDouble repository = new GuestRepositoryDouble();
        service = new GuestService(repository);
    }

    @Test
    void shouldFindFirstGuestByEmail(){
        List<Guest> guest = service.findByEmail("jacobl@gmail.com");
        assertNotNull(guest);
    }

    @Test
    void shouldFindSecondGuestByEmail(){
        List<Guest> guest = service.findByEmail("jjsnow@whatsapp.com");
        assertNotNull(guest);
    }
}
