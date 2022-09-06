package learn.dwmh.data;

import learn.dwmh.models.Host;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HostRepositoryDouble implements HostRepository{

    List<Host> host = new ArrayList<>();

    public final static Host HOST = makeHost();

    public HostRepositoryDouble(){
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

        Host h1 = new Host();

        h1.setId("fbjhekr-78975-ncedn-mewklf");
        h1.setLastName("Byrd");
        h1.setFirstName("Jamie");
        h1.setStandardRate(BigDecimal.valueOf(40.00));
        h1.setWeekendRate(BigDecimal.valueOf(90.00));
        h1.setEmail("jamieb@gmail.com");
        h1.setPhone("414-364-0947");
        h1.setAddress("1539 N 78th st");
        h1.setCity("Milwaukee");
        h1.setStateCode("WI");
        h1.setPostalCode("95348");

        Host h2 = new Host();

        h2.setId("suthekr-86931-cbqor-eutopd");
        h2.setLastName("Spencer");
        h2.setFirstName("Diana");
        h2.setStandardRate(BigDecimal.valueOf(80.00));
        h2.setWeekendRate(BigDecimal.valueOf(120.00));
        h2.setEmail("dianas@gmail.com");
        h2.setPhone("262-842-4678");
        h2.setAddress("1023 E 22th st");
        h2.setCity("Huston");
        h2.setStateCode("TX");
        h2.setPostalCode("12094");

        host.add(h1);
        host.add(h2);


    }

    @Override
    public Host findByEmail(String email) {
        return host.stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Host> findAll() {
        List<Host> toReturn = new ArrayList<>();

        for( Host toCopy : host ){
            toReturn.add( toCopy );
        }

        return toReturn;
    }

    private static Host makeHost() {
        Host host = new Host();

        host.setId("jekbnf-478n-djnondn3-094j76");
        host.setLastName("Martinez");
        host.setFirstName("Jose");
        host.setStandardRate(BigDecimal.valueOf(80.00));
        host.setWeekendRate(BigDecimal.valueOf(100.00));
        host.setEmail("jose.martinez@gmail.com");
        host.setPhone("414-654-1287");
        host.setAddress("92 W 122th st");
        host.setCity("Milwaukee");
        host.setStateCode("WI");
        host.setPostalCode("37485");

        return host;
    }
}
