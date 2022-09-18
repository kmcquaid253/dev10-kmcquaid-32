package learn.field_agent.data;

import learn.field_agent.models.Agent;

public interface AliasRepository {
    boolean deleteById(Integer id) throws DataException;

//    Agent findById(int agentId);
}
