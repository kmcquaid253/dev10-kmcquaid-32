package learn.field_agent.data;

import learn.field_agent.data.mappers.AliasMapper;
import learn.field_agent.models.Alias;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class AliasJdbcTemplateRepository implements AliasRepository{

    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Alias> findByName(String name) {
        return jdbcTemplate.query( "SELECT * FROM alias WHERE `name`= ?;", new AliasMapper(), name);
    }

    @Override
    public List<Alias> findByPersona(String persona) {
        return jdbcTemplate.query( "SELECT * FROM alias WHERE persona= ?;", new AliasMapper(), persona);
    }
    @Override
    public boolean deleteById(Integer id) throws DataException {
        return jdbcTemplate.update("delete from alias where alias_id = ?;", id) > 0;
    }

    @Override
    public Alias add(Alias alias) {
        final String sql = "insert into alias(`name`, persona, agent_id) values (?,?,?);";

        KeyHolder holder = new GeneratedKeyHolder();

        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alias.getName());
            ps.setString(2, alias.getPersona());
            ps.setInt(3, alias.getAgentId());
            return ps;
        }, holder);

        if (rowsAffected <= 0) {
            return null;
        }

        alias.setAliasId(holder.getKey().intValue());
        return alias;
    }

    @Override
    public boolean update(Alias alias) {
        final String sql = "update alias set `name` = ?, persona = ?, agent_id = ? where alias_id = ?;";

        return jdbcTemplate.update(sql,
                alias.getName(),
                alias.getPersona(),
                alias.getAgentId(),
                alias.getAliasId()) > 0;
    }

    @Override
    public Alias findById(Integer id) {
        return jdbcTemplate.query("SELECT * FROM field_agent.alias where alias_id = ?;",
                new AliasMapper(),
                id).stream().findFirst().orElse(null);
    }

}

