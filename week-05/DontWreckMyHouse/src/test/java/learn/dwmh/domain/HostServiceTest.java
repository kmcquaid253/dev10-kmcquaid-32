package learn.dwmh.domain;

import learn.dwmh.data.HostRepositoryDouble;
import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HostServiceTest {

    HostService service;

    @BeforeEach
    void setup() {
        HostRepositoryDouble repository = new HostRepositoryDouble();
        service = new HostService(repository);
    }

    @Test
    void shouldFindSecondHostByEmail(){
        Host host = new Host();

        host.setId("jekbnf-478n-djnondn3-094j76");
        host.setLastName("Martinez");
        host.setStandardRate(BigDecimal.valueOf(80.00));
        host.setWeekendRate(BigDecimal.valueOf(100.00));
        host.setEmail("jose.martinez@gmail.com");
        host.setPhone("414-654-1287");
        host.setAddress("92 W 122th st");
        host.setCity("Milwaukee");
        host.setStateCode("WI");
        host.setPostalCode("37485");

        List<Host> hostOne =service.findByEmail("jose.martinez@gmail.com");
        assertNotNull(hostOne);
    }
}
