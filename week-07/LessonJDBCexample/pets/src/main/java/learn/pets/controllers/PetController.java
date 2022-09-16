package learn.pets.controllers;

import learn.pets.domain.PetService;
import learn.pets.domain.Result;
import learn.pets.domain.ResultType;
import learn.pets.models.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController          // 1. Spring DI and MVC : registers PetController in the Spring DI context, lets Spring MVC know this is a controller, and pre-configures all handler methods to return results as JSON.
@CrossOrigin(origins = {"http://localhost:3000", "http://initial-domain.com"})
@RequestMapping("/pets") // 2. Base URL : sets one base URL for all PetController requests. The handlers append their paths to /pets.
public class PetController {

    private final PetService service;

    // 3. Auto-inject PetService
    // Since our PetService is registered with a @Service annotation,
    // it's available to be injected and is provided via the PetController constructor.
    public PetController(PetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pet> findAll() {
        return service.findAll();
    }

    @GetMapping("/{petId}")
    public ResponseEntity<Pet> findById(@PathVariable int petId) {
        Pet pet = service.findById(petId);
        if (pet == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pet> add(@RequestBody Pet pet) {
        Result<Pet> result = service.add(pet);
        if (result.getType() == ResultType.INVALID) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
    }

    @PutMapping("/{petId}")
    public ResponseEntity<Void> update(@PathVariable int petId, @RequestBody Pet pet) {

        // id conflict. stop immediately.
        if (petId != pet.getPetId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{petId}")
    public ResponseEntity<Void> delete(@PathVariable int petId) {
        if (service.deleteById(petId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
