package learn.field_agent.controllers;

import learn.field_agent.data.DataException;
import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AliasController {

    @Autowired
    AliasService service;


    @DeleteMapping("/alias/{id}")
    public ResponseEntity deleteSecurityClearanceById(@PathVariable Integer id) throws DataException {
        Result deleteResult = service.deleteById(id);

        if(deleteResult.isSuccess()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(deleteResult.getMessages().get(0), HttpStatus.NOT_FOUND);
    }

//    @GetMapping("/agent/{agentId}")
//    public Agent findById(@PathVariable int agentId) {
//        return service.findById(agentId);
//    }
}


