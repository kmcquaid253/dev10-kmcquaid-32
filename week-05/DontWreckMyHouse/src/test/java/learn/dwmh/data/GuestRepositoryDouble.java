package learn.dwmh.data;

import learn.dwmh.models.Guest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GuestRepositoryDouble implements GuestRepository {

    private final ArrayList<Guest> guests = new ArrayList<>();

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


        guests.add(guest);
    }


    @Override
    public Guest findByEmail(String email) {
        return null;
    }

    @Override
    public List<Guest> findAll() {
        List<Guest> toReturn = new ArrayList<>();

        for( Guest toCopy : guests ){
            toReturn.add( toCopy );
        }

        return toReturn;
        //return new ArrayList<>(foragers);
    }

    public Guest add(Guest guest) {
        guests.add(guest);
        return guest;
    }
}

