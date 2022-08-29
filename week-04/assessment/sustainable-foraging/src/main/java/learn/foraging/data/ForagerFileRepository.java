package learn.foraging.data;

import learn.foraging.models.Forager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ForagerFileRepository implements ForagerRepository {

    private final String filePath;

    private static final String HEADER = "id,first_name,last_name,state";

    public ForagerFileRepository(@Value("${dataFilePath}")String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Forager add(Forager forager) throws DataException {///////////////////////////////////////////////////////////
        List<Forager> all = findAll();
        forager.setId(java.util.UUID.randomUUID().toString());
        all.add(forager);
        writeAll(all);
        return forager;
    }

    @Override
    public List<Forager> findAll() {
        ArrayList<Forager> result = new ArrayList<>();
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

    @Override
    public Forager getForagerByLocation(String firstName, String lastName, String state) {
        for(Forager forager : findAll()){
            if(forager.getFirstName().equals(firstName) && forager.getLastName().equals(lastName) && forager.getState().equals(state)){
                return forager;
            }
        }
        return null;
    }

    @Override
    public Forager findById(String id) {
        return findAll().stream()
                .filter(i -> i.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return findAll().stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .collect(Collectors.toList());
    }

    private void writeAll(List<Forager> all) throws DataException{
        try (PrintWriter writer = new PrintWriter(getFilePath())) {//////////////////////////////////////////////////////

            writer.println(HEADER);

            for (Forager item : all) {
                writer.println(serialize(item));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Forager item) {/////////////////////////////////////////////////////////////////////////////
        return String.format("%s,%s,%s,%s",
                item.getId(),
                item.getFirstName(),
                item.getLastName(),
                item.getState());
    }

    private String getFilePath() {
        return Paths.get(filePath).toString();
    }///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private Forager deserialize(String[] fields) {
        Forager result = new Forager();
        result.setId(fields[0]);
        result.setFirstName(fields[1]);
        result.setLastName(fields[2]);
        result.setState(fields[3]);
        return result;
    }
}
