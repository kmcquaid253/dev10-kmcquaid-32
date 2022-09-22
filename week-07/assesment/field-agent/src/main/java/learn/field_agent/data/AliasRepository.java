package learn.field_agent.data;

import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;

import java.util.List;

public interface AliasRepository {
    List<Alias> findByName(String name);

    boolean deleteById(Integer id) throws DataException;

    Alias add(Alias alias);

    boolean update(Alias alias);

    Alias findById(Integer id);
}
