package learn.dwmh.data;

import learn.dwmh.models.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestRepositoryDouble implements GuestRepository {

    private final List<Guest> guests = new ArrayList<>();

    public final static Guest GUEST = makeGuest();

    public GuestRepositoryDouble() {

        /*
            private int id;
            private String lastName;
            private String firstName;
            private String email;
            private String phone;
            private String stateCode;
         */

        Guest guest = new Guest();

        guest.setId(1);
        guest.setLastName("Lannister");
        guest.setFirstName("Jacob");
        guest.setEmail("jacobl@gmail.com");
        guest.setPhone("909-324-9475");
        guest.setStateCode("53215");

        Guest guestTwo = new Guest();

        guestTwo.setId(1);
        guestTwo.setLastName("Snow");
        guestTwo.setFirstName("Jon");
        guestTwo.setEmail("jjsnow@whatsapp.com");
        guestTwo.setPhone("262-578-9056");
        guestTwo.setStateCode("09364");


        guests.add(guest);
        guests.add(guestTwo);
    }


    @Override
    public Guest findByEmail(String email) {
        return guests.stream()
                .filter(i -> i.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Guest> findAll() {
        List<Guest> toReturn = new ArrayList<>();

        for( Guest toCopy : guests ){
            toReturn.add( toCopy );
        }

        return toReturn;
    }

    public Guest add(Guest guest) {
        guests.add(guest);
        return guest;
    }

    private static Guest makeGuest() {
        Guest guest = new Guest();

        guest.setId(1);
        guest.setLastName("Lannister");
        guest.setFirstName("Cersie");
        guest.setEmail("lanne.cer@gmail.com");
        guest.setPhone("262-473-3846");
        guest.setStateCode("83746");

        return guest;
    }
}

