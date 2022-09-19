package learn.field_agent.controllers;

import learn.field_agent.data.DataException;
import learn.field_agent.domain.AliasService;
import learn.field_agent.domain.Result;
import learn.field_agent.models.Agent;
import learn.field_agent.models.Alias;
import learn.field_agent.models.SecurityClearance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alias")
public class AliasController {

    @Autowired
    AliasService service;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Alias alias) {

        Result<Alias> result = service.add(alias);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/{aliasId}")
    public ResponseEntity<Object> update(@PathVariable int aliasId, @RequestBody Alias alias) {

        if (aliasId != alias.getAliasId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Alias> result = service.update(alias);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteSecurityClearanceById(@PathVariable Integer id) throws DataException {
        Result deleteResult = service.deleteById(id);

        if(deleteResult.isSuccess()){
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity(deleteResult.getMessages().get(0), HttpStatus.NOT_FOUND);
    }
}


