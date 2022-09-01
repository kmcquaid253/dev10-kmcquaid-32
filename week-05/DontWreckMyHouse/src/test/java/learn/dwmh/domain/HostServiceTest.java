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
    void shouldFindFirstHostByEmail(){
        //service.findByEmail("jamieb@gmail.com");

    }

    @Test
    void shouldFindSecondHostByEmail(){
        List<Host> host =service.findByEmail("dianas@gmail.co");
        assertNotNull(host);
    }
}
