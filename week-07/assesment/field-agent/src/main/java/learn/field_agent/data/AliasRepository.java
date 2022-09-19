package learn.field_agent.data;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;

public interface AliasRepository {
    boolean deleteById(Integer id) throws DataException;

    Alias add(Alias alias);

    boolean update(Alias alias);
}
