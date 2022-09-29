package learn.dwmh.data;

import learn.dwmh.models.Guest;
import learn.dwmh.models.Host;
import learn.dwmh.models.Reservation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReservationFileRepository implements ReservationRepository{

    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    static final String DELIMITER = ",";

    public ReservationFileRepository(@Value("${dataDirectory}")String directory) {
        this.directory = directory;
    }

    public boolean update(Reservation updated) throws DataException {
        //if( updated == null ) throw new DataException("Cannot update null Reservation.");
        //boolean found = false;
        List<Reservation> allReservations = findByHost(updated.getHost().getId());

        for (int i = 0; i < allReservations.size(); i++) {
            if (allReservations.get(i).getId() == updated.getId()){

                    allReservations.set(i, updated);
                    //found = true;
                    writeAll(allReservations, updated.getHost().getId());
                    //break;
                    return true;
            }
        }
        return false;
    }

    //Needed to update reservation, writes the whole List
    private void writeAll(List<Reservation> reservations, String hostId) throws DataException {
        Path filepath = Paths.get(directory, hostId + ".csv");
        String path = filepath.toString();
        try( PrintWriter writer = new PrintWriter(new FileWriter(path))){

            writer.println(HEADER);

            for (Reservation toCovert : reservations) {
                writer.println(serialize(toCovert));
            }
        } catch (IOException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Reservation toCovert) {
        return String.format("%s,%s,%s,%s,%s",
                toCovert.getId(),
                toCovert.getStart(),
                toCovert.getEnd(),
                toCovert.getGuest().getId(),
                toCovert.getTotal());
    }


    private Reservation deserialize(String[] fields) {

        /*
            private int id;
            private LocalDate start;
            private LocalDate end;
            private BigDecimal total;
            private Guest guest;
            private Host host;
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
    public List<Reservation> findByHost(String hostId) {
        ArrayList<Reservation> result = new ArrayList<>();
        Path filepath = Paths.get(directory, hostId + ".csv");
        String path = filepath.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {

            reader.readLine(); // read header

            for (String line = reader.readLine(); line != null; line = reader.readLine()) {

                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    Reservation toAdd = deserialize((fields));
                    Host host = new Host();
                    host.setId(hostId);
                    toAdd.setHost(host);

                    result.add(toAdd);
                }
            }
        } catch (IOException ex) {
            // don't throw on read
        }
        return result;
    }

    @Override
    public Reservation add(Reservation toAdd) throws DataException {
        List<Reservation> currentReservations = findByHost(toAdd.getHost().getId());//gets all existing reservations by host

        int maxId = 0;
        for( Reservation currentReservation : currentReservations ){
            if( currentReservation.getId() > maxId ){//loop through to find the max id
                maxId = currentReservation.getId();
            }
        }

        int newId = maxId + 1;//add one to the new id

        //set the id in toAdd
        toAdd.setId( newId );

        currentReservations.add( toAdd );//add reservation to list

        //  write the whole list
        writeAll( currentReservations, toAdd.getHost().getId());

        //return the hydrated Reservation object
        return toAdd;
    }



    @Override
    public boolean delete(int id, String hostId) throws DataException {
        List<Reservation> reservation = findByHost(hostId);
        for (int i = 0; i < reservation.size(); i++){
            if(reservation.get(i).getId() == id)
            {
                reservation.remove(i);
                writeAll(reservation, hostId);
                return true;
            }
        }
       return false;
    }
}