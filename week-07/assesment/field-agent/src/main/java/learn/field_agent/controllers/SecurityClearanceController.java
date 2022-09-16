package learn.field_agent.controllers;

import learn.field_agent.domain.SecurityClearanceService;
import learn.field_agent.models.Agent;
import learn.field_agent.models.SecurityClearance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
