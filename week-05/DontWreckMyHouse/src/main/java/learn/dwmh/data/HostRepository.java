package learn.dwmh.data;

import learn.dwmh.models.Host;

import java.util.Arrays;
import java.util.List;

public interface HostRepository {

    List<Host> findByEmail(String email);

    List<Host> findAll();
}
