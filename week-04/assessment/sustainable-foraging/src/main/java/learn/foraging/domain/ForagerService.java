package learn.foraging.domain;

import learn.foraging.data.DataException;
import learn.foraging.data.ForagerRepository;
import learn.foraging.models.*;
import learn.foraging.models.Forager;
import learn.foraging.models.Forager;
import learn.foraging.models.Forager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ForagerService {

    private final ForagerRepository repository;

    public ForagerService(ForagerRepository repository) {
        this.repository = repository;
    }

    public List<Forager> findByState(String stateAbbr) {
        return repository.findByState(stateAbbr);
    }

    public List<Forager> findByLastName(String prefix) {
        return repository.findAll().stream()
                .filter(i -> i.getLastName().startsWith(prefix))
                .collect(Collectors.toList());
    }

    public Result<Forager> add(Forager forager) throws DataException {
        Result<Forager> result = validate(forager);

        if (!result.isSuccess()) {
            return result;
        }

        result.setPayload(repository.add(forager));

        return result;
    }

    private Result<Forager> validate(Forager forager) {

        Result<Forager> result = validateNulls(forager);

        if (!result.isSuccess()) {
            return result;
        }


        return result;
    }

    private Result<Forager> validateNulls(Forager forager) {
        Result<Forager> result = new Result<>();

        if (forager == null) {
            result.addErrorMessage("Nothing to save.");
            return result;
        }

        Forager existingPanel = repository.getForagerByLocation(forager.getFirstName(), forager.getLastName(), forager.getState());

        if(existingPanel != null){
            result.addErrorMessage("Cannot add duplicate Foragers");
            return result;
        }

        if (forager.getFirstName() == null) {
            result.addErrorMessage("Forager name is required.");
        }

        if (forager.getLastName() == null) {
            result.addErrorMessage("Forager last name is required.");
        }

        if (forager.getState() == null) {
            result.addErrorMessage("State is required.");
        }
        return result;
    }
}
