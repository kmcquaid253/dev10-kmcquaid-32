package learn.dwmh.data;

import learn.dwmh.models.Guest;

import java.util.List;

public interface GuestRepository {


    Guest findByEmail(String email);

    List<Guest> findAll();
}
