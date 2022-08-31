package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostFileRepository implements HostRepository{

    private final String filePath;

    public HostFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Host findByEmail(String email) {
        return findAll().stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Host> findAll() {
        ArrayList<Host> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 10) {
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
                private String email;
                private String phone;
                private String address;
                private String city;
                private String stateCode;
                private String postalCode;
                private BigDecimal standardRate;
                private BigDecimal weekendRate;

         */

        Host result = new Host();

        result.setId(fields[0]);
        result.setLastName(fields[1]);
        result.setEmail(fields[2]);
        result.setPhone(fields[3]);
        result.setAddress(fields[4]);
        result.setCity(fields[5]);
        result.setStateCode(fields[6]);
        result.setPostalCode(fields[7]);
        result.setStandardRate( new BigDecimal(fields[8]));
        result.setWeekendRate( new BigDecimal(fields[9]));

        return result;
    }
}
