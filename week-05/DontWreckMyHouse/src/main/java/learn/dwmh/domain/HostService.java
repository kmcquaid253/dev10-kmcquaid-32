package learn.dwmh.domain;

import learn.dwmh.data.HostRepository;
import learn.dwmh.models.Host;

import java.util.List;
import java.util.stream.Collectors;

public class HostService {

    private final HostRepository repository;

    public HostService(HostRepository repository) {
        this.repository = repository;
    }
    public List<Host> findByEmail(String email) {
        return repository.findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }
}
