package learn.field_agent.data;

import learn.field_agent.models.SecurityClearance;

import java.util.List;

public interface SecurityClearanceRepository {
    SecurityClearance findById(int securityClearanceId);
    List<SecurityClearance> getAllSecurityClearance();

    SecurityClearance add(SecurityClearance securityClearance);

    List<SecurityClearance> findByName(String name);

    boolean update(SecurityClearance sc);

    boolean deleteById(Integer id) throws DataException;
}
