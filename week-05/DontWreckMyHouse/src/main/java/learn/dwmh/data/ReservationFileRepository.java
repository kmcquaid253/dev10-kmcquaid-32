package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository{

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    static final String DELIMITER = ",";

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }


//    @Override
//    public List<Reservation> findByHost(String host) {
//        ArrayList<Reservation> result = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(host)))) {
//
//            reader.readLine(); // read header
//
//            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//
//                String[] fields = line.split(",", -1);
//                if (fields.length == 6) {
//                    result.add(deserialize(fields));
//                }
//            }
//        } catch (IOException ex) {
//            // don't throw on read
//        }
//        return result;
//    }


//    @Override
//    public List<Reservation> findAll() {
//        ArrayList<Reservation> result = new ArrayList<>();
//        try (BufferedReader reader = new BufferedReader(new FileReader(directory))) {
//
//            reader.readLine(); // read header
//
//            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
//
//                String[] fields = line.split(",", -1);
//                if (fields.length == 6) {
//                    result.add(deserialize(fields));
//                }
//            }
//        } catch (IOException ex) {
//            // don't throw on read
//        }
//        return result;
//    }

//    public boolean update(Reservation updated) throws DataException {
//        if (updated == null) throw new DataException("Cannot update null Reservation.");
//
//        boolean found = false;
//
//        List<Reservation> allReservations = findAll();
//
//        for (int i = 0; i < allReservations.size(); i++) {
//            if (allReservations.get(i).getHost().getEmail().equals(updated.getHost().getEmail()) &&
//                    allReservations.get(i).getGuest().getEmail().equals(updated.getGuest().getEmail())) {
//                allReservations.set(i, updated);
//                found = true;
//                writeAll(allReservations);
//                break;
//            }
//        }
//        return found;
//    }

    //Needed to update reservation, writes the whole List
//    private void writeAll(List<Reservation> reservations) throws DataException {
//        try (PrintWriter writer = new PrintWriter(getFilePath())) {
//
//            writer.println(HEADER);
//
//            for (Reservation reservation : reservations) {
//                writer.println(serialize(reservation));
//            }
//        } catch (FileNotFoundException ex) {
//            throw new DataException(ex);
//        }
//    }

//    private String serialize(Reservation reservation) {
//        return String.format("%s,%s,%s,%s",
//                .getId(),
//                item.getForager().getId(),
//                item.getItem().getId(),
//                item.getKilograms());
//    }

    private String getFilePath() {
        return Paths.get(directory).toString();
    }


    private Reservation deserialize(String[] fields) {

        /*

            private int id;
            private LocalDate start;
            private LocalDate end;
            private Guest guest;
            private Host host;
            private BigDecimal total;
         */

        Reservation result = new Reservation();

        result.setId(Integer.parseInt(fields[0]));
        result.setStart(LocalDate.parse(fields[1]));
        result.setEnd(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        guest.setId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        result.setTotal(new BigDecimal(fields[4]));

        return result;
    }


    @Override
    public List<Reservation> findByHost(Host host) {
        ArrayList<Reservation> result = new ArrayList<>();
        String path = directory + host.getId() + ".csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) {
        return null;
    }

    @Override
    public boolean update(Reservation reservation) {
        return false;
    }

    @Override
    public boolean delete(Host host, int id) {
        return false;
    }
}
