package learn.field_agent.controllers;

import learn.field_agent.data.DataException;
import learn.field_agent.domain.Result;
import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/security/clearance")
public class SecurityClearanceController {

    @Autowired
    SecurityClearanceService service;

    @GetMapping
    public ResponseEntity<List<SecurityClearance>> getAllSecurityClearance(){
        List<SecurityClearance> allSecurityClearance = service.getAllSecurityClearance();

        return ResponseEntity.ok( allSecurityClearance );
    }

    //read by id
    @GetMapping("/{id}")
    public ResponseEntity<SecurityClearance> getSecurityClearanceById(@PathVariable Integer id){
        SecurityClearance matchingSecurityClearance = service.findById( id );

        if( matchingSecurityClearance == null ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matchingSecurityClearance);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody SecurityClearance securityClearance) {

        Result<SecurityClearance> result = service.add(securityClearance);

        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }

        return ErrorResponse.build(result);
    }


    @PutMapping("/{securityClearanceId}")
    public ResponseEntity<Object> update(@PathVariable int securityClearanceId, @RequestBody SecurityClearance sc) {
        if (securityClearanceId != sc.getSecurityClearanceId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<SecurityClearance> result = service.update(sc);
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
