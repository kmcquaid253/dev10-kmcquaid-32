package learn.field_agent.data;

import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SecurityClearanceJdbcTemplateRepository implements SecurityClearanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public SecurityClearanceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SecurityClearance findById(int securityClearanceId) {

//        final String sql = "select security_clearance_id, name security_clearance_name "
//                + "from security_clearance "
//                + "where security_clearance_id = ?;";
//
//        return jdbcTemplate.query(sql, new SecurityClearanceMapper(), securityClearanceId)
//                .stream()
//                .findFirst().orElse(null);
        return null;
    }

    @Override
    public List<SecurityClearance> getAllSecurityClearance() {
        final String sql = "security_clearance_id, name ";

        return jdbcTemplate.query("SELECT * FROM field_agent.security_clearance", new SecurityClearanceMapper() );
    }
}
