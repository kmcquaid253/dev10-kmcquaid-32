package learn.unexplained.data;

import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.io.*;
import java.util.*;

public class EncounterFileRepository implements EncounterRepository {

    private static final String DELIMITER = ",";
    private static final String DELIMITER_REPLACEMENT = "@@@";
    private static final String HEADER = "encounter_id,type,when,description,occurrences";
    private final String filePath;

    public EncounterFileRepository(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Encounter> findAll() throws DataAccessException {

        ArrayList<Encounter> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            reader.readLine(); // skip header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                Encounter encounter = deserialize(line);
                if (encounter != null) {
                    result.add(encounter);
                }
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }

        return result;
    }

    @Override
    public Encounter add(Encounter encounter) throws DataAccessException {
        List<Encounter> all = findAll();
        encounter.setEncounterId(getNextId(all));
        all.add(encounter);
        writeAll(all);
        return encounter;
    }

    @Override
    public boolean deleteById(int encounterId) throws DataAccessException {
        List<Encounter> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEncounterId() == encounterId) {
                all.remove(i);
                writeAll(all);
                return true;
            }
        }
        return false;
    }

    private int getNextId(List<Encounter> allEncounters) {
        int nextId = 0;
        for (Encounter e : allEncounters) {
            nextId = Math.max(nextId, e.getEncounterId());
        }
        return nextId + 1;
    }

    private void writeAll(List<Encounter> encounters) throws DataAccessException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println(HEADER);
            for (Encounter e : encounters) {
                writer.println(serialize(e));
            }
        } catch (IOException ex) {
            throw new DataAccessException(ex.getMessage(), ex);
        }
    }

    private String serialize(Encounter encounter) {
        return String.format("%s,%s,%s,%s,%s",
                encounter.getEncounterId(),
                encounter.getType(),
                clean(encounter.getWhen()),
                clean(encounter.getDescription()),
                encounter.getOccurrences());
    }

    private Encounter deserialize(String line) {
        String[] fields = line.split(DELIMITER, -1);
        if (fields.length == 5) {
            Encounter encounter = new Encounter();
            encounter.setEncounterId(Integer.parseInt(fields[0]));
            encounter.setType(EncounterType.valueOf(fields[1]));
            encounter.setWhen(restore(fields[2]));
            encounter.setDescription(restore(fields[3]));
            encounter.setOccurrences(Integer.parseInt(fields[4]));
            return encounter;
        }
        return null;
    }

    private String clean(String value) {
        return value.replace(DELIMITER, DELIMITER_REPLACEMENT);
    }

    private String restore(String value) {
        return value.replace(DELIMITER_REPLACEMENT, DELIMITER);
    }


    /*
            Name: findByType
            Inputs: EncounterType
            Output: List<Encounter>
            Description: Returns all encounters with a given type as a list.

            Name: update
            Inputs: Encounter
            Output: boolean
            Description: Updates an existing encounter if it exists and returns true. If it doesn't exist, returns false.

            Since EncounterFileRepository implements EncounterRepository, the method contracts must also be added to the interface.
     */
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        ArrayList<Encounter> encounter = new ArrayList<>();
        for (Encounter encounterType : findAll()) {
            if (encounterType.getType() == type) {
                encounter.add(encounterType);
            }

        }   return encounter;
    }

    public boolean update(Encounter encounter) throws DataAccessException {
        List<Encounter> all = findAll();
        for (int i = 0; i < all.size(); i++) {
            if (all.get(i).getEncounterId() == encounter.getEncounterId()) {
                all.set(i, encounter); // replace
                writeAll(all);
                return true;
            }
        }
        return false;
    }
}
