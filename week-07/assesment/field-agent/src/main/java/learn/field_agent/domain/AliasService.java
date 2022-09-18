package learn.field_agent.domain;

import learn.field_agent.data.AliasRepository;
import learn.field_agent.data.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliasService {
    @Autowired
    private final AliasRepository repository;

    public AliasService(AliasRepository repository) {
        this.repository = repository;
    }

    public Result deleteById(Integer id) throws DataException {
        Result toReturn = new Result();

        if(!repository.deleteById(id)){
            toReturn.addMessage("Could not find alias with Id", ResultType.NOT_FOUND);
        }

        return toReturn;
    }
}
