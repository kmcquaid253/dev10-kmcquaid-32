package learn.field_agent.data;

import learn.field_agent.data.mappers.AgentMapper;
import learn.field_agent.models.Agent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class AliasJdbcTemplateRepository implements AliasRepository{

    private final JdbcTemplate jdbcTemplate;

    public AliasJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean deleteById(Integer id) throws DataException {
        return jdbcTemplate.update("delete from alias where alias_id = ?;", id) > 0;
    }

//    @Override
//    @Transactional
//    public Agent findById(int agentId) {
//
//        final String sql = "select a.*, al.* from agent as a "
//                + "inner join alias as al on a.agent_id = al.agent_id "
//                + "where a.agent_id = ?;";
//
//        // 1. Map the Agent.
//        Agent agent = jdbcTemplate.query(sql, new AgentMapper(), agentId).stream()
//                .findFirst().orElse(null);
//
//        return agent;
//    }
}

