package learn.dwmh.data;

import learn.dwmh.models.Host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HostFileRepository implements HostRepository{

    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Host> findByEmail(String email) {
        return findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 4) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    private Host deserialize(String[] fields) {

        /*
                private String id;
                private String lastName;
                private String firstName;

                private BigDecimal standardRate;
                private BigDecimal weekendRate;

                private String email;
                private String phone;
                private String address;
                private String city;
                private String stateCode;
                private String postalCode;
         */

        Host result = new Host();

        result.setId(fields[0]);
        result.setLastName(fields[1]);
        result.setFirstName(fields[2]);

        result.setStandardRate(BigDecimal.valueOf(Long.parseLong(fields[3])));
        result.setWeekendRate(BigDecimal.valueOf(Long.parseLong(fields[4])));

        result.setEmail(fields[5]);
        result.setPhone(fields[6]);
        result.setAddress(fields[7]);
        result.setCity(fields[8]);
        result.setStateCode(fields[9]);
        result.setPostalCode(fields[10]);

        return result;
    }
}
