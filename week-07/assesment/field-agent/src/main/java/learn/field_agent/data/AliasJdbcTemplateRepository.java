package learn.field_agent.data;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AliasJdbcTemplateRepository implements AliasRepository{

    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean deleteById(Integer id) throws DataException {


        int aliasTable = jdbcTemplate.update(
                "delete from alias where alias_id = ?;", id);

        if( aliasTable < 1){
            return false;
        } else if ( aliasTable > 1 ){
            throw new DataException("Alias Ids are not unique in the database!");
        }

        return true;
    }
}

