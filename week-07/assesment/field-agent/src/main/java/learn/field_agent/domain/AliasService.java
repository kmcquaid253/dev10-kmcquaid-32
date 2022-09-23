package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.DataException;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AliasService {
    @Autowired
    private final AliasRepository repository;

    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public Alias findById(Integer id) {
        return repository.findById(id);
    }

    public Result deleteById(Integer id) throws DataException {
        Result toReturn = new Result();

        if(!repository.deleteById(id)){
            toReturn.addMessage("Could not find alias with Id", ResultType.NOT_FOUND);
        }

        return toReturn;
    }

    public Result<Alias> add(Alias alias) {

        Result<Alias> result = validate(alias);

        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() != 0) {
            result.addMessage("Alias Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        alias = repository.add(alias);
        result.setPayload(alias);
        return result;
    }

    public Result<Alias> update(Alias alias) {

        Result<Alias> result = validate(alias);
        if (!result.isSuccess()) {
            return result;
        }

        if (alias.getAliasId() <= 0) {
            result.addMessage("Alias Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(alias)) {
            String msg = String.format("AliasId: %s, not found", alias.getAliasId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }


    //VALIDATION
    private Result<Alias> validate(Alias alias) {
        Result<Alias> result = new Result<>();
        if (alias == null) {
            result.addMessage("alias cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(alias.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if (alias.getAgentId() <= 0) {
            result.addMessage("Alias agent_Id is required", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            List<Alias> existingAlias = repository.findByName(alias.getName());
            List<Alias> existingPersona = repository.findByPersona(alias.getPersona());

            for (Alias al : existingAlias) {
                if (al.getAliasId() != alias.getAliasId() &&
                        al.getName().equalsIgnoreCase(alias.getName())) {
                    if (Validations.isNullOrBlank(alias.getPersona())) {
                        result.addMessage("Persona is required for duplicate names", ResultType.INVALID);
                    }
                }
            }
                for (Alias al : existingPersona) {
                    if (al.getAliasId() != alias.getAliasId() &&
                            al.getPersona().equalsIgnoreCase(alias.getPersona())) {
                        result.addMessage("Persona cannot be duplicate", ResultType.INVALID);
                    }
                }
        }
        return result;
    }
}
