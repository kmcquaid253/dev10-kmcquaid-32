package learn.solarfarm.controller;

import learn.solarfarm.data.DataAccessException;
import learn.solarfarm.domain.SolarPanelResult;
import learn.solarfarm.domain.SolarPanelService;
import learn.solarfarm.models.SolarPanel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SolarController {

    @Autowired
    private SolarPanelService service;

    @GetMapping()
    public String helloWorld(){
        return "Hello World";
    }

    //TODO: catch/handle the DataAccessException rather than allowing the user
    //          to see all of that info
    @GetMapping("api/{sectionName}")
    public List<SolarPanel> getPanelsBySectionName(@PathVariable String sectionName) throws DataAccessException {

        List<SolarPanel> sectionPanels = service.findBySection(sectionName);

        return sectionPanels;

    }

    @PostMapping("api/create")
    public SolarPanelResult addPanel( @RequestBody SolarPanel toAdd ) throws DataAccessException {
        SolarPanelResult addResult = service.create(toAdd);

        return addResult;
    }

    @PutMapping("api/{solarPanelId}")
    public ResponseEntity<Object> updatePanel(@PathVariable int solarPanelId, @RequestBody SolarPanel solarPanel )
            throws DataAccessException {
        if (solarPanelId != solarPanel.getId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        SolarPanelResult result = service.update(solarPanel);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result.getErrorMessages(), HttpStatus.BAD_REQUEST);
    }

}
