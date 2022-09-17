package learn.field_agent.domain;

import learn.field_agent.data.DataException;
import learn.field_agent.data.SecurityClearanceRepository;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SecurityClearanceService {

    @Autowired
    private final SecurityClearanceRepository repository;

    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }

    public List<SecurityClearance> getAllSecurityClearance() {
        return repository.getAllSecurityClearance();
    }

    public SecurityClearance findById(Integer id) {
        return repository.findById(id);
    }

    public Result<SecurityClearance> add(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = validate(securityClearance);
        if (!result.isSuccess()) {
            return result;
        }

        if (securityClearance.getSecurityClearanceId() != 0) {
            result.addMessage("securityClearanceId cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        securityClearance = repository.add(securityClearance);
        result.setPayload(securityClearance);
        return result;
    }

    public Result<SecurityClearance> update(SecurityClearance sc) {

        Result<SecurityClearance> result = validate(sc);
        if (!result.isSuccess()) {
            return result;
        }

        if (sc.getSecurityClearanceId() <= 0) {
            result.addMessage("Security Clearance Id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.update(sc)) {
            String msg = String.format("SecurityClearanceId: %s, not found", sc.getSecurityClearanceId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public Result deleteById(Integer id) throws DataException {

        Result toReturn = new Result();

        if(!repository.deleteById(id)){
            toReturn.addMessage("Could not find Security Clearance with Id ", ResultType.NOT_FOUND);
        }

        return toReturn;
    }


    //VALIDATION
    private Result<SecurityClearance> validate(SecurityClearance securityClearance) {
        Result<SecurityClearance> result = new Result<>();
        if (securityClearance == null) {
            result.addMessage("SecurityClearance cannot be null", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(securityClearance.getName())) {
            result.addMessage("Name is required", ResultType.INVALID);
        }


        if (result.isSuccess()) {
            List<SecurityClearance> existingSecurityClearance = repository.findByName(securityClearance.getName());

            for (SecurityClearance sc : existingSecurityClearance) {

                if (sc.getSecurityClearanceId() != securityClearance.getSecurityClearanceId() &&
                        sc.getName().equalsIgnoreCase(securityClearance.getName())){
                    result.addMessage("Security Clearance `name` must be unique.", ResultType.INVALID);
                }
            }
        }

        return result;
    }
}
