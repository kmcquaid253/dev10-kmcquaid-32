package learn.field_agent.data;

import learn.field_agent.data.mappers.SecurityClearanceMapper;
import learn.field_agent.models.SecurityClearance;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SecurityClearanceJdbcTemplateRepository implements SecurityClearanceRepository {

    private final JdbcTemplate jdbcTemplate;

    public SecurityClearanceJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SecurityClearance findById(int securityClearanceId) {

        return jdbcTemplate.query("SELECT * FROM field_agent.security_clearance where security_clearance_id = ?",
                new SecurityClearanceMapper(),
                securityClearanceId).stream().findFirst().orElse(null);
    }

    @Override
    public List<SecurityClearance> getAllSecurityClearance() {
        final String sql = "security_clearance_id, name ";

        return jdbcTemplate.query("SELECT * FROM field_agent.security_clearance", new SecurityClearanceMapper() );
    }

    @Override
    public SecurityClearance add(SecurityClearance securityClearance) {

        final String sql = "insert into security_clearance (`name`) "
                + " values (?);";

        KeyHolder holder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, securityClearance.getName());
            return ps;
        }, holder);

        if (rowsAffected <= 0) {
            return null;
        }

        securityClearance.setSecurityClearanceId(holder.getKey().intValue());
        return securityClearance;
    }

    @Override
    public List<SecurityClearance> findByName(String name) {
        return jdbcTemplate.query( "SELECT * FROM security_clearance WHERE `name`= ?", new SecurityClearanceMapper(), name);
    }

    @Override
    public boolean update(SecurityClearance sc) {
        final String sql = "update security_clearance set `name` = ? where security_clearance_id = ?;";

        return jdbcTemplate.update(sql,
                sc.getName(),
                sc.getSecurityClearanceId()) > 0;
    }

    @Override
    //@Transactional
    public boolean deleteById(int id) throws DataException {
        /*
                delete from agency_agent
                where security_clearance_id = 1

                delete from security_clearance
                where security_clearance_id = 1
         */

//        int bridgeTableRows = jdbcTemplate.update(
//                "delete from agency_agent where security_clearance_id = ?;", id);

        int securityClearanceTable = jdbcTemplate.update(
                "delete from security_clearance where security_clearance_id = ?;", id);

        if( securityClearanceTable < 1){
            return false;
        } else if ( securityClearanceTable > 1 ){
            throw new DataException("Ids are not unique in the database!");
        }

        return true;
    }

    @Override
    public int countSecurityClearanceInUse(int securityClearanceId){
        final String sql = ("select count(*) from agency_agent where security_clearance_id = ?;");
        return jdbcTemplate.queryForObject(sql, (rowData, rowNum) ->
                rowData.getInt(1), securityClearanceId);
    }
}
