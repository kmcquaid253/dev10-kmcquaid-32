package learn.foraging.data;

import learn.foraging.models.Forage;
import learn.foraging.models.Forager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ForagerRepositoryDouble implements ForagerRepository {

    public final static Forager FORAGER = makeForager();

    private final ArrayList<Forager> foragers = new ArrayList<>();

    public ForagerRepositoryDouble() {

            Forager forager = new Forager();

            forager.setId("498604db-b6d6-4599-a503-3d8190fda823");
            forager.setFirstName("Mark");
            forager.setLastName("Cuban");
            forager.setState("WI");

            foragers.add(forager);
            foragers.add(FORAGER);
    }

    @Override
    public Forager getForagerByLocation(String firstName, String lastName, String state) {
        for (Forager forager : foragers) {
            if (forager.getFirstName().equals(firstName) && forager.getLastName().equals(lastName) && forager.getState().equals(state)) {
                return forager;
            }
        }
        return null;
    }

    @Override
    public Forager findById(String id) {
        return foragers.stream()
                .filter(i -> i.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Forager add(Forager forager) throws DataException {
        foragers.add(forager);
        return forager;
    }

    @Override
    public List<Forager> findAll() {
        //we return a copy of the list
        //filled with copies of each element
        //so that way we're not simulating writing to the file
        //by call List.add() or by calling the setters
        //on the objects

        List<Forager> toReturn = new ArrayList<>();

        for( Forager toCopy : foragers ){
            toReturn.add( toCopy );
        }

        return toReturn;
        //return new ArrayList<>(foragers);
    }

    @Override
    public List<Forager> findByState(String stateAbbr) {
        return foragers.stream()
                .filter(i -> i.getState().equalsIgnoreCase(stateAbbr))
                .collect(Collectors.toList());
    }

    private static Forager makeForager() {
        Forager forager = new Forager();

        forager.setId("0e4707f4-407e-4ec9-9665-baca0aabe88c");
        forager.setFirstName("Jilly");
        forager.setLastName("Sisse");
        forager.setState("GA");
        return forager;
    }

}
