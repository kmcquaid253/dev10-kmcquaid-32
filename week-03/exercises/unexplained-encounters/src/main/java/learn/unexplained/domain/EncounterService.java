package learn.unexplained.domain;

import learn.unexplained.data.DataAccessException;
import learn.unexplained.data.EncounterRepository;
import learn.unexplained.models.Encounter;
import learn.unexplained.models.EncounterType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataAccessException {
        return repository.findAll();
    }

    public EncounterResult add(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);
        if (!result.isSuccess()) {
            return result;
        }

        // check for duplicate
        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                return result;
            }
        }

        encounter = repository.add(encounter);
        result.setPayload(encounter);
        return result;
    }

    private EncounterResult validate(Encounter encounter) {

        EncounterResult result = new EncounterResult();
        if (encounter == null) {
            result.addErrorMessage("encounter cannot be null");
            return result;
        }

        if (encounter.getWhen() == null || encounter.getWhen().trim().length() == 0) {
            result.addErrorMessage("when is required");
        }

        if (encounter.getDescription() == null || encounter.getDescription().trim().length() == 0) {
            result.addErrorMessage("description is required");
        }

        if (encounter.getOccurrences() <= 0) {
            result.addErrorMessage("occurrences must be greater than 0");
        }

        return result;
    }

    //Add three new method
    //findByType
    public List<Encounter> findByType(EncounterType type) throws DataAccessException {
        ArrayList<Encounter> encounter = new ArrayList<>();
        for (Encounter encounterType : findAll()) {
            if (encounterType.getType() == type) {
                encounter.add(encounterType);
            }

        }   return encounter;
    }

    //update
    public EncounterResult update(Encounter encounter) throws DataAccessException {
        EncounterResult result = validate(encounter);

        if (encounter.getEncounterId() <= 0) {
            result.addErrorMessage("Encounter `id` is required.");
        }

        if( encounter == null ){
            result.addErrorMessage( "Cannot add null encounter.");
        }
        if(encounter.getWhen() == null){
            result.addErrorMessage("When is required");
        }

        if(encounter.getOccurrences() < 0){
            result.addErrorMessage("Occu");
        }

        if(encounter.getDescription() == null){
            result.addErrorMessage("Description is required");
        }

        // check for duplicate
        List<Encounter> encounters = repository.findAll();
        for (Encounter e : encounters) {
            if (Objects.equals(encounter.getWhen(), e.getWhen())
                    && Objects.equals(encounter.getType(), e.getType())
                    && Objects.equals(encounter.getDescription(), e.getDescription())) {
                result.addErrorMessage("duplicate encounter is not allowed");
                return result;
            }
        }

        if (result.isSuccess()) {
            if (repository.update(encounter)) {
                result.setPayload(encounter);
            } else {
                String message = String.format("Encounter id %s was not found.", encounter.getEncounterId());
                result.addErrorMessage(message);
            }
        }
        return result;
    }

    //deleteById
    public EncounterResult deleteById(int encounterId) throws DataAccessException {
        EncounterResult results = new EncounterResult();
        if (!repository.deleteById(encounterId)) {
            String message = String.format("Encounter id %s was not found.", encounterId);
            results.addErrorMessage(message);
        }
        return results;
    }

}
